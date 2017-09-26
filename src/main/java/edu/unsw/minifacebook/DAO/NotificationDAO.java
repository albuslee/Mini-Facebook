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

	public NotificationBean generate_Notification(int id, String commented_record) {
		long N_time = System.currentTimeMillis();
		Date N_date = new Date(N_time);
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(dateFormat.format(N_date));
		NotificationBean notificationbean = null;
		notificationbean.setId(id);
		notificationbean.setcommented_record(commented_record);
		notificationbean.setcomment_time(N_date);
		notificationbean.setnotification_status("unread");
		

		return notificationbean;
	}
}
