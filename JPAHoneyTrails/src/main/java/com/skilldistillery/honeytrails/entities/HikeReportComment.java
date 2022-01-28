package com.skilldistillery.honeytrails.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hike_report_comment")
public class HikeReportComment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="comment_box")
	private String commentBox;
	
	@Column(name="create_date")
	private LocalDateTime createDate;

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
