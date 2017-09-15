package edu.unsw.minifacebook.service;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.UserForm;

@Service
public class UserService {
	@Autowired
	private UserDAO userDao;
	
	public boolean register(UserForm userForm) throws HibernateException{
		UserBean userBean =new UserBean();
		BeanUtils.copyProperties(userForm, userBean);
		if(userDao.ifUsernameExisted(userBean.getUsername())){
			return false;
		}else {
			userDao.saveObject(userBean);
			return true;
		}
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
