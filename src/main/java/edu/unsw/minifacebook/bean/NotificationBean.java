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
import java.util.Date;

@Entity
@Table(name = "Notification")
public class NotificationBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "commented_by", referencedColumnName="userid",unique=true)
	private UserBean commented_by;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "commented_record", referencedColumnName="userid",unique=true)
	private UserBean commented_record;
	
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

	public UserBean getcommented_record() {
		return commented_record;
	}

	public void setcommented_record(UserBean commented_record) {
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
