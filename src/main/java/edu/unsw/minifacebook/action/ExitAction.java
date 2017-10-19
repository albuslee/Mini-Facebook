package edu.unsw.minifacebook.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ExitAction extends ActionSupport{
   
@Override
   
public String execute() throws Exception {
   
HttpServletRequest request = ServletActionContext.getRequest(); 
HttpSession session1 = request.getSession();  
session1.invalidate();  
return super.execute();
}
}