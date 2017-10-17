package edu.unsw.minifacebook.bean;

import java.util.Date;

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
@Table(name = "graph")
public class graphBean {
	@Column
	private String Subject;

	@Column
	private String Predicate;

	@Column
	private String Object;
	
	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getPredicate() {
		return Predicate;
	}

	public void setPredicate(String predicate) {
		Predicate = predicate;
	}

	public String getObject() {
		return Object;
	}

	public void setObject(String object) {
		Object = object;
	}





}
