package com.skilldistillery.honeytrails.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Difficulty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "difficulty")
	private List<Trail> trails;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Trail> getTrails() {
		return trails;
	}

	public void setTrails(List<Trail> trails) {
		this.trails = trails;
	}
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Constructor
	 * 
	 -----------------------------------------------------------------------------------------------------*/
	
	public Difficulty() {}

	public Difficulty(int id, String name, String description, List<Trail> trails) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.trails = trails;
	}
	
	/*-----------------------------------------------------------------------------------------------------
	 * 
	 *       Hashcode & Equals
	 * 
	 -----------------------------------------------------------------------------------------------------*/

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, trails);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Difficulty other = (Difficulty) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(trails, other.trails);
	}
	
	
	
	
	
	
	

}
