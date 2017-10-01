package edu.unsw.minifacebook.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.LikeBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.service.FriendService;
import edu.unsw.minifacebook.service.LikeService;
import edu.unsw.minifacebook.DAO.LikeDAO;

public class LikeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	//private LikeDAO likeDao;
	
	@Autowired
	private LikeService likeService;

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
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String username = ActionContext.getContext().getSession().get("User").toString();
		int postid = Integer.parseInt(request.getParameter("postid"));
        String thumb = ActionContext.getContext().getSession().get("Thumb").toString();
        if(username != null) {
        		System.out.println(username);
        		System.out.println(postid);
        		//LikeDAO likeDao = new LikeDAO();
        		int t = Integer.parseInt(thumb);
        		likeService.addLikes(username, postid, t);
        		return SUCCESS;
        }else {
        	System.out.println("FAILED");
        	return ERROR;
        }
	}

}
