package edu.unsw.minifacebook.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unsw.minifacebook.bean.LikeBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;

public class LikeDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	public void saveObject(Object obj) throws HibernateException{
		this.getCurrentSession().save(obj);
	}
	
	public int addLikes(UserBean like_from, PostBean post, int thumb) {
		LikeBean likeBean = new LikeBean();
		likeBean.setLikeFrom(like_from);
		likeBean.setPostId(post);
		likeBean.setThumb(thumb);
		this.getCurrentSession().save(likeBean);
		return likeBean.getId();
	}
	
	public void deleteLikes(int id) {
		try {
			Transaction trn = this.getCurrentSession().beginTransaction();
			LikeBean unlikeBean = (LikeBean) this.getCurrentSession().get(LikeBean.class, id);
			this.getCurrentSession().delete(unlikeBean);
			trn.commit();
			this.getCurrentSession().close();
		} catch (HibernateException e) {
			e.printStackTrace();
			this.getCurrentSession().getTransaction().rollback();
		}

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
