package com.skilldistillery.honeytrails.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	@Column(name="profile_picture")
	private String profilePicture;

	private String role;

	private Boolean enabled;
	
	private String firstName;
	
	private String lastName;
	
	//need to make "user" in address
	@OneToOne(mappedBy="user")
	private Address address;
	
	private String biography;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="favorite_trail", 
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="trail_id"))
	private List<Trail> favoriteTrails;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="planned_hikes", 
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="trail_id"))
	private List<Trail> plannedHikes;
	
	@OneToMany(mappedBy = "user")
	private List<TrailComment> comments;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="group_hike_has_user", 
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="group_hike_id"))
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddressId(Address address) {
		this.address = address;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	
	public List<Trail> getFavoriteTrails() {
		return favoriteTrails;
	}

	public void setFavoriteTrails(List<Trail> favoriteTrails) {
		this.favoriteTrails = favoriteTrails;
	}

	public List<Trail> getPlannedHikes() {
		return plannedHikes;
	}

	public void setPlannedHikes(List<Trail> plannedHikes) {
		this.plannedHikes = plannedHikes;
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
	public User() {
		super();
	}

	
	public User(int id, String username, String password, String profilePicture, String role, Boolean enabled,
			String firstName, String lastName, Address address, String biography, List<Trail> favoriteTrails,
			List<Trail> plannedHikes, List<TrailComment> comments, List<GroupHike> groupHikes) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.profilePicture = profilePicture;
		this.role = role;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.biography = biography;
		this.favoriteTrails = favoriteTrails;
		this.plannedHikes = plannedHikes;
		this.comments = comments;
		this.groupHikes = groupHikes;
	}

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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", profilePicture="
				+ profilePicture + ", role=" + role + ", enabled=" + enabled + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", biography=" + biography + "]";
	}

}
