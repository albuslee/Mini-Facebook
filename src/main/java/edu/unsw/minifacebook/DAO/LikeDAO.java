package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.LikeBean;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.util.ReflexUtil;

@Repository
public class LikeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private NotificationDAO NotificationDao;
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	public void saveObject(LikeBean obj) throws HibernateException{
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {

			String sql = "select nextval('LikeSeq')";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int seq = 0;
			while (rs.next()) {
				seq = rs.getInt(1);
			}
			int eseq = 0;
			sql = "select nextval('EdgeSeq')";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				eseq = rs.getInt(1);
			}
			obj.setId(seq);
			List<String> insertSqls = ReflexUtil.getInserts(obj, seq);
			for (String inserSql : insertSqls) {
				stmt.addBatch(inserSql);
			}
			sql = "insert into entitystore(subject,predicate,object) values ('"
					+ "Edge" + eseq + "', 'label','like'";
			stmt.addBatch(sql);
			
			
			sql = "insert into graph (subject,predicate,object) values ('" + 
			"UserBean" + obj.getLikeFrom().getUserid() + "', 'Edge" + eseq + "','"
					+ "PostBean" + obj.getPost().getId() + "')" ;
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
	
	public LikeBean addLikes(String username, int postid, int thumb) {

		
		UserBean like_from = null;
		like_from = userDao.getUserByUsername(username);
		PostBean post = null;
		post = postDao.getPostByid(postid);
		
		LikeBean likeBean = new LikeBean();
		likeBean.setLikefrom(like_from);
		likeBean.setLike_from_id(like_from.getUserid());
		likeBean.setPostid(post.getId());
		likeBean.setThumb(thumb);
		this.saveObject(likeBean);
		
		//add like to notification
		UserBean userBean = post.getCreator();
		String commented_record = post.getDescription();
		NotificationBean nb = new NotificationBean();
		nb.setType("like");
		nb.setFrom2(like_from);
		nb.setUserBean(userBean);
		nb.setCommented_record(commented_record);
		NotificationDao.insertNotificationByUserBean(userBean, nb);

		
		return likeBean;
	}
	
	public void deleteLikes(String username, int postid) {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {
			UserBean ub = userDao.getUserByUsername(username);
			String sql = "select * from entitystore e, graph g where e.subject = g.predicate and e.object='like'"
					+ " and g.subject='UserBean" + ub.getUserid() + "' and g.object='PostBean" + postid + "'" ;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String likeid = null;
			while (rs.next()) {
				likeid = rs.getString("e.subject");
			}
			sql = "delete * from entitystore where subject = '" + likeid + "'";
			stmt.addBatch(sql);
			sql = "delete * from graph where predicatte = '" + likeid + "'";
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
	
	public LikeBean getLikeBean(String username, int postid) {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		LikeBean likeBean = new LikeBean();
		try {
			UserBean ub = userDao.getUserByUsername(username);
			String sql = "select * from entitystore e, graph g where e.subject = g.predicate and e.object='like'"
					+ " and g.subject='UserBean" + ub.getUserid() + "' and g.object='PostBean" + postid + "'" ;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String likeid = null;
			while (rs.next()) {
				likeid = rs.getString("e.subject");
			}
			sql = "select * from entitystore where subject = '" + likeid + "'";
			stmt.executeQuery(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String predicate = rs.getString("predicate");
				String object = rs.getString("object");
				ReflexUtil.setAttribute(likeBean, predicate, object);
			}
			
			likeBean.setLikefrom(userDao.getUserByUserid(likeBean.getLike_from_id()));
			likeBean.setPost(postDao.getPostByid(likeBean.getPostid()));
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
		return likeBean;
	}
	
	public int numLikes(int postid) {
		
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		int count = 0;
		try {
			//UserBean ub = userDao.getUserByUsername(username);
			String sql = "select distinct(e.subject) from entitystore e, graph g where e.subject = g.predicate and e.object='like'"
					 + " and g.object='PostBean" + postid + "'" ;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				count ++;
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
		
		
		return count;
	}

}
