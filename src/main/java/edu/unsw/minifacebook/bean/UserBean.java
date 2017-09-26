package edu.unsw.minifacebook.bean;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class UserBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;

	@Column(nullable=false, unique= true)
	private String username;
	@Column
	private String password;
	@Column
	private String gender;
	@Column
	private String email;
	@Column
	private Integer state = 0;
	@Column
	private String validateCode;
	@Column
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		return userid;
	}

	public void setUserId(Integer id) {
		this.userid = id;
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
}
