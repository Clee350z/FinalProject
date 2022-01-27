package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.Trail;

public interface TrailService {
	public List<Trail> index();
	
	public Trail show(int trailId);
	
	public Trail create(Trail trail);
	
	public Trail update(int trailId, Trail trail);
	
	public boolean destroy(int trailId);
}
