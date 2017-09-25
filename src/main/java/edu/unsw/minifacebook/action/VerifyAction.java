package edu.unsw.minifacebook.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.service.UserService;

//load all friends' posts and comments
public class VerifyAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
    //实现接口中的方法
    public void setServletRequest(HttpServletRequest request){
     this.request = request;
    }

	@Autowired
	private UserService userService;

	public String execute() {
		try {
			String code=(String) request.getParameter("code");
			String usr=(String) request.getParameter("username");
	        boolean result = userService.verification(usr,code);
	        if (result){
	        	return SUCCESS;}
	        else{
	        	return ERROR;
	        }

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void setApplication(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
}
