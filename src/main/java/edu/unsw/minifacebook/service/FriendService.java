package edu.unsw.minifacebook.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.DAO.FriendDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.DetailForm;

@Service
@Transactional
public class FriendService {
	@Autowired
	private DetailDAO detailDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private FriendDAO friendDAO;
	
	
	
	public ArrayList<DetailBean> searchFriendsByName(DetailForm detailForm) throws HibernateError{
		ArrayList<DetailBean> list = new ArrayList<DetailBean>();
		if (detailForm.getName() != null) {
			list = detailDAO.getFriendByname(detailForm.getName());	
		}
		else if (detailForm.getBirthday() != null) {
			list = detailDAO.getFriendByBirthDay(detailForm.getBirthday());
		}
		else if(detailForm.getGender() != null) {
			list = detailDAO.getFriendByGender(detailForm.getGender());
		}
		else if(detailForm.getMajor() != null) {
			list = detailDAO.getFriendByMajor(detailForm.getMajor());
		}
		if (list != null) {
			return list;
		}	
		else {
			return null;
		}

		
	}

	

}
/*

	public DetailBean login(UserForm userForm) throws HibernateException{
		UserBean userBean = 
		userDao.getUserByUsername(userForm.getUsername());
		if(userBean.getPassword().equals(MD5Util.getMD5String(userForm.getPassword()))) {
			return detailDao.getUserByUsername(userForm.getUsername());
		}
		return null;
	}
	

}
*/