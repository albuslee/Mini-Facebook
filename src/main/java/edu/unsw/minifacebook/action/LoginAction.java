package edu.unsw.minifacebook.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.UserService;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.service.NotificationService;
import edu.unsw.minifacebook.forms.NotificationForm;

public class LoginAction extends ActionSupport {
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
			DetailBean detailBean = userService.login(userform);
			List<NotificationBean> notificationList = notificationService.getNotificationList(userform.getUsername());
			if (detailBean != null) {
				ActionContext.getContext().getSession().put("detailbean", detailBean);
				ActionContext.getContext().getSession().put("notificationList", notificationList);
				//ActionContext.getContext().getSession().put("notificationusername", userform.getUsername());
				return SUCCESS;
			} else
				return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
