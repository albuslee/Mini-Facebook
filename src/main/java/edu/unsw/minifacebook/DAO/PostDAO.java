package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.util.ReflexUtil;

@Repository
public class PostDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(PostBean postBean) throws HibernateException {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {

			String sql = "select nextval('PostSeq')";
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
			postBean.setId(seq);
			List<String> insertSqls = ReflexUtil.getInserts(postBean, seq);
			for (String inserSql : insertSqls) {
				stmt.addBatch(inserSql);
			}
			sql = "insert into graph (subject,predicate,object) values ('" + 
			"UserBean" + postBean.getCreator() + "', 'Edge" + eseq + "','"
					+ "PostBean" + postBean.getId() + "')" ;
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

	public List<PostBean> getPostsByUserlist(List<Integer> userList) {
		if(userList == null || userList.isEmpty()) return null;
		
		
		List<PostBean> postList = new ArrayList<PostBean>();

		
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select distinct(subject) from entitystore";
			ResultSet rs = stmt.executeQuery(sql);
			List<String> ids = new ArrayList<String>();
			while(rs.next()){
				ids.add(rs.getString(1));
			}
			
			
			for(String id: ids) {
				PostBean postBean = new PostBean();
				sql = "select * from entitystore where subject='" + id+ "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String predicate = rs.getString("predicate");
					String object = rs.getString("object");
					ReflexUtil.setAttribute(postBean, predicate, object);
				}
				postList.add(postBean);
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

		return postList;
	}
	
	public Map<Date, String> getPostActivity(Integer userId){
		List<PostBean> postList = null;
		Map<Date, String> result = new HashMap<Date,String>();
		Query query2 = getCurrentSession().createQuery
				("from PostBean where creator.id = :id").setParameter("id", userId);
		
		postList = query2.getResultList();
		for(PostBean pb: postList) {
			result.put(pb.getPosttime(), "writes post:" + pb.getDescription());
		}
		return result;
	}


}
