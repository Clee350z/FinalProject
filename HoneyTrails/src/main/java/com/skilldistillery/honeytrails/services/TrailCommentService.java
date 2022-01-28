package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.honeytrails.entities.TrailComment;

public interface TrailCommentService {
	public List<TrailComment> index();
	
	public Set<TrailComment> getCommentsByUser_Username(String username);
	
	public TrailComment show(int tcId);
	
	public TrailComment create(int trailId, String username, TrailComment tc);
	
	public TrailComment update(String username, int tcId, TrailComment tc);
	
	public boolean delete(String username, int tcId);
}
