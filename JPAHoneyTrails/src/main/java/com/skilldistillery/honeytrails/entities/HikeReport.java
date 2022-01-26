package com.skilldistillery.honeytrails.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HikeReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="hike_title")
	private String hikeTitle;
	
	private String report;
	
	@Column(name="date_created")
	private LocalDateTime dateCreated;
	
	@Column(name="hiked_date")
	private LocalDate hikedDate;
	
	private int rating;

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
