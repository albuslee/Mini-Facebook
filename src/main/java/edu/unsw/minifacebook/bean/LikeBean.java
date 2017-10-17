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


public class LikeBean {

	private int id;
	

	private UserBean likefrom;
	
	public int getLike_from_id() {
		return like_from_id;
	}

	public void setLike_from_id(int like_from_id) {
		this.like_from_id = like_from_id;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	private int like_from_id;
	

	private PostBean post;
	
	private int postid;
	
	private int thumb;
	
	public void setId(int id) {
		this.id = id;
	}

	public void setLikefrom(UserBean likefrom) {
		this.likefrom = likefrom;
	}

	public void setPost(PostBean post) {
		this.post = post;
	}
	
	public PostBean getPost() {
		return this.getPost();
	}

	public void setThumb(int thumb) {
		this.thumb = thumb;
	}
	
	public int getId() {
		return id;
	}

	public UserBean getLikeFrom() {
		return likefrom;
	}



	public int getThumb() {
		return thumb;
	}

}
