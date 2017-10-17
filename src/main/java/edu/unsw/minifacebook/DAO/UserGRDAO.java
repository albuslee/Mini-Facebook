package edu.unsw.minifacebook.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.UserBean;

@Repository
public class UserGRDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	public void saveObject(UserBean ub) throws HibernateException{
		this.getCurrentSession().save(ub);
	}
	
	public void updateObject(Object obj) throws HibernateException{
		Session session = this.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
		session.close();
	}
	
	
	public boolean ifUsernameExisted(String username) {
		return this.getUserByUsername(username) != null;
	}
	
	public UserBean getUserByUsername(String username){
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<UserBean> query = builder.createQuery(UserBean.class);
		Root<UserBean> root = query.from(UserBean.class);
		query.select(root).where(builder.equal(root.get("username"), username));
		UserBean userBean = null;
		Query q = this.getCurrentSession().createQuery(query);
		try {
			 userBean = (UserBean) q.getSingleResult();
		}catch(NoResultException nre) {
			nre.printStackTrace();
		}
		return userBean;
	}
	
	public UserBean getUserByUserid(Integer userId) {
		UserBean userBean = null;
		userBean = this.getCurrentSession().get(UserBean.class, userId);
		return userBean;
	}

	public List getAllUsers() {
		List<UserBean> result;
		Query query = getCurrentSession().createQuery
				("from UserBean");
		result = query.getResultList();
		return result;
		
	}
}
