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

@Entity
@Table(name = "Comment")
public class CommentBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	//Comment from USER
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "Reply_from", referencedColumnName="userid",unique=true)
	private UserBean reply_from;
	
	//Comment on USER
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "Reply_to", referencedColumnName="userid",unique=true)
	private UserBean reply_to;
	
	@Column(name = "Content")
	private String content;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setReplyFrom(UserBean reply_from) {
		this.reply_from = reply_from;
	}
	
	public void setReplyTo(UserBean reply_to) {
		this.reply_to = reply_to;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	public UserBean getReplyFrom() {
		return reply_from;
	}
	
	public UserBean getReplyTo() {
		return reply_to;
	}
	
	public String getContent() {
		return content;
	}
}
