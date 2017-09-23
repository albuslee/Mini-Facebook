package edu.unsw.minifacebook.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.service.PostService;

//load all friends' posts and comments
public class LoadPostAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	// private UserForm userform;

	@Autowired
	private PostService postServices;

	public String execute() {
		try {
			String currentUsername = (String) ActionContext.getContext().getSession().get("username");
			List<PostBean> postList = postServices.loadFriendPosts(currentUsername);
			request.put("postlist", postList);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
