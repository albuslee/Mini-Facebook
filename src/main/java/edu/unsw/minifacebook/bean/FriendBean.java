package edu.unsw.minifacebook.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

	
	@ManyToOne
	@JoinColumn(name="firstuser",referencedColumnName="userid") 
	private UserBean firstUser;
	
	
	@ManyToOne
	@JoinColumn(name="seconduser",referencedColumnName="userid") 
	private UserBean secoundUser;
	
	@Column
	private Date friendtime;


	public Date getFriendtime() {
		return friendtime;
	}


	public void setFriendtime(Date friendtime) {
		this.friendtime = friendtime;
	}


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
