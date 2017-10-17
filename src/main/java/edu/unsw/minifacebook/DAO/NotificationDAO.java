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

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(NotificationBean obj) throws HibernateException {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {
			obj.setUserid(obj.getUserBean().getUserid());
			obj.setFromid(obj.getFrom2().getUserid());
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
		List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
		
		Query query1 = getCurrentSession().createQuery
				("from NotificationBean where userBean.id = (:ids)").setParameter("ids", userBean.getUserid());
		
		notificationList = query1.getResultList();
		
		return notificationList;
	}
	
	public List getFriendNotificationByUserBean(UserBean userBean) {
		
		List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
		
		Query query1 = getCurrentSession().createQuery
				("from NotificationBean where userBean.id = (:id) and type=(:type)")
				.setParameter("id", userBean.getUserid())
				.setParameter("type", "friend");
		
		notificationList = query1.getResultList();
		
		return notificationList;
	}
	
	
	public void insertNotificationByUserBean(UserBean userBean, NotificationBean notificationbean) {
		long N_time = System.currentTimeMillis();
		Date N_date = new Date(N_time);
		notificationbean.setUserBean(userBean);
		notificationbean.setComment_time(N_date);
		notificationbean.setNotification_status("unread");
		this.getCurrentSession().save(notificationbean);
		return;
	}
	
	
}
