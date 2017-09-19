package edu.unsw.minifacebook.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Friend")
public class FriendBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//TODO: contains a pair of user id, the smaller id is always before the bigger one
}
