package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.Trail;
import com.skilldistillery.honeytrails.repositories.TrailRepository;

@Service
public class TrailServiceImpl implements TrailService {

	@Autowired
	TrailRepository tr;

	@Override
	public List<Trail> getAllTrails() {
		return tr.findAll();
	}

	@Override
	public Trail getTrailById(int trailId) {
		if (tr.existsById(trailId)) {
			return tr.findById(trailId).get();
		}
		return null;
	}

	@Override
	public Trail addTrail(Trail trail) {
		return tr.saveAndFlush(trail);
	}

	@Override
	public Trail updateTrail(int trailId, Trail trail) {
		if (tr.existsById(trailId)) {
			return tr.save(trail);
		}
		return null;
	}

	@Override
	public boolean deleteTrail(int trailId) {
		tr.deleteById(trailId);
		if (tr.existsById(trailId)) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public List<Trail> getRandomTrail(int howMany){
		return tr.getRandomTrails(howMany);
	}

}
