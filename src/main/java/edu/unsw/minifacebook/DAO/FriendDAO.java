package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.FriendBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.util.ReflexUtil;

@Repository
public class FriendDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDAO userDao;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}


	public void addFriends(UserBean userBean1, UserBean userBean2) {
		if (userBean1.getUserid() > userBean2.getUserid()) {
			UserBean temp = userBean1;
			userBean1 = userBean2;
			userBean2 = temp;
		}
		
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {
			int eseq = 0;
			String sql = "select nextval('EdgeSeq')";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				eseq = rs.getInt(1);
			}
			
			sql = "insert into entitystore (subject,predicate,object) values ('"
					+ "Edge" + eseq + "', 'label','friend of'";
			stmt.addBatch(sql);
			sql = "insert into entitystore (subject,predicate,object) values ('"
					+ "Edge" + eseq + "', 'FriendTime','" + new Date().toString() + "'";

			sql = "insert into graph (subject,predicate,object) values ('" + 
			"UserBean" + "UserBean"+userBean1.getUserid() + "', 'Edge" + eseq + "','"
					+ "UserBean"+userBean2.getUserid() + "')" ;
			stmt.addBatch(sql);
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public boolean existed(int user1, int user2) {
		if (user1 > user2) {
			int temp = user1;
			user1 = user2;
			user2 = temp;
		}
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		try {
			String sql = "select from graph where subject = 'UserBean" + user1 + "' and object = '"
					+ "UserBean" + user2 + "'";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			return false;
			
		} catch (SQLException se) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public List<UserBean> getAllFriendsByUserid(Integer userId) {
		List<UserBean> userBeanList = null;
		List<Integer> userIdList = this.getAllFriendIdsByUserid(userId);
		for (int i = 0; i < userIdList.size(); i++) {
			UserBean userBean = userDao.getUserByUserid(userIdList.get(i));
			userBeanList.add(userBean);
		}
		return userBeanList;
	}


	public List<Integer> getAllFriendIdsByUserid(Integer userId) {
		List<Integer> result = new ArrayList<Integer>();
		
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		try {
			String sql = "select from graph where subject = 'UserBean" + userId + "'";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String object = rs.getString("object");
				object = object.replaceAll("UserBean", "");
				result.add(Integer.parseInt(object));
			}
			
			sql = "select from graph where object = 'UserBean" + userId + "'";
			
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String subject = rs.getString("subject");
				subject = subject.replaceAll("UserBean", "");
				result.add(Integer.parseInt(subject));
			}
			

			
		} catch (SQLException se) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public Map<Date, String> getFriendActivity(Integer userId) {
		Map<Date, String> result = new HashMap<Date, String>();
		List<FriendBean> friendBeanList = new ArrayList<FriendBean>();
		Query query1 = getCurrentSession().createQuery("from FriendBean where seconduser = (:id)").setParameter("id",
				userId);
		Query query2 = getCurrentSession().createQuery("from FriendBean where firstuser = (:id)").setParameter("id",
				userId);
		friendBeanList = query1.getResultList();
		for (FriendBean fb : friendBeanList) {
			result.put(fb.getFriendtime(),"become friends with" + fb.getFirstUser().getUserid().toString());
		}
		List<FriendBean> tempList2 = query2.getResultList();
		for (FriendBean fb : tempList2) {
			result.put(fb.getFriendtime(),"become friends with" + fb.getSecoundUser().getUserid().toString());
		}
		return result;
	}
}
