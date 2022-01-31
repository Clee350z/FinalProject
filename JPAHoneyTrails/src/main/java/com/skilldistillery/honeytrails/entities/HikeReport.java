package com.skilldistillery.honeytrails.entities;

import java.time.LocalDate;
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
@Table(name = "hike_report")
public class HikeReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "hike_title")
	private String hikeTitle;

	private String report;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "hiked_date")
	private LocalDate hikedDate;

	private int rating;

	@ManyToOne
	@JoinColumn(name = "condition_type_id")
//	@JsonIgnore 
	private Condition condition;
	
	@ManyToOne
	@JoinColumn(name="trail_id")
//	@JsonIgnore
	private Trail trail;
	
	@ManyToOne
	@JoinColumn(name="user_id")
//	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "hikeReport")
	@JsonIgnore
	private List<HikePhoto> hikePhoto;
	
	@OneToMany(mappedBy = "hikeReportId")
	@JsonIgnore
	private List<HikeReportComment> hikeComments;
	
//	@OneToMany(mappedBy = "hikeReport")
//	@JsonIgnore
//	private List<GroupHikeComment> hikeReportComments;

	public HikeReport() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHikeTitle() {
		return hikeTitle;
	}

	public void setHikeTitle(String hikeTitle) {
		this.hikeTitle = hikeTitle;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getHikedDate() {
		return hikedDate;
	}

	public void setHikedDate(LocalDate hikedDate) {
		this.hikedDate = hikedDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trails) {
		this.trail = trails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<HikePhoto> getHikePhoto() {
		return hikePhoto;
	}

	public void setHikePhoto(List<HikePhoto> hikePhoto) {
		this.hikePhoto = hikePhoto;
	}

//	public List<GroupHikeComment> getGroupHikeComments() {
//		return hikeReportComments;
//	}
//
//	public void setGroupHikeComments(List<GroupHikeComment> hikeReportComments) {
//		this.hikeReportComments = hikeReportComments;
//	}

	public List<HikeReportComment> getHikeComments() {
		return hikeComments;
	}

	public void setHikeComments(List<HikeReportComment> hikeComments) {
		this.hikeComments = hikeComments;
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
		HikeReport other = (HikeReport) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "HikeReport [id=" + id + ", hikeTitle=" + hikeTitle + ", report=" + report + ", dateCreated="
				+ dateCreated + ", hikedDate=" + hikedDate + ", rating=" + rating + "]";
	}

}
