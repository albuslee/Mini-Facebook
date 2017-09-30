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
@Table(name = "Likes")
public class LikeBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// Like from USER
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Like_from", referencedColumnName = "username")
	private UserBean like_from;
	
	// which post
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Post", referencedColumnName = "id")
	private PostBean post;
	
	@Column(name = "THUMB")
	private int thumb;
	
	public void setId(int id) {
		this.id = id;
	}

	public void setLikeFrom(UserBean like_from) {
		this.like_from = like_from;
	}

	public void setPostId(PostBean post) {
		this.post = post;
	}

	public void setThumb(int thumb) {
		this.thumb = thumb;
	}
	
	public int getId() {
		return id;
	}

	public UserBean getLikeFrom() {
		return like_from;
	}

	public PostBean getPostId() {
		return post;
	}

	public int getThumb() {
		return thumb;
	}

}
