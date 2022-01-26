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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "group_hike")
public class GroupHike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "event_date")
	private String eventName;
	
	@Column(name = "meetup_date")
	private LocalDateTime meetupDate;
	
	@ManyToMany
	@JoinColumn(name = "user_id")
	private List<User> users;
	
	@ManyToOne
	@JoinColumn(name = "trail_id")
	private Trail trail;
	
	@Column(name = "meetup_time")
	private LocalDateTime meetupTime;
	
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;
	
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

	public LocalDateTime getMeetupDate() {
		return meetupDate;
	}

	public void setMeetupDate(LocalDateTime meetupDate) {
		this.meetupDate = meetupDate;
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

	public LocalDateTime getMeetupTime() {
		return meetupTime;
	}

	public void setMeetupTime(LocalDateTime meetupTime) {
		this.meetupTime = meetupTime;
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
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Constructor
	 * 
	 -----------------------------------------------------------------------------------------------------*/
	
	public GroupHike() {}

	public GroupHike(int id, String eventName, LocalDateTime meetupDate, List<User> users, Trail trail,
			LocalDateTime meetupTime, String description, String imageUrl) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.meetupDate = meetupDate;
		this.users = users;
		this.trail = trail;
		this.meetupTime = meetupTime;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Hashcode & Equals
	 * 
	 -----------------------------------------------------------------------------------------------------*/

	@Override
	public int hashCode() {
		return Objects.hash(description, eventName, id, imageUrl, meetupDate, meetupTime, trail, users);
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
		return Objects.equals(description, other.description) && Objects.equals(eventName, other.eventName)
				&& id == other.id && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(meetupDate, other.meetupDate) && Objects.equals(meetupTime, other.meetupTime)
				&& Objects.equals(trail, other.trail) && Objects.equals(users, other.users);
	}
	
	
	
	
	
	
}
