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
			if(result) {
<<<<<<< HEAD
				ActionContext.getContext().getSession().put("currentuser", userform.getUsername());
=======
				DetailBean detailBean = 
						detailDao.getUserByUsername(userform.getUsername());
				ActionContext.getContext().getSession().put("username", userform.getUsername());
				ActionContext.getContext().getSession().put("age", detailBean.getAge());
				ActionContext.getContext().getSession().put("name", detailBean.getName());
				ActionContext.getContext().getSession().put("birthday", detailBean.getBirthday());
				ActionContext.getContext().getSession().put("major", detailBean.getMajor());
				ActionContext.getContext().getSession().put("gender", detailBean.getGender());
>>>>>>> 2854ffa47746fbfd1c523957d5869b67aed8da40
				return SUCCESS;
			}else
				return ERROR;
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	

}
