package edu.unsw.minifacebook.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.DetailForm;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.UserService;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private UserForm userform;
	private DetailDAO detailDao;

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
			boolean result = userService.login(userform);
			if (result) {
				DetailBean detailBean = detailDao.getUserByUsername(userform.getUsername());
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
