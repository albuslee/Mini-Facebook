package edu.unsw.minifacebook.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Friend")
public class FriendBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="firstuser",referencedColumnName="firstid",unique=true) 
	private UserBean firstUser;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="seconduser",referencedColumnName="secondid",unique=true) 
	private UserBean secoundUser;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public UserBean getFirstUser() {
		return firstUser;
	}


	public void setFirstUser(UserBean firstUser) {
		this.firstUser = firstUser;
	}


	public UserBean getSecoundUser() {
		return secoundUser;
	}


	public void setSecoundUser(UserBean secoundUser) {
		this.secoundUser = secoundUser;
	}
}
