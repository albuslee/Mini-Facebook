package edu.unsw.minifacebook.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.DAO.FriendDAO;
import edu.unsw.minifacebook.DAO.PostDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.util.Emailer;
import edu.unsw.minifacebook.util.MD5Util;; 

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private DetailDAO detailDao;
	
	@Autowired 
	private FriendDAO friendDao;
	
	@Autowired
	private PostDAO postDao;
	
	public boolean checkexist(String username){
		if(userDao.ifUsernameExisted(username)){
			return false;
		}else {
			return true;
		}
	}
	
	public boolean register(UserForm userForm) throws HibernateException{
		UserBean userBean =new UserBean();
		DetailBean detailBean =new DetailBean();
		userForm.setPassword(MD5Util.getMD5String(userForm.getPassword()));
		userForm.setValidateCode(MD5Util.getMD5String(userForm.getUsername()));
		Emailer.sendMail(userForm.getEmail(),"verify your Email","http://localhost:8080/mini_facebook/verify?username="+userForm.getUsername()+"&code="+userForm.getValidateCode());
		BeanUtils.copyProperties(userForm, userBean);
		BeanUtils.copyProperties(userForm, detailBean);
		userBean.setRegistTime();
		if(userDao.ifUsernameExisted(userBean.getUsername())){
			return false;
		}else {
			userDao.saveObject(userBean);
			detailDao.saveObject(detailBean);
			return true;
		}
	}
	
	public boolean verification(String usr,String code) throws HibernateException{
		UserBean userBean =userDao.getUserByUsername(usr);
		Date currentTime=new Date();
		if(userBean.getValidateCode().equals(code) && currentTime.before(userBean.getRegistTime())) {
			userBean.setState(1);
			userDao.updateObject(userBean);
			return true;
		}
		return false;
	}

	
	public DetailBean login(UserForm userForm) throws HibernateException{
		UserBean userBean = 
		userDao.getUserByUsername(userForm.getUsername());
		if(userBean.getPassword().equals(MD5Util.getMD5String(userForm.getPassword())) && userBean.getState()==1) {
			return detailDao.getUserByUsername(userForm.getUsername());
		}
		return null;
	}
	
	public UserBean getUserbean(UserForm userForm) throws HibernateException{
		UserBean userBean = 
		userDao.getUserByUsername(userForm.getUsername());
		if(userBean.getPassword().equals(MD5Util.getMD5String(userForm.getPassword())) && userBean.getState()==1) {
			return userBean;
		}
		return null;
	}
	
	
	public List<UserBean> loadAllUsers(){
		List<UserBean> result = userDao.getAllUsers();
		if(result != null && !result.isEmpty()) {
			for(UserBean ub: result) {
				DetailBean db = detailDao.getUserByUsername(ub.getUsername());
				ub.setDetailBean(db);
			}
		}
		return result;
	}
	
	
	public boolean banUser(String username) {
		UserBean ub = userDao.getUserByUsername(username);
		if(ub!= null) {
			ub.setState(-1);
			userDao.updateObject(ub);
		}
		
		return true;
	}
	
	public Map<Date, String> loadUserActivity(String username) {
	     Map<Date, String> result = new TreeMap<Date, String>(
	                new Comparator<Date>() {
	                    public int compare(Date obj1, Date obj2) {
	                        // Ωµ–Ú≈≈–Ú
	                        return obj2.compareTo(obj1);
	                    }
	                });
		UserBean ub = userDao.getUserByUsername(username);
		DetailBean db = detailDao.getUserByUsername(username);
		ub.setDetailBean(db);
		result.put(ub.getRegistTime(), db.getName() + "joined UNSWBook");
		result.putAll(friendDao.getFriendActivity(ub.getUserId()));
		result.putAll(postDao.getPostActivity(ub.getUserId()));
		return result;
	}
	
	public DetailBean loadUserDetail(String username) {
		return detailDao.getUserByUsername(username);
	}

}
