package edu.unsw.minifacebook.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class CommentBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//TODO: a status field indicates the comment is a sub-comment or root comment
	
	//TODO: father comment id
}
