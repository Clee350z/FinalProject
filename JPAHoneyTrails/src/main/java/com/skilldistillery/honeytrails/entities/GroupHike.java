package com.skilldistillery.honeytrails.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "group_hike")
public class GroupHike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "event_name")
	private String eventName;
	
	@Column(name = "meetup_date")
	private LocalDate meetupDate;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User createdByUser;
	
	@ManyToOne
	@JoinColumn(name = "trail_id")
	@JsonIgnore
	private Trail trail;
	
	@Column(name = "meetup_time")
	private LocalTime meetupTime;
	
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="group_hike_has_user", 
	joinColumns=@JoinColumn(name="group_hike_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
//	@JsonIgnore
	private List<User> users;
	
	
	
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDate getMeetupDate() {
		return meetupDate;
	}

	public void setMeetupDate(LocalDate meetupDate) {
		this.meetupDate = meetupDate;
	}

	public LocalTime getMeetupTime() {
		return meetupTime;
	}

	public void setMeetupTime(LocalTime meetupTime) {
		this.meetupTime = meetupTime;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
	}

	@Override
	public String toString() {
		return "GroupHike [id=" + id + ", eventName=" + eventName + ", meetupDate=" + meetupDate + ", createdByUser="
				+ createdByUser + ", trail=" + trail + ", meetupTime=" + meetupTime + ", description=" + description
				+ ", imageUrl=" + imageUrl + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Constructor
	 * 
	 -----------------------------------------------------------------------------------------------------*/

	public GroupHike() {}

	
	
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
		GroupHike other = (GroupHike) obj;
		return id == other.id;
	}
	
	
	
	
	
}
