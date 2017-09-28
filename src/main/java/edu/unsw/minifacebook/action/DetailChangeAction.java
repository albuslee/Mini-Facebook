package edu.unsw.minifacebook.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.forms.DetailForm;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.service.DetailChangeService;

public class DetailChangeAction extends ActionSupport{
	@Autowired
	private DetailChangeService detailchange;
	
	private DetailForm detailform;
	
	public DetailForm getDetailform() {
		return detailform;
	}
	
	public void setDetailform(DetailForm detailform) {
		this.detailform = detailform;
	}
	
	
	public String execute() {
		try {
			boolean result = detailchange.nomalchange(detailform);
			if (result) {
				return SUCCESS;
			} else
				return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String searchUser() {
        HttpServletResponse response = ServletActionContext.getResponse();  
        HttpServletRequest request = ServletActionContext.getRequest();  
		List<DetailBean> detailList = detailchange.searchUser(detailform);
		ActionContext ctx = ActionContext.getContext();

		ctx.put("detailList", detailList); 
		return SUCCESS;
	}
}
