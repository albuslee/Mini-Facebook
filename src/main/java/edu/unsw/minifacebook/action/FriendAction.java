package edu.unsw.minifacebook.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.spi.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.DetailForm;
import edu.unsw.minifacebook.service.FriendService;

public class FriendAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private DetailForm detailform;
	
	@Autowired
	private FriendService friendService;
	
	public DetailForm getDetailform() {
		return detailform;
	}
	
	public void setDetailform(DetailForm detailform) {
		this.detailform = detailform;
	}
	
	public String execute() {
		List<DetailBean> list = new ArrayList<DetailBean>();
		try {
			list = friendService.searchFriends(detailform);
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
		
		return SUCCESS;
	}

}


