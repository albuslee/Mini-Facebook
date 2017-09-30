package edu.unsw.minifacebook.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.DAO.NotificationDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.bean.CommentBean;
import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.NotificationForm;
import edu.unsw.minifacebook.util.Emailer;
import edu.unsw.minifacebook.util.MD5Util;


@Service
@Transactional
public class NotificationService {
	@Autowired
	private NotificationDAO NotificationDao;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private DetailDAO detailDao;
	
	public List<NotificationBean> getNotificationList(String userName) throws HibernateException{
		UserBean userBean = userDAO.getUserByUsername(userName);
		List<NotificationBean> notificationList = NotificationDao.getNotificationByUserBean(userBean);
		return notificationList;
	}
	
	public void sendAddFriendRequestNotification(String username, DetailBean currentUser) {
		UserBean userBean = userDAO.getUserByUsername(username);
		DetailBean detailBean = detailDao.getUserByUsername(username);
		UserBean rfrom = userDAO.getUserByUsername(currentUser.getUsername());
		NotificationBean nb = new NotificationBean();
		nb.setType("friend");
		nb.setFrom(rfrom);
		nb.setuserBean(userBean);
		
		NotificationDao.insertNotificationByUserBean(userBean, nb);
	}
	
	public List<NotificationBean> loadAddFriendRequest(String username) {
		UserBean userBean = userDAO.getUserByUsername(username);
		List<NotificationBean> result = NotificationDao.getFriendNotificationByUserBean(userBean);
		for(NotificationBean request: result) {
			DetailBean db = detailDao.getUserByUsername(request.getFrom().getUsername());
			request.getFrom().setDetailBean(db);
		}
		return result;
	}
	
}

