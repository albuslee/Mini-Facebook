package edu.unsw.minifacebook.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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
		this.getCurrentSession().save(friendBean);
		
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
		List<Integer> userIdList = new ArrayList<Integer>();
		Query query1 = getCurrentSession().createQuery
				("select firstuser.id from Friend where seconduser.id = (:id)").setParameter("id", userId);
		Query query2 = getCurrentSession().createQuery
				("select seconduser.id from Friend where firstuser.id = (:id)").setParameter("id", userId);
		userIdList = query1.getResultList();
		List<Integer> tempList2 = query2.getResultList();
		userIdList.addAll(tempList2);
		return userIdList;
	}
}
