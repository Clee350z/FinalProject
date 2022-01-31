package com.skilldistillery.honeytrails.entities;

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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trail{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String location;
	
	@Column(name = "length_miles")
	private double lengthMiles;
	
	@Column(name = "picture_url")
	private String pictureUrl;
	
	@Column(name = "trail_open")
	private boolean trailOpen;
	
	private double latitude;
	
	private double longitude;
	
	@ManyToOne
	@JoinColumn(name = "difficulty_id")
	private Difficulty difficulty;
	
	@ManyToMany(mappedBy = "favoriteTrails")
	@JsonIgnore
	private List<User> usersFavorite;
	
	@ManyToMany(mappedBy = "plannedHikes")
	@JsonIgnore
	private List<User> usersPlanned;
	
	@OneToMany(mappedBy = "trail")
	private List<TrailComment> comments;
	
	@OneToMany(mappedBy = "trail")
	@JsonIgnore
	private List<GroupHike> groupHikes;
	
	@OneToMany(mappedBy = "trail")
	@JsonIgnore
	private List<HikeReport> hikeReports;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLengthMiles() {
		return lengthMiles;
	}

	public void setLengthMiles(double lengthMiles) {
		this.lengthMiles = lengthMiles;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public boolean isTrailOpen() {
		return trailOpen;
	}

	public void setTrailOpen(boolean trailOpen) {
		this.trailOpen = trailOpen;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public List<User> getUsersFavorite() {
		return usersFavorite;
	}

	public void setUsersFavorite(List<User> usersFavorite) {
		this.usersFavorite = usersFavorite;
	}

	public List<User> getUsersPlanned() {
		return usersPlanned;
	}

	public void setUsersPlanned(List<User> usersPlanned) {
		this.usersPlanned = usersPlanned;
	}

	public List<TrailComment> getComments() {
		return comments;
	}

	public void setComments(List<TrailComment> comments) {
		this.comments = comments;
	}

	public List<GroupHike> getGroupHikes() {
		return groupHikes;
	}

	public void setGroupHikes(List<GroupHike> groupHikes) {
		this.groupHikes = groupHikes;
	}
	
	public List<HikeReport> getHikeReports() {
		return hikeReports;
	}
	
	public void setHikeReports(List<HikeReport> hikeReports) {
		this.hikeReports = hikeReports;
	}
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Constructor
	 * 
	 -----------------------------------------------------------------------------------------------------*/


	public Trail() {}

	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *      Hashcode & Equals
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
		Trail other = (Trail) obj;
		return id == other.id;
	}
	
	
	
	
	
	
	
	
	
}
