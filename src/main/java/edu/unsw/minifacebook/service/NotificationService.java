package edu.unsw.minifacebook.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

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
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		UserBean userBean = (UserBean) request.getSession().getAttribute("userbean");
		List<NotificationBean> notificationList = NotificationDao.getNotificationByUserBean(userBean);
		for(NotificationBean request2: notificationList) {
			DetailBean db2 = detailDao.getUserByUsername(request2.getFrom2().getUsername());
			request2.getFrom2().setDetailBean(db2);
		}
		return notificationList;
	}
	
	public void sendAddFriendRequestNotification(String username, DetailBean currentUser) {
		UserBean userBean = userDAO.getUserByUsername(username);
		DetailBean detailBean = detailDao.getUserByUsername(username);
		UserBean rfrom = userDAO.getUserByUsername(currentUser.getUsername());
		NotificationBean nb = new NotificationBean();
		nb.setType("friend");
		nb.setFrom2(rfrom);
		nb.setuserBean(userBean);
    	Emailer.sendMail(currentUser.getEmail(), detailBean.getName()+ " wants to add you as friend\n", "http://localhost:8080/mini_facebook/acceptFriendRequest?username="+rfrom.getUsername());
		NotificationDao.insertNotificationByUserBean(userBean, nb);
		
	}
	
	public List<NotificationBean> loadAddFriendRequest(String username) {
		UserBean userBean = userDAO.getUserByUsername(username);
		List<NotificationBean> result = NotificationDao.getFriendNotificationByUserBean(userBean);
		for(NotificationBean request: result) {
			DetailBean db = detailDao.getUserByUsername(request.getFrom2().getUsername());
			request.getFrom2().setDetailBean(db);
		}
		return result;
	}
	
}

