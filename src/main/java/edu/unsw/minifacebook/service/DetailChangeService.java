package edu.unsw.minifacebook.service;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.util.Emailer;
import edu.unsw.minifacebook.util.MD5Util;

@Service
public class DetailChangeService {
	@Autowired
	private UserDAO userDao;
	
	public boolean register(UserForm userForm) throws HibernateException{
		UserBean userBean =new UserBean();
		userForm.setValidateCode(MD5Util.getMD5String(userForm.getUsername()));
		Emailer.sendMail(userForm.getEmail(),"verify your Email","http://localhost:8080/mini_facebook/verify?username="+userForm.getUsername()+"&code="+userForm.getValidateCode());
		BeanUtils.copyProperties(userForm, userBean);
		userBean.setRegistTime();
		if(userDao.ifUsernameExisted(userBean.getUsername())){
			return false;
		}else {
			userDao.saveObject(userBean);
			return true;
		}
	}
}
