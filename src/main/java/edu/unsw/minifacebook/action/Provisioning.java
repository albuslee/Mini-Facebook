package edu.unsw.minifacebook.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.UserService;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.service.NotificationService;
import edu.unsw.minifacebook.service.ProvisionerService;
import edu.unsw.minifacebook.forms.NotificationForm;

public class Provisioning extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private UserForm userform;

	@Autowired
	private ProvisionerService ps;
	
	@Autowired
	private UserService us;

	public UserForm getUserform() {
		return userform;
	}

	public void setUserform(UserForm userform) {
		this.userform = userform;
	}

	public String adminlogin() {
		try {
			if (ps.login(userform)) {
				List<UserBean> allUsers = us.loadAllUsers();
		        HttpServletRequest request = ServletActionContext.getRequest();  
		        request.setAttribute("allusers", allUsers);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return ERROR;
	}
	

}
