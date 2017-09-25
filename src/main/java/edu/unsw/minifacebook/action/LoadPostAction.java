package edu.unsw.minifacebook.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.service.PostService;

//load all friends' posts and comments
public class LoadPostAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {

}