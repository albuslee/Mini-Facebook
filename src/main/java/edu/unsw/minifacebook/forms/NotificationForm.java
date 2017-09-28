package edu.unsw.minifacebook.forms;

import java.util.Date;
import edu.unsw.minifacebook.bean.UserBean;

public class NotificationForm {

	private int id;

	private UserBean commented_by;

	private String commented_record;

	private Date comment_time;

	private String notification_status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public UserBean getcommented_by() {
		return commented_by;
	}

	public void setcommented_by(UserBean commented_by) {
		this.commented_by = commented_by;
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
}