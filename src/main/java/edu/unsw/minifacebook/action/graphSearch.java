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

import edu.unsw.minifacebook.DAO.PostDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.DAO.UserGRDAO;
import edu.unsw.minifacebook.bean.GraphBean;

public class graphSearch extends ActionSupport{
	
	@Autowired
	private UserGRDAO graphDao;
	@Autowired
	private PostDAO postDao;
	
	public String execute() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		String getfield=request.getParameter("choose");
		String gettext=request.getParameter("searchField");
		if (gettext==null){
			gettext="";
		}
		List<GraphBean> list = new ArrayList<GraphBean>();
		try {

			list = graphDao.getgraphByRequest(gettext,getfield);
			ArrayList<GraphBean> graphlist = new ArrayList<GraphBean>();
			JSONArray  jsonArrayLink=new JSONArray();
			for(int i = 0; i < list.size(); i ++){
				JSONObject json = new JSONObject();
				GraphBean graphBean = list.get(i);
				json.put("source", graphBean.getSubject());
				json.put("name", graphBean.getPredicate());
				json.put("target", graphBean.getObject());
				jsonArrayLink.put(json);
			}
		List<GraphBean> list2 = new ArrayList<GraphBean>();
			list2 = graphDao.getEntitiesByRequest("");
			JSONArray  jsonArrayData=new JSONArray();
			for(int i = 0; i < list2.size(); i ++){
				JSONObject json = new JSONObject();
				GraphBean graphBean = list2.get(i);
				json.put("name", graphBean.getSubject());
				json.put("des", graphBean.getObject());
				if (graphBean.getSubject().contains("User")){
					String style = "{normal: {color: red}}"; 
					JSONObject a = new JSONObject(style); 
					json.put("des", graphBean.getObject());
					json.put("itemStyle", a);
					json.put("symbolSize", "50");
				}
				if (graphBean.getSubject().contains("Post")){
					json.put("symbolSize", "30");
					json.put("des", postDao.getPostByid(Integer.parseInt(graphBean.getSubject().replaceAll("PostBean", ""))).getDescription().replaceAll("<p>", "").replaceAll("</p>", ""));
				}
				jsonArrayData.put(json);
			}
			if (list != null) {
				ActionContext.getContext().getSession().put("graphlist", jsonArrayLink.toString());
				ActionContext.getContext().getSession().put("entitylist", jsonArrayData.toString());
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


