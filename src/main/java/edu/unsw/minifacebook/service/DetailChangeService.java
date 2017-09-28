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
	@Autowired
	private UserDAO userdao;
	
	public boolean nomalchange(DetailForm detailForm) throws HibernateException{
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		DetailBean detailBean =(DetailBean)request.getSession().getAttribute("detailbean");
		UserBean userbean=userdao.getUserByUsername(detailBean.getUsername());
		if (detailForm.getName()!= "" && detailForm.getName()!= null){detailBean.setName(detailForm.getName());}
		if (detailForm.getAge()!= "" && detailForm.getAge()!= null){detailBean.setAge(detailForm.getAge());}
		if (detailForm.getBirthday()!= "" && detailForm.getBirthday()!= null){detailBean.setBirthday(detailForm.getBirthday());}
		if (detailForm.getMajor()!= "" && detailForm.getMajor()!= null){detailBean.setMajor(detailForm.getMajor());}
		if (detailForm.getGender()!= "" && detailForm.getGender()!= null){detailBean.setGender(detailForm.getGender());}
		if (detailForm.getPassword()!= null){detailBean.setPassword(detailForm.getPassword());userbean.setPassword(MD5Util.getMD5String(detailForm.getPassword()));
		Emailer.sendMail(userbean.getEmail(),"UNSWbook password changed","password changed");}
		if (detailForm.getEmail()!= null){Emailer.sendMail(userbean.getEmail(),"email changed","email changed");
		detailBean.setEmail(detailForm.getEmail());userbean.setEmail(detailForm.getEmail());
		Emailer.sendMail(userbean.getEmail(),"UNSWbook email changed","email changed");
		}
		detailDao.updateObject(detailBean);
		userdao.updateObject(userbean);
		ActionContext.getContext().getSession().put("detailbean", detailBean);
		return true;
		}

	}
