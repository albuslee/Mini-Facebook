package edu.unsw.minifacebook.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Notification")
public class NotificationBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade= CascadeType.MERGE)
	@JoinColumn(name = "userBean", referencedColumnName="userid")
	private UserBean userBean;
	
	
	@ManyToOne(cascade= CascadeType.MERGE)
	@JoinColumn(name = "nfrom", referencedColumnName="userid")
	private UserBean from2;
	
	public UserBean getFrom2() {
		return from2;
	}

	public void setFrom2(UserBean from) {
		this.from2 = from;
	}

	@Column
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column
	private String commented_record;
	
	@Column
	private Date comment_time;
	
	@Column
	private String notification_status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserBean getuserBean() {
		return userBean;
	}

	public void setuserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public String getcommented_record() {
		return commented_record;
	}

	public void setcommented_record(String commented_record) {
		this.commented_record = commented_record;
	}

	public Date getcomment_time() {
		return comment_time;
	}

	public void setcomment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	
	public String getnotification_status() {
		return notification_status;
	}

	public void setnotification_status(String notification_status) {
		this.notification_status = notification_status;
	}

	//TODO: status : read, unread
}
