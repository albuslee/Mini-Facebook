package edu.unsw.minifacebook.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.LikeBean;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.NotificationForm;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.NotificationService;
import edu.unsw.minifacebook.service.UserService;

public class LikeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private UserForm userform;
	private NotificationForm notificationForm;

	@Autowired
	private UserService userService;
	@Autowired
	private NotificationService notificationService;

	public UserForm getUserform() {
		return userform;
	}

	public void setUserform(UserForm userform) {
		this.userform = userform;
	}
	
	public NotificationForm getNotificationform() {
		return notificationForm;
	}

	public void setNotificationform(NotificationForm notificationForm) {
		this.notificationForm = notificationForm;
	}

	public String execute() {
		try {
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST); 
            UserBean userBean=(UserBean) request.getSession().getAttribute("User");
            PostBean postBean=(PostBean) request.getSession().getAttribute("Post");
            String thumb = request.getSession().getAttribute("Thumb").toString();
			if (userBean != null) {
//				ActionContext.getContext().getSession().put("detailbean", detailBean);
//				ActionContext.getContext().getSession().put("notificationList", notificationList);
				System.out.println(thumb);
				return SUCCESS;
			} else
				return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String addLikes() {
		UserBean userBean=(UserBean) ActionContext.getContext().getSession().get("User");
        PostBean postBean=(PostBean) ActionContext.getContext().getSession().get("Post");
        String thumb = ActionContext.getContext().getSession().get("Thumb").toString();
        if(userBean != null) {
        		System.out.println(thumb);
        		return SUCCESS;
        }else {
        	System.out.println("FAILED");
        	return ERROR;
        }
	}

}
