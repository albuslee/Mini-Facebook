package edu.unsw.minifacebook.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.forms.PostForm;
import edu.unsw.minifacebook.service.PostService;
import edu.unsw.minifacebook.service.UserService;

public class PostAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	// private UserForm userform;

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	
	private PostForm postform;

	public PostForm getPostform() {
		return postform;
	}


	public void setPostform(PostForm postform) {
		this.postform = postform;
	}


	public String loadposts() {
		try {
			String currentUsername = (String) ActionContext.getContext().getSession().get("username");
			List<PostBean> postList = postService.loadFriendPosts(currentUsername);
			request.put("postlist", postList);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	
	public String addposts() {
		try {

			DetailBean detailBean = (DetailBean) ActionContext.getContext().getSession().get("detailbean");
			postService.createNewPost(detailBean.getUsername(), postform);
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
