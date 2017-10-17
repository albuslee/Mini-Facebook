package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.util.ReflexUtil;

@Repository
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(UserBean userBean) throws HibernateException {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {

			String sql = "select nextval('UserSeq')";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int seq = 0;
			while (rs.next()) {
				seq = rs.getInt(1);
			}
			userBean.setUserid(seq);
			List<String> insertSqls = ReflexUtil.getInserts(userBean, seq);
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

	public void updateObject(Object obj) throws HibernateException {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();

			int seq = ((UserBean) obj).getUserid();
			String deleteSql = ReflexUtil.getDeletes(obj);
			List<String> insertSqls = ReflexUtil.getInserts(obj, seq);
			stmt.addBatch(deleteSql);
			for (String inserSql : insertSqls) {
				stmt.addBatch(inserSql);
			}
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean ifUsernameExisted(String username) {
		return this.getUserByUsername(username) != null;
	}

	public UserBean getUserByUsername(String username) {
		UserBean userBean = new UserBean();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select subject from entitystore where subject like 'UserBean%'"
					+ " and predicate='Username' and object='" + username + "'";
			ResultSet rs = stmt.executeQuery(sql);
			String id = null;
			while (rs.next()) {
				id = rs.getString("subject");
			}
			if (id == null)
				return null;
			String realid = id.replaceAll("UserBean", "");
			ReflexUtil.setAttribute(userBean, "Userid", realid);
			sql = "select * from entitystore where subject='" + id + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String predicate = rs.getString("predicate");
				if (predicate.contains("Time")) {
					String dateString = rs.getString("object");
					Date parsed;
					try {
					    SimpleDateFormat format =
					        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
					    parsed = format.parse(dateString);
					}
					catch(ParseException pe) {
					    throw new IllegalArgumentException(pe);
					}
					ReflexUtil.setAttribute(userBean, predicate, parsed);
				} else {
					String object = rs.getString("object");
					ReflexUtil.setAttribute(userBean, predicate, object);
				}
				
			}

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return userBean;
	}

	public UserBean getUserByUserid(Integer userId) {
		UserBean userBean = new UserBean();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from entitystore where subject='" + userId + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String predicate = rs.getString("predicate");
				String object = rs.getString("object");
				ReflexUtil.setAttribute(userBean, predicate, object);
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return userBean;
	}

	public List getAllUsers() {
		List<UserBean> userList = new ArrayList<UserBean>();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select distinct(subject) from entitystore";
			ResultSet rs = stmt.executeQuery(sql);
			List<String> ids = new ArrayList<String>();
			while (rs.next()) {
				ids.add(rs.getString(1));
			}

			for (String id : ids) {
				UserBean userBean = new UserBean();
				sql = "select * from entitystore where subject='" + id + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String predicate = rs.getString("predicate");
					String object = rs.getString("object");
					ReflexUtil.setAttribute(userBean, predicate, object);
				}
				userList.add(userBean);
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return userList;

	}
}
