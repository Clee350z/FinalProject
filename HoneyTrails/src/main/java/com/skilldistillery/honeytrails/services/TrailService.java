package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.Trail;

public interface TrailService {
	public List<Trail> getAllTrails();
	
	public Trail getTrailById(int trailId);
	
	public Trail addTrail(Trail trail);
	
	public Trail updateTrail(int trailId, Trail trail);
	
	public boolean deleteTrail(int trailId);

	List<Trail> getRandomTrail(int howMany);
}
