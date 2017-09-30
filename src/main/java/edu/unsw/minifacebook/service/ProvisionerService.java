package edu.unsw.minifacebook.service;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unsw.minifacebook.DAO.ProvisionerDAO;
import edu.unsw.minifacebook.bean.Provisioner;
import edu.unsw.minifacebook.forms.UserForm;;

@Service
@Transactional
public class ProvisionerService {
	@Autowired
	private ProvisionerDAO pDao;


	public boolean login(UserForm userForm) throws HibernateException {
		Provisioner p = pDao.getUserByUsername(userForm.getUsername());
		if (p.getPassword().equals(userForm.getPassword())) {
			return true;
		} else
			return false;
	}

}
