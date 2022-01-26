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

@Entity
public class Trail {
	
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
	private List<User> usersFavorite;
	
	@ManyToMany(mappedBy = "plannedHikes")
	private List<User> usersPlanned;
	
	@OneToMany(mappedBy = "trail")
	private List<TrailComment> comments;
	
	@OneToMany(mappedBy = "trail")
	private List<GroupHike> groupHikes;
	
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
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Constructor
	 * 
	 -----------------------------------------------------------------------------------------------------*/

	public Trail() {}

	public Trail(int id, String name, String location, double lengthMiles, String pictureUrl, boolean trailOpen,
			double latitude, double longitude, Difficulty difficulty) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.lengthMiles = lengthMiles;
		this.pictureUrl = pictureUrl;
		this.trailOpen = trailOpen;
		this.latitude = latitude;
		this.longitude = longitude;
		this.difficulty = difficulty;
	}
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *      Hashcode & Equals
	 * 
	 -----------------------------------------------------------------------------------------------------*/

	@Override
	public int hashCode() {
		return Objects.hash(difficulty, id, latitude, lengthMiles, location, longitude, name, pictureUrl, trailOpen);
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
		return Objects.equals(difficulty, other.difficulty) && id == other.id
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(lengthMiles) == Double.doubleToLongBits(other.lengthMiles)
				&& Objects.equals(location, other.location)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude)
				&& Objects.equals(name, other.name) && Objects.equals(pictureUrl, other.pictureUrl)
				&& trailOpen == other.trailOpen;
	}
	
	
	
	
	
	
	
	
	
}
