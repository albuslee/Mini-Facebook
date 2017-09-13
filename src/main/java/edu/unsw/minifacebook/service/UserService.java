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
	
	public void register(UserForm userForm) throws HibernateException{
		UserBean user =new UserBean();
		BeanUtils.copyProperties(userForm, user);
		userDao.saveObject(user);
	}
}
