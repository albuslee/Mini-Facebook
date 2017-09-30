package edu.unsw.minifacebook.DAO;

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

import edu.unsw.minifacebook.bean.Provisioner;

@Repository
public class ProvisionerDAO {
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
	
	

	public Provisioner getUserByUsername(String username){
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Provisioner> query = builder.createQuery(Provisioner.class);
		Root<Provisioner> root = query.from(Provisioner.class);
		query.select(root).where(builder.equal(root.get("username"), username));
		Provisioner p = null;
		Query q = this.getCurrentSession().createQuery(query);
		try {
			 p = (Provisioner) q.getSingleResult();
		}catch(NoResultException nre) {
			nre.printStackTrace();
		}
		return p;
	}
	
}
