package edu.unsw.minifacebook.DAO;

import java.util.ArrayList;
import java.util.Iterator;
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
//		NotificationBean nb = new NotificationBean();
//		nb.setType("like");
//		nb.setFrom2(like_from);
//		nb.setuserBean(post.getCreator());
//		nb.setcommented_record(post.getDescription());
//		NotificationDao.insertNotificationByUserBean(post.getCreator(), nb);
		
		return likeBean;
	}
	
	public void deleteLikes(String username, int postid) {
		LikeBean likeBean = this.getLikeBean(username, postid);
		//int id = likeBean.getId();
		try {
			Transaction trn = this.getCurrentSession().beginTransaction();
			//LikeBean unlikeBean = (LikeBean) this.getCurrentSession().get(LikeBean.class, id);
			this.getCurrentSession().delete(likeBean);
			trn.commit();
			this.getCurrentSession().close();
		} catch (HibernateException e) {
			e.printStackTrace();
			this.getCurrentSession().getTransaction().rollback();
		}

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
		return likeBean;
	}
	
	public int numLikes(PostBean post) {
		List<LikeBean> likeList = new ArrayList<LikeBean>();
		Query query1 = getCurrentSession().createQuery
				("select * from Likes");
		try {
		likeList = query1.getResultList();
		}catch(NoResultException nre) {
			nre.printStackTrace();
		}
		int count = 0;
		LikeBean l = null;
		Iterator<LikeBean> i = likeList.iterator();
		while(i.hasNext()) {
			l = i.next();
			if (l.getPostId() == post) {
				count += l.getThumb();
			}
		}
		return count;
	}

}
