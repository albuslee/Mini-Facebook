package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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

	private String action = "send message";

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public PostBean getPostByid(int id) {
		PostBean postBean = new PostBean();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from entitystore where subject='PostBean" + id + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String predicate = rs.getString("predicate");
				String object = rs.getString("object");
				ReflexUtil.setAttribute(postBean, predicate, object);
			}
			postBean.setId(id);

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
		return postBean;
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
			sql = "insert into entitystore(subject,predicate,object) values ('" + "Edge" + eseq
					+ "', 'label','send message')";
			stmt.addBatch(sql);

			sql = "insert into graph (subject,predicate,object) values ('" + "UserBean" + postBean.getCreatorid()
					+ "', 'Edge" + eseq + "','" + "PostBean" + postBean.getId() + "')";
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
		if (userList == null || userList.isEmpty())
			return null;

		List<PostBean> postList = new ArrayList<PostBean>();

		List<Integer> postidList = new ArrayList<Integer>();
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		ResultSet rs = null;

		try {
			Statement stmt = conn.createStatement();
			for (Integer userid : userList) {
				String sql = "select distinct(g.object) from entitystore e, graph g where e.subject = g.predicate and e.object='"
						+ action + "'" + " and g.subject='UserBean" + userid + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String postid = rs.getString(1);
					postid = postid.replaceAll("PostBean", "");
					postidList.add(Integer.parseInt(postid));
				}
			}

			Collections.sort(postidList);
			for (Integer postid : postidList) {
				PostBean pb = new PostBean();
				String sql = "select * from entitystore where subject='PostBean" + postid + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String predicate = rs.getString("predicate");
					String object = rs.getString("object");
					ReflexUtil.setAttribute(pb, predicate, object);
				}
				pb.setId(postid);
				postList.add(pb);
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

	public Map<Date, String> getPostActivity(Integer userId) {
		List<PostBean> postList = null;
		Map<Date, String> result = new HashMap<Date, String>();
		Query query2 = getCurrentSession().createQuery("from PostBean where creator.id = :id").setParameter("id",
				userId);

		postList = query2.getResultList();
		for (PostBean pb : postList) {
			result.put(pb.getPosttime(), "writes post:" + pb.getDescription());
		}
		return result;
	}

}
