package edu.unsw.minifacebook.DAO;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.bean.FriendBean;
import edu.unsw.minifacebook.bean.UserBean;

@Repository
public class FriendDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDao;
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	public void saveObject(Object obj) throws HibernateException{
		this.getCurrentSession().save(obj);
	}
	
	public void addFriends(UserBean userBean1, UserBean userBean2) {
		if(userBean1.getUserId() > userBean2.getUserId()) {
			UserBean temp = userBean1;
			userBean1 = userBean2;
			userBean2 = temp;
		}
		FriendBean friendBean = new FriendBean();
		friendBean.setFirstUser(userBean1);
		friendBean.setSecoundUser(userBean2);
		if(!existed(userBean1.getUserId(), userBean2.getUserId()))
			this.getCurrentSession().save(friendBean);
		
	}
	
	public boolean existed(int user1, int user2) {
		if(user1 > user2) {
			int temp = user1;
			user1 = user2;
			user2 = temp;
		}
		Query query1 = getCurrentSession().createQuery
				("from FriendBean where firstuser = :user1 and seconduser = :user2").setParameter("user1", user1)
				.setParameter("user2", user2);
		try {
			query1.getSingleResult();
		}catch(NoResultException nre) {
			return false;
		}
		return true;
	}
	

	public List<UserBean> getAllFriendsByUserid(Integer userId){
		List<UserBean> userBeanList = null;
		List<Integer> userIdList = this.getAllFriendIdsByUserid(userId);
		for(int i = 0; i < userIdList.size(); i++) {
			UserBean userBean = userDao.getUserByUserid(userIdList.get(i));
			userBeanList.add(userBean);
		}
		return userBeanList;
	}
	
	public List<Integer> getAllFriendIdsByUsername(String username){
		List<Integer> userBeanList = new ArrayList<Integer>();
		
//		Query query2 = getCurrentSession().createQuery
//				("from Friend where firstuser.id = (:id)").setParameter("id", username);
//		
//		postList = query2.getResultList();
//		userBeanList.add(1);
		return userBeanList;
	}
	
	
	
	public List<Integer> getAllFriendIdsByUserid(Integer userId){
		List<Integer> result = new ArrayList<Integer>();
		List<FriendBean> friendBeanList = new ArrayList<FriendBean>();
		Query query1 = getCurrentSession().createQuery
				("from FriendBean where seconduser = (:id)").setParameter("id", userId);
		Query query2 = getCurrentSession().createQuery
				("from FriendBean where firstuser = (:id)").setParameter("id", userId);
		friendBeanList = query1.getResultList();
		for(FriendBean fb: friendBeanList) {
			result.add(fb.getFirstUser().getUserId());
		}
		List<FriendBean> tempList2 = query2.getResultList();
		for(FriendBean fb: tempList2) {
			result.add(fb.getSecoundUser().getUserId());
		}
		return result;
	}
}
