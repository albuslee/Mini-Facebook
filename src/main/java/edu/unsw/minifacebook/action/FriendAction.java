package edu.unsw.minifacebook.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.spi.ErrorCode;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.NotificationBean;
import edu.unsw.minifacebook.forms.DetailForm;
import edu.unsw.minifacebook.service.FriendService;
import edu.unsw.minifacebook.service.NotificationService;

public class FriendAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private DetailForm detailform;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private NotificationService nservice;
	
	public DetailForm getDetailform() {
		return detailform;
	}
	
	public void setDetailform(DetailForm detailform) {
		this.detailform = detailform;
	}
	
	public String execute() {
		List<DetailBean> list = new ArrayList<DetailBean>();
		try {
			list = friendService.searchFriendsByName(detailform);
			if (list != null) {
				ActionContext.getContext().getSession().put("friendlist", list);
				return SUCCESS;
				}
			else 
				return ERROR;
		}catch(Exception e) {
				e.printStackTrace();
				return ERROR;
			}
	}
	
	public String sendFriendRequest() {
        HttpServletRequest request = ServletActionContext.getRequest(); 
        DetailBean currentUse = (DetailBean) ActionContext.getContext().getSession().get("detailbean");
        String username = request.getParameter("username");
        if(currentUse != null) {
        	nservice.sendAddFriendRequestNotification(username, currentUse);
        	return SUCCESS;
        }else {
        	return ERROR;
        }
	}
	
	public String loadFriendRequest() {
		HttpServletRequest request = ServletActionContext.getRequest(); 
        DetailBean currentUser = (DetailBean) ActionContext.getContext().getSession().get("detailbean");
        if(currentUser != null) {
        	List<NotificationBean> notifications = nservice.loadAddFriendRequest(currentUser.getUsername());
        	request.setAttribute("friendrequests", notifications);
        	return SUCCESS;
        }else {
        	return ERROR;
        }
	}

}

