package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.util.ReflexUtil;

@Repository
public class DetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(DetailBean detailBean) throws HibernateException {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		try {

			String sql = "select nextval('DetailSeq')";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int seq = 0;
			if (rs.next()) {
				seq = rs.getInt(1);
			}
			detailBean.setId(seq);
			List<String> insertSqls = ReflexUtil.getInserts(detailBean, seq);
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

	public void updateObject(Object obj) throws HibernateException {
		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();
		DetailBean db = (DetailBean) obj;
		try {
			Statement stmt = conn.createStatement();
			DetailBean exitedbean = this.getUserByUsername(db.getUsername());
			int seq = exitedbean.getId();
			String deleteSql = ReflexUtil.getDeletes(obj);
			List<String> insertSqls = ReflexUtil.getInserts(obj, seq);
			stmt.addBatch(deleteSql);
			for (String inserSql : insertSqls) {
				stmt.addBatch(inserSql);
			}
			stmt.executeBatch();
			conn.commit();
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
	}

	public boolean ifUsernameExisted(String username) {
		return this.getUserByUsername(username) != null;
	}

	public DetailBean getUserByUsername(String username) {
		DetailBean detailBean = new DetailBean();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select subject from entitystore where subject like 'DetailBean%'"
					+ " and predicate='Username' and object='" + username + "'";
			ResultSet rs = stmt.executeQuery(sql);
			String id = null;
			while (rs.next()) {
				id = rs.getString("subject");
			}
			if (id == null)
				return null;
			Integer realid = Integer.parseInt(id.replaceAll("DetailBean", ""));
			sql = "select * from entitystore where subject='" + id + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String predicate = rs.getString("predicate");
				String object = rs.getString("object");
				ReflexUtil.setAttribute(detailBean, predicate, object);
			}
			detailBean.setId(realid.intValue());
			conn.commit();
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
		return detailBean;
	}

	public List<DetailBean> getDetailByname(String name) {
		List<DetailBean> list = new ArrayList<DetailBean>();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		session.beginTransaction();
		Connection conn = sessionImpl.connection();

		try {
			Statement stmt = conn.createStatement();
			String sql = "select subject from entitystore where subject like 'DetailBean%'"
					+ " and predicate='Name' and object='" + name + "'";
			ResultSet rs = stmt.executeQuery(sql);

			List<String> ids = new ArrayList<String>();
			while (rs.next()) {
				String id = rs.getString("subject");
				ids.add(id);
			}

			if (ids.isEmpty())
				return null;
			for (String id : ids) {
				DetailBean detailBean = new DetailBean();
				sql = "select * from entitystore where subject='" + id + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String predicate = rs.getString("predicate");
					String object = rs.getString("object");
					ReflexUtil.setAttribute(detailBean, predicate, object);
				}
				list.add(detailBean);
			}
			conn.commit();
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

		return list;
	}

	public List<DetailBean> getFriendByname(String name) {

		return this.getDetailByname(name);
	}

	public ArrayList<DetailBean> getFriendByGender(String gender) {
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<DetailBean> query = builder.createQuery(DetailBean.class);
		Root<DetailBean> root = query.from(DetailBean.class);
		query.select(root).where(builder.equal(root.get("gender"), gender));
		ArrayList<DetailBean> list = new ArrayList<DetailBean>();
		Query q = this.getCurrentSession().createQuery(query);
		try {
			list = (ArrayList<DetailBean>) q.getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace();
		}
		return list;

	}

	public ArrayList<DetailBean> getFriendByBirthDay(String birthday) {
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<DetailBean> query = builder.createQuery(DetailBean.class);
		Root<DetailBean> root = query.from(DetailBean.class);
		query.select(root).where(builder.equal(root.get("birthday"), birthday));
		ArrayList<DetailBean> list = new ArrayList<DetailBean>();
		Query q = this.getCurrentSession().createQuery(query);
		try {
			list = (ArrayList<DetailBean>) q.getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace();
		}
		return list;

	}

	public ArrayList<DetailBean> getFriendByMajor(String major) {
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<DetailBean> query = builder.createQuery(DetailBean.class);
		Root<DetailBean> root = query.from(DetailBean.class);
		query.select(root).where(builder.equal(root.get("major"), major));
		ArrayList<DetailBean> list = new ArrayList<DetailBean>();
		Query q = this.getCurrentSession().createQuery(query);
		try {
			list = (ArrayList<DetailBean>) q.getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace();
		}
		return list;

	}

}
