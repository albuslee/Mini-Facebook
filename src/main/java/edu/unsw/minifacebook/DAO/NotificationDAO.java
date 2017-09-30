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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import java.util.Date;
import java.util.ArrayList;

@Repository
public class NotificationDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(Object obj) throws HibernateException {
		this.getCurrentSession().save(obj);
	}

	public List<NotificationBean> getNotificationByUserBean(UserBean userBean) {
		List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
		
		Query query1 = getCurrentSession().createQuery
				("from NotificationBean where userBean.id = (:ids)").setParameter("ids", userBean.getUserId());
		
		notificationList = query1.getResultList();
		
		return notificationList;
	}
	
	public void insertNotificationByUserBean(UserBean userBean, String commented_record) {
		long N_time = System.currentTimeMillis();
		Date N_date = new Date(N_time);
		NotificationBean notificationbean = new NotificationBean();
		notificationbean.setuserBean(userBean);
		notificationbean.setcommented_record(commented_record);
		notificationbean.setcomment_time(N_date);
		notificationbean.setnotification_status("unread");
		return;
	}
}
