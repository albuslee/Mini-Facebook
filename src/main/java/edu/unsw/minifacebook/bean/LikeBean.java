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
@Table(name = "Like")
public class LikeBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	// Like from USER
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Like_from", referencedColumnName = "userid", unique = true)
	private UserBean like_from;
	
	// Like to USER
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Like_to", referencedColumnName = "userid", unique = true)
	private UserBean like_to;
	
	@Column(name = "THUMB")
	private int thumb;
	
	public void setId(int id) {
		this.id = id;
	}

	public void setLikeFrom(UserBean like_from) {
		this.like_from = like_from;
	}

	public void setLikeTo(UserBean like_to) {
		this.like_to = like_to;
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

	public UserBean getLikeTo() {
		return like_to;
	}

	public int getThumb() {
		return thumb;
	}

}
