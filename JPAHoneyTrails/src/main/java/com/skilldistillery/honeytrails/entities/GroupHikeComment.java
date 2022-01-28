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
//TODO: change name to hike_report_comment after schema change
@Table(name="group_hike_comment")
public class GroupHikeComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="comment_box")
	private String commentBox;
	
	@ManyToOne
	@JoinColumn(name="hike_report_id")
	@JsonIgnore
	private HikeReport hikeReport;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;
	
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name="reply_to_id")
	GroupHikeComment inReplyTo;
	
	@OneToMany(mappedBy="inReplyTo")
	@JsonIgnore
	List<GroupHikeComment> replies;

	public GroupHikeComment() {
		super();
	}

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

	public HikeReport getHikeReport() {
		return hikeReport;
	}

	public void setHikeReport(HikeReport hikeReport) {
		this.hikeReport = hikeReport;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "GroupHikeComment [id=" + id + ", commentBox=" + commentBox + ", hikeReport=" + hikeReport + ", userId="
				+ userId + ", createDate=" + createDate + "]";
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
