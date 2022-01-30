package com.skilldistillery.honeytrails.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="group_hike_comment")
public class GroupHikeComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="comment_box")
	private String commentBox;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name="reply_to_id")
	private GroupHikeComment inReplyTo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_hike_id")
	private GroupHike groupHike;
	
	@OneToMany(mappedBy="inReplyTo")
	@JsonIgnore
	private List<GroupHikeComment> replies;
	
	private boolean hidden;
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Constructor
	 * 
	 -----------------------------------------------------------------------------------------------------*/
	

	public GroupHikeComment() {
		super();
	}
	
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

	public String getCommentBox() {
		return commentBox;
	}

	public void setCommentBox(String commentBox) {
		this.commentBox = commentBox;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public GroupHikeComment getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(GroupHikeComment inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public List<GroupHikeComment> getReplies() {
		return replies;
	}

	public void setReplies(List<GroupHikeComment> replies) {
		this.replies = replies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GroupHike getGroupHike() {
		return groupHike;
	}

	public void setGroupHike(GroupHike groupHike) {
		this.groupHike = groupHike;
	}
	
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *      Hashcode & Equals
	 * 
	 -----------------------------------------------------------------------------------------------------*/

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

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
		GroupHikeComment other = (GroupHikeComment) obj;
		return id == other.id;
	}
	
	
}
