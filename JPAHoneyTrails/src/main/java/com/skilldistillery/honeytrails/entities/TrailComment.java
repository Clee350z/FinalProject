package com.skilldistillery.honeytrails.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trail_comment")
public class TrailComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "comment_body")
	private String commentBody;
	
	@Column(name = "time_posted")
	private LocalDateTime timePosted;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "trail_id")
	private Trail trail;


	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Getters & Setters
	 * 
	 -----------------------------------------------------------------------------------------------------*/
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public LocalDateTime getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(LocalDateTime timePosted) {
		this.timePosted = timePosted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Constructors
	 * 
	 -----------------------------------------------------------------------------------------------------*/
	
	public TrailComment() {}

	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Hashcode & Equals
	 * 
	 -----------------------------------------------------------------------------------------------------*/

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrailComment other = (TrailComment) obj;
		return id == other.id;
	}
	
	
	
	
	
	
}
