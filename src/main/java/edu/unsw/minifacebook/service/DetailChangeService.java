package edu.unsw.minifacebook.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.DetailForm;
import edu.unsw.minifacebook.forms.UserForm;
import edu.unsw.minifacebook.util.Emailer;
import edu.unsw.minifacebook.util.MD5Util;

@Service
public class DetailChangeService {
	@Autowired
	private DetailDAO detailDao;
	
	public boolean nomalchange(DetailForm detailForm) throws HibernateException{
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		DetailBean detailBean =(DetailBean)request.getSession().getAttribute("detailbean");
		BeanUtils.copyProperties(detailForm, detailBean);
		detailDao.updateObject(detailBean);
		return true;
		}
	}
