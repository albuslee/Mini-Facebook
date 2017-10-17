package edu.unsw.minifacebook.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.util.ReflexUtil;

import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class NotificationDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDao;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(NotificationBean obj) throws HibernateException {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {
			obj.setUserid2(obj.getUserBean().getUserid());
			obj.setFromid2(obj.getFrom2().getUserid());
			String sql = "select nextval('NotificationSeq')";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int seq = 0;
			while (rs.next()) {
				seq = rs.getInt(1);
			}
			obj.setId(seq);
			List<String> insertSqls = ReflexUtil.getInserts(obj, seq);
			for (String inserSql : insertSqls) {
				stmt.addBatch(inserSql);
			}
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

	public List<NotificationBean> getNotificationByUserBean(UserBean userBean) {
		List<NotificationBean> list = new ArrayList<NotificationBean>();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select distinct(subject) from entitystore where subject like 'NotificationBean%'"
					+ " and predicate='Userid2' and object='" + userBean.getUserid() + "'";
			ResultSet rs = stmt.executeQuery(sql);

			List<String> ids = new ArrayList<String>();
			while (rs.next()) {
				String id = rs.getString("subject");
				ids.add(id);
			}

			if (ids.isEmpty())
				return list;
			for (String id : ids) {
				NotificationBean nb = new NotificationBean();
				sql = "select * from entitystore where subject='" + id + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String predicate = rs.getString("predicate");
					String object = rs.getString("object");
					ReflexUtil.setAttribute(nb, predicate, object);
				}
				nb.setUserBean(userDao.getUserByUserid(nb.getUserid2()));
				nb.setFrom2(userDao.getUserByUserid(nb.getFromid2()));
				list.add(nb);
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		return list;
	}
	
	public List getFriendNotificationByUserBean(UserBean userBean) {
		
		List<NotificationBean> notificationList = this.getNotificationByUserBean(userBean);
		for(NotificationBean nb: notificationList) {
			if(!nb.getType().equals("friend")) {
				notificationList.remove(nb);
			}
		}
		
		return notificationList;
	}
	
	
	public void insertNotificationByUserBean(UserBean userBean, NotificationBean notificationbean) {
		long N_time = System.currentTimeMillis();
		Date N_date = new Date(N_time);
		notificationbean.setUserBean(userBean);
		notificationbean.setComment_time(N_date);
		notificationbean.setNotification_status("unread");
		this.saveObject(notificationbean);
		return;
	}
	
	
}
