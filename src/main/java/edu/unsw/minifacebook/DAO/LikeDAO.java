package edu.unsw.minifacebook.DAO;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.LikeBean;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;

@Repository
public class LikeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private NotificationDAO NotificationDao;
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	public void saveObject(Object obj) throws HibernateException{
		this.getCurrentSession().save(obj);
	}
	
	public LikeBean addLikes(String username, int postid, int thumb) {
		UserBean like_from = null;
		like_from = userDao.getUserByUsername(username);
		PostBean post = null;
		post = this.getCurrentSession().get(PostBean.class, postid);
		
		LikeBean likeBean = new LikeBean();
		likeBean.setLikeFrom(like_from);
		likeBean.setPostId(post);
		likeBean.setThumb(thumb);
		this.getCurrentSession().save(likeBean);
		
		//add like to notification
		UserBean userBean = post.getCreator();
		String commented_record = post.getDescription();
		NotificationBean nb = new NotificationBean();
		nb.setType("like");
		nb.setFrom2(like_from);
		nb.setuserBean(userBean);
		nb.setcommented_record(commented_record);
		NotificationDao.insertNotificationByUserBean(userBean, nb);

		
		return likeBean;
	}
	
	public void deleteLikes(String username, int postid) {
		LikeBean likeBean = this.getLikeBean(username, postid);
		int id = likeBean.getId();
		likeBean.setThumb(0);
		Session session = this.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(likeBean);
		tx.commit();
		session.close();
		
//		String sid = String.valueOf(id);
//		this.getCurrentSession().beginTransaction();
//		this.getCurrentSession().createQuery("delete from edu.unsw.minifacebook.bean.LikeBean where id =" + sid).executeUpdate();
		
//		try {
//			sessionFactory.openSession().beginTransaction();
//			//LikeBean unlikeBean = (LikeBean) this.getCurrentSession().get(LikeBean.class, id);
//			sessionFactory.openSession().delete(likeBean);
//			// commit only, if tx still hasn't been committed yet by Hibernate
//			if (sessionFactory.openSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) { 
//				sessionFactory.openSession().getTransaction().commit();
//			}
//			//this.getCurrentSession().getTransaction().commit();
//			
//			//this.getCurrentSession().close();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			this.getCurrentSession().getTransaction().rollback();
//		}

	}
	
	public LikeBean getLikeBean(String username, int postid) {
		UserBean like_from = null;
		like_from = userDao.getUserByUsername(username);
		PostBean post = null;
		post = this.getCurrentSession().get(PostBean.class, postid);
		
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<LikeBean> query = builder.createQuery(LikeBean.class);
		Root<LikeBean> root = query.from(LikeBean.class);
		query.select(root).where(builder.equal(root.get("like_from"), like_from), builder.equal(root.get("post"), post));
		LikeBean likeBean = null;
		Query q = this.getCurrentSession().createQuery(query);
		try {
			 likeBean = (LikeBean) q.getSingleResult();
			 System.out.println(likeBean.getId());
		}catch(NoResultException nre) {
			nre.printStackTrace();
		}
		
//		CriteriaDelete<LikeBean> delete = builder.createCriteriaDelete(LikeBean.class);
//
//		// set the root class
//		Root<LikeBean> e = delete.from(LikeBean.class);
//
//		// set where clause
//		delete.where(builder.equal(e.get("like_from"), like_from), builder.equal(e.get("post"), post));
//
//		// perform update
//		this.em.createQuery(delete).executeUpdate();
		return likeBean;
	}
	
	public int numLikes(int postid) {
		PostBean post = null;
		post = this.getCurrentSession().get(PostBean.class, postid);
		
		List<LikeBean> likeList = new ArrayList<LikeBean>();
		CriteriaBuilder builder = this.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<LikeBean> query = builder.createQuery(LikeBean.class);
		Root<LikeBean> root = query.from(LikeBean.class);
		query.select(root);
		Query query1 = getCurrentSession().createQuery
				(query);
		try {
		likeList = (List<LikeBean>)query1.getResultList();
		}catch(NoResultException nre) {
			nre.printStackTrace();
		}
		int count = 0;
		LikeBean l = null;
		Iterator<LikeBean> i = likeList.iterator();
		while(i.hasNext()) {
			l = i.next();
			if (l.getPostId().getId() == postid) {
				count += l.getThumb();
			}
		}
		System.out.println(count);
		return count;
	}

}
