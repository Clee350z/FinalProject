package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.Difficulty;

public interface DifficultyService {
	public List<Difficulty> index ();
	
	public Difficulty show(int difficultyId);
	
	public Difficulty create(Difficulty difficulty);
	
	public Difficulty update(int difficultyId, Difficulty difficulty);
	
	public boolean destroy(int difficultyId);
}
