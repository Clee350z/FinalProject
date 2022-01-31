package com.skilldistillery.honeytrails.entities;

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
@Table(name = "hike_photo")
public class HikePhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "image_url")
	private String imageUrl;

	private String title;

	private String description;
	
	@ManyToOne
	@JoinColumn(name="hike_report_id")
	@JsonIgnore
	private HikeReport hikeReport;
	
	public HikePhoto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HikeReport getHikeReport() {
		return hikeReport;
	}

	public void setHikeReport(HikeReport hikeReport) {
		this.hikeReport = hikeReport;
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
		HikePhoto other = (HikePhoto) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "HikePhoto [id=" + id + ", imageUrl=" + imageUrl + ", title=" + title + ", description=" + description
				+ "]";
	}

}
