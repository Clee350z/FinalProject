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
@Table(name = "hike_report_comment")
public class HikeReportComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "comment_box")
	private String commentBox;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@ManyToOne
	@JoinColumn(name="hike_report_id")
	@JsonIgnore
	private HikeReport hikeReportId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User userId;
	
	
	@ManyToOne
	@JoinColumn(name="reply_to_id")
	private HikeReportComment inReplyTo;
	
	@OneToMany(mappedBy = "inReplyTo")
	List<HikeReportComment> replies;

	public HikeReportComment() {
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public HikeReport getHikeReportId() {
		return hikeReportId;
	}

	public void setHikeReportId(HikeReport hikeReportId) {
		this.hikeReportId = hikeReportId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public HikeReportComment getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(HikeReportComment inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public List<HikeReportComment> getReplies() {
		return replies;
	}

	public void setReplies(List<HikeReportComment> replies) {
		this.replies = replies;
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
		HikeReportComment other = (HikeReportComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "HikeReportComment [id=" + id + ", commentBox=" + commentBox + ", createDate=" + createDate + "]";
	}

}
