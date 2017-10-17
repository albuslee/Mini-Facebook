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

	private int id;
	
	private UserBean userBean;
	
	private Integer userid;
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getFromid() {
		return fromid;
	}

	public void setFromid(Integer fromid) {
		this.fromid = fromid;
	}

	private Integer fromid;
	
	private UserBean from2;
	
	public UserBean getFrom2() {
		return from2;
	}

	public void setFrom2(UserBean from) {
		this.from2 = from;
	}

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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public String getCommented_record() {
		return commented_record;
	}

	public void setCommented_record(String commented_record) {
		this.commented_record = commented_record;
	}

	public Date getComment_time() {
		return comment_time;
	}

	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	
	public String getNotification_status() {
		return notification_status;
	}

	public void setNotification_status(String notification_status) {
		this.notification_status = notification_status;
	}

	//TODO: status : read, unread
}
