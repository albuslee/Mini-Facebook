package edu.unsw.minifacebook.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.UserService;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.service.NotificationService;
import edu.unsw.minifacebook.forms.NotificationForm;

public class RefreshNotification extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private NotificationForm notificationForm;

	@Autowired
	private NotificationService notificationService;

	
	public NotificationForm getNotificationform() {
		return notificationForm;
	}

	public void setNotificationform(NotificationForm notificationForm) {
		this.notificationForm = notificationForm;
	}

	public String execute() {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
					.get(ServletActionContext.HTTP_REQUEST);
			String username=(String) request.getSession().getAttribute("notificationusername");
			List<NotificationBean> notificationList = notificationService.getNotificationList(username);
			ActionContext.getContext().getSession().put("notificationList", notificationList);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
