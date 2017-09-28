package edu.unsw.minifacebook.DAO;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.UserBean;

@Repository
public class DetailDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	public void saveObject(Object obj) throws HibernateException{
		this.getCurrentSession().save(obj);
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
	
	public DetailBean getUserByUsername(String username) {
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<DetailBean> query = builder.createQuery(DetailBean.class);
		Root<DetailBean> root = query.from(DetailBean.class);
		query.select(root).where(builder.equal(root.get("username"), username));
		DetailBean detailBean = null;
		Query q = this.getCurrentSession().createQuery(query);
		try {
			detailBean = (DetailBean) q.getSingleResult();
		}catch(NoResultException nre) {
			nre.printStackTrace();
		}
		return detailBean;
	}
	
	
	public List<DetailBean> getDetailByname(String name) {
	
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<DetailBean> query = builder.createQuery(DetailBean.class);
		Root<DetailBean> root = query.from(DetailBean.class);
		query.select(root).where(builder.equal(root.get("name"), name));
		ArrayList<DetailBean> list = new ArrayList<DetailBean>();
		Query q = this.getCurrentSession().createQuery(query);
		try {
			list = (ArrayList<DetailBean>) q.getResultList();
		}catch(NoResultException nre) {
			nre.printStackTrace();
		}
		return list;
	}
	
	
}
