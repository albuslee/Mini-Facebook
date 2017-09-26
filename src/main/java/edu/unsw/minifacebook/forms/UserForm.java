package edu.unsw.minifacebook.forms;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;

public class UserForm {
	private String username;
	
	private String password;
	
	private String gender;
	
	private String email;
	
	private Boolean state;
	
	private String validateCode;
	
	private Date registTime;
	
	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime() {
		Date regTime=new Date();
        Calendar cl = Calendar.getInstance();  
        cl.setTime(regTime);  
        cl.add(Calendar.DATE , 1);
        this.registTime=cl.getTime();
	}
	
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
