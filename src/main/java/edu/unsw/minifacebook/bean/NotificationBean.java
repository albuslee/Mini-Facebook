package edu.unsw.minifacebook.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Notification")
public class NotificationBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String commented_record;
	
	@Column
	private String comment_time;
	
	@Column
	private String notification_status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getcommented_record() {
		return commented_record;
	}

	public void setcommented_record(String commented_record) {
		this.commented_record = commented_record;
	}

	public String getcomment_time() {
		return comment_time;
	}

	public void setcomment_time(String comment_time) {
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
