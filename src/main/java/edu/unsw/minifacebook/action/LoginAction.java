package edu.unsw.minifacebook.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.UserService;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private UserForm userform;

	@Autowired
	private UserService userService;

	public UserForm getUserform() {
		return userform;
	}

	public void setUserform(UserForm userform) {
		this.userform = userform;
	}

	public String execute() {
		try {
			DetailBean detailBean = userService.login(userform);
			if (detailBean != null) {
				ActionContext.getContext().getSession().put("detailbean", detailBean);

				return SUCCESS;
			} else
				return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
