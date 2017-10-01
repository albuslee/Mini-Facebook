package edu.unsw.minifacebook.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.spi.ErrorCode;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.DAO.NotificationDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.DetailForm;
import edu.unsw.minifacebook.service.FriendService;
import edu.unsw.minifacebook.service.NotificationService;
import edu.unsw.minifacebook.util.Emailer;

public class FriendAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private DetailForm detailform;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private NotificationService nservice;
	
	@Autowired
	private NotificationDAO NotificationDao;
	
	@Autowired
	private UserDAO UserDAO;
	
	public DetailForm getDetailform() {
		return detailform;
	}
	
	public void setDetailform(DetailForm detailform) {
		this.detailform = detailform;
	}
	
	public String execute() {
		List<DetailBean> list = new ArrayList<DetailBean>();
		try {
			list = friendService.searchFriendsByName(detailform);
			if (list != null) {
				ActionContext.getContext().getSession().put("friendlist", list);
				return SUCCESS;
				}
			else 
				return ERROR;
		}catch(Exception e) {
				e.printStackTrace();
				return ERROR;
			}
	}
	
	public String sendFriendRequest() {
        HttpServletRequest request = ServletActionContext.getRequest(); 
        DetailBean currentUser = (DetailBean) ActionContext.getContext().getSession().get("detailbean");
        String username = request.getParameter("username");
        
        if(currentUser != null) {

        	nservice.sendAddFriendRequestNotification(username, currentUser);
        	return SUCCESS;
        }else {
        	return LOGIN;
        }
	}
	
	public String loadFriendRequest() {
		HttpServletRequest request = ServletActionContext.getRequest(); 
        DetailBean currentUser = (DetailBean) ActionContext.getContext().getSession().get("detailbean");
        if(currentUser != null) {
        	List<NotificationBean> notifications = nservice.loadAddFriendRequest(currentUser.getUsername());
        	request.setAttribute("friendrequests", notifications);
        	return SUCCESS;
        }else {
        	return LOGIN;
        }
	}
	
	public String acceptFriendRequest() {
		HttpServletRequest request = ServletActionContext.getRequest(); 
        DetailBean currentUser = (DetailBean) ActionContext.getContext().getSession().get("detailbean");
        String username = request.getParameter("username");
        if(currentUser != null) {
        	friendService.addFriends(username, currentUser.getUsername());
        	List<NotificationBean> notifications = nservice.loadAddFriendRequest(currentUser.getUsername());
        	request.setAttribute("friendrequests", notifications);
        	
        	//add acceptfriendnotification
        	UserBean userBean = UserDAO.getUserByUsername(username);
        	UserBean rfrom = UserDAO.getUserByUsername(currentUser.getUsername());
        	NotificationBean nb = new NotificationBean();
    		nb.setType("accept");
    		nb.setFrom2(rfrom);
    		nb.setuserBean(userBean);
    		NotificationDao.insertNotificationByUserBean(userBean, nb);
        	
        	return SUCCESS;
        }else {
        	return ERROR;
        }
	}

}


