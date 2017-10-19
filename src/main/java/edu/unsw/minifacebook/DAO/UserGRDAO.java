package edu.unsw.minifacebook.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import edu.unsw.minifacebook.bean.GraphBean;
import edu.unsw.minifacebook.util.ReflexUtil;

@Repository
public class UserGRDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private DetailDAO detailDao;

	private List<GraphBean> relationList = new ArrayList<GraphBean>();

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(UserBean ub) throws HibernateException {
		this.getCurrentSession().save(ub);
	}

	public void updateObject(Object obj) throws HibernateException {
		Session session = this.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
		session.close();
	}

	public boolean ifUsernameExisted(String username) {
		return this.getUserByUsername(username) != null;
	}

	public UserBean getUserByUsername(String username) {
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<UserBean> query = builder.createQuery(UserBean.class);
		Root<UserBean> root = query.from(UserBean.class);
		query.select(root).where(builder.equal(root.get("username"), username));
		UserBean userBean = null;
		Query q = this.getCurrentSession().createQuery(query);
		try {
			userBean = (UserBean) q.getSingleResult();
		} catch (NoResultException nre) {
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
		Query query = getCurrentSession().createQuery("from UserBean");
		result = query.getResultList();
		return result;

	}

	public List<GraphBean> getgraphByRequest(String request, String field) {

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = null;
			if (request.equals("")) {
				relationList = new ArrayList<GraphBean>();
				sql = "select * from graph";
				rs = stmt.executeQuery(sql);
				List<String> ids = new ArrayList<String>();
				while (rs.next()) {
					GraphBean graphBean = new GraphBean();
					String subject = rs.getString("subject");
					String predicate = rs.getString("predicate");
					String object = rs.getString("object");
					sql = "select * from entitystore where subject='" + predicate + "'";
					ResultSet rss = stmt.executeQuery(sql);
					while (rss.next()) {
						String idd = rss.getString("predicate");
						if (idd.contains("label")) {
							GraphBean graphBean2 = new GraphBean();
							predicate=rss.getString("object");
							graphBean2.setSubject(subject);
							graphBean2.setPredicate(predicate);
							graphBean2.setObject(object);
							relationList.add(graphBean2);
						}
						}
				}
			} else {
				if (field.equals("people")) {
					relationList = new ArrayList<GraphBean>();
					List<DetailBean> detailList = detailDao.getDetailByname(request);
					
					Set<String> ids = new HashSet<String>();

					for(DetailBean db: detailList){
						UserBean ub = userDao.getUserByUsername(db.getUsername());
						ids.add("UserBean" + ub.getUserid());
					}
					
			
					for (Iterator it = ids.iterator(); it.hasNext();) {
						GraphBean graphBean = new GraphBean();
						String id = it.next().toString();
						sql = "select * from graph where subject='" + id + "' or object='" + id + "'";
						rs = stmt.executeQuery(sql);
						String des = "";
						while (rs.next()) {
							String subject = rs.getString("subject");
							String predicate = rs.getString("predicate");
							String object = rs.getString("object");
							sql = "select * from entitystore where subject='" + predicate + "'";
							ResultSet rss = stmt.executeQuery(sql);
							while (rss.next()) {
								String idd = rss.getString("predicate");
								if (idd.contains("label")) {
									GraphBean graphBean2 = new GraphBean();
									predicate=rss.getString("object");
									graphBean2.setSubject(subject);
									graphBean2.setPredicate(predicate);
									graphBean2.setObject(object);
									relationList.add(graphBean2);
								}
								}
						}
					}
				}
				if (field.equals("friend")) {
					relationList = new ArrayList<GraphBean>();
					sql = "select * from entitystore where object like '%" + request + "%'";
					rs = stmt.executeQuery(sql);
					Set ids = new HashSet();
					while (rs.next()) {
						String id = rs.getString("subject");
						if (id.contains("DetailBean")) {
							id = "UserBean" + id.replaceAll("\\D", "");
						}
						ids.add(id);
					}
					for (Iterator it = ids.iterator(); it.hasNext();) {
						GraphBean graphBean = new GraphBean();
						String id = it.next().toString();
						sql = "select * from graph where subject='" + id + "' or object='" + id + "'";
						rs = stmt.executeQuery(sql);
						String des = "";
						while (rs.next()) {
							String subject = rs.getString("subject");
							String predicate = rs.getString("predicate");
							String object = rs.getString("object");
							sql = "select * from entitystore where subject='" + predicate + "'";
							ResultSet rss = stmt.executeQuery(sql);
							while (rss.next()) {
								String idd = rss.getString("object");
								if (idd.contains("friend")) {
									GraphBean graphBean2 = new GraphBean();
									graphBean2.setSubject(subject);
									graphBean2.setPredicate("friend");
									graphBean2.setObject(object);
									relationList.add(graphBean2);
								}
							}
						}
					}
				}
				if (field.equals("message")) {
					relationList = new ArrayList<GraphBean>();
					sql = "select * from entitystore where object like '%" + request + "%' and predicate='Description'";
					rs = stmt.executeQuery(sql);
					Set ids = new HashSet();
					while (rs.next()) {
						String id = rs.getString("subject");
						ids.add(id);
					}
					for (Iterator it = ids.iterator(); it.hasNext();) {
						GraphBean graphBean = new GraphBean();
						String id = it.next().toString();
						sql = "select * from graph where subject='" + id + "' or object='" + id + "'";
						rs = stmt.executeQuery(sql);
						String des = "";
						while (rs.next()) {
							String subject = rs.getString("subject");
							String predicate = rs.getString("predicate");
							String object = rs.getString("object");
							sql = "select * from entitystore where subject='" + predicate + "'";
							ResultSet rss = stmt.executeQuery(sql);
							while (rss.next()) {
								String idd = rss.getString("predicate");
								if (idd.contains("label")) {
									GraphBean graphBean2 = new GraphBean();
									predicate=rss.getString("object");
									graphBean2.setSubject(subject);
									graphBean2.setPredicate(predicate);
									graphBean2.setObject(object);
									relationList.add(graphBean2);
								}
								}
						}
					}

				}
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

	public List<GraphBean> getEntitiesByRequest(String request) {
		List<GraphBean> entityList = new ArrayList<GraphBean>();

		Session session = this.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		try {
			Statement stmt = conn.createStatement();
			Set ids = new HashSet();
			for (GraphBean bean : relationList) {
				String sql = "select * from entitystore where subject='" + bean.getSubject() + "' or subject='"
						+ bean.getObject() + "'";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("subject");
					ids.add(id);
				}
			}

			if (ids.isEmpty())
				return null;
			for (Iterator it = ids.iterator(); it.hasNext();) {
				GraphBean graphBean = new GraphBean();
				String id = it.next().toString();
				String sql = "select * from entitystore where subject='" + id + "'";
				ResultSet rs = stmt.executeQuery(sql);
				String des = "";
				while (rs.next()) {
					String subject = rs.getString("subject");
					String predicate = rs.getString("predicate");
					String object = rs.getString("object");
					if (predicate.equals("Username")) {
						des = object;
						DetailBean detail=detailDao.getUserByUsername(des);
						des="name: "+detail.getName()+"<br>age: "+detail.getAge()+"<br>Birthday: "+detail.getBirthday()+"<br>Gender: "
						+detail.getGender()+"<br>Major:"+detail.getMajor();
					}
				}
				//id = id.replaceAll("UserBean", "");
				//UserBean ub = userDao.getUserByUserid(Integer.parseInt(id));
				//id = detailDao.getUserByUsername(ub.getUsername()).getName();
				graphBean.setSubject(id);
				graphBean.setObject(des);
				entityList.add(graphBean);
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

		return entityList;
	}
}
