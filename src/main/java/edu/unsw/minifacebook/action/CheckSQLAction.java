package edu.unsw.minifacebook.action;
import java.io.File;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;  
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;  
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.bean.DetailBean;  
 
public class CheckSQLAction extends ActionSupport{
	
	public String execute() {
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);   
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST); 
		String user=request.getParameter("username");
	    String responseText ="    usernme exists!"; 
	    try {
			response.getWriter().println(responseText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    return "success";
	}
}  