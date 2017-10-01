package edu.unsw.minifacebook.DAO;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;

@Repository
public class PostDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public void saveObject(Object obj) throws HibernateException {
		this.getCurrentSession().save(obj);
	}

	public List<PostBean> getPostsByUserlist(List<Integer> userList) {
		if(userList == null || userList.isEmpty()) return null;
		
		List<PostBean> postList = null;
		
		Query query2 = getCurrentSession().createQuery
				("from PostBean where creator.id in (:ids) order by posttime desc").setParameterList("ids", userList);
		
		postList = query2.getResultList();
		
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
