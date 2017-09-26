package edu.unsw.minifacebook.action;

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
}
