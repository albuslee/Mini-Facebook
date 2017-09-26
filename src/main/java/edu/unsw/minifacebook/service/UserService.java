package edu.unsw.minifacebook.service;

import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.util.Emailer;
import edu.unsw.minifacebook.util.MD5Util;; 

@Service
public class UserService {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private DetailDAO detailDao;
	
	public boolean register(UserForm userForm) throws HibernateException{
		UserBean userBean =new UserBean();
		DetailBean detailBean =new DetailBean();
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

	
	public boolean login(UserForm userForm) throws HibernateException{
		UserBean userBean = 
		userDao.getUserByUsername(userForm.getUsername());
		if(userBean.getPassword().equals(userForm.getPassword())) {
			return true;
		}
		return false;
	}
}
