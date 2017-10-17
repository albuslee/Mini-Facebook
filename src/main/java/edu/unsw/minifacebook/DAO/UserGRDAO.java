package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.bean.graphBean;
import edu.unsw.minifacebook.util.ReflexUtil;

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
	
	public List<graphBean> getgraphByRequest(String request) {
		List<graphBean> relationList = new ArrayList<graphBean>();

		
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from graph";
			ResultSet rs = stmt.executeQuery(sql);
			List<String> ids = new ArrayList<String>();
			while(rs.next()){
				ids.add(rs.getString(1));
			}
			
			
			for(String id: ids) {
				graphBean graphBean = new graphBean();
				sql = "select * from entitystore where subject='" + id+ "'";
				rs = stmt.executeQuery(sql);
				String predicate = rs.getString("predicate");
				String object = rs.getString("object");
				ReflexUtil.setAttribute(graphBean, predicate, object);
				relationList.add(graphBean);
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

		return relationList;
	}
}
