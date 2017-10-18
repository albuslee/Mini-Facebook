package edu.unsw.minifacebook.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.spi.ErrorCode;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.DAO.UserGRDAO;
import edu.unsw.minifacebook.bean.GraphBean;

public class graphSearch extends ActionSupport{
	
	@Autowired
	private UserGRDAO graphDao;
	
	public String execute() {
		List<GraphBean> list = new ArrayList<GraphBean>();
		try {
			list = graphDao.getgraphByRequest("");
			ArrayList<GraphBean> graphlist = new ArrayList<GraphBean>();
			JSONObject json = new JSONObject();
			JSONArray  jsonArray=new JSONArray();
			for(int i = 0; i < list.size(); i ++){
				GraphBean graphBean = list.get(i);
				json.put("source", graphBean.getSubject());
				json.put("name", graphBean.getPredicate());
				json.put("target", graphBean.getObject());
				jsonArray.put(json);
			}
			if (list != null) {
				ActionContext.getContext().getSession().put("graphlist", jsonArray.toString());
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


