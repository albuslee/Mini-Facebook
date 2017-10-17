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

import edu.unsw.minifacebook.DAO.UserGRDAO;
import edu.unsw.minifacebook.bean.graphBean;

public class graphSearch extends ActionSupport{
	
	@Autowired
	private UserGRDAO graphDao;
	
	public String execute() {
		List<graphBean> list = new ArrayList<graphBean>();
		try {
			list = graphDao.getgraphByRequest("");
			if (list != null) {
				ActionContext.getContext().getSession().put("graphlist", list);
				return SUCCESS;
				}
			else 
				return ERROR;
		}catch(Exception e) {
				e.printStackTrace();
				return ERROR;
			}
	}
	
	
}


