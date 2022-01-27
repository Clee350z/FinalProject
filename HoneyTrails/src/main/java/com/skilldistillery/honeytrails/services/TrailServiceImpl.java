package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.honeytrails.entities.Trail;
import com.skilldistillery.honeytrails.repositories.TrailRepository;

public class TrailServiceImpl implements TrailService {

	@Autowired
	TrailRepository tr;

	@Override
	public List<Trail> index() {
		return tr.findAll();
	}

	@Override
	public Trail show(int trailId) {
		return tr.findById(trailId).get();
	}

	@Override
	public Trail create(Trail trail) {
		return tr.saveAndFlush(trail);
	}

	@Override
	public Trail update(int trailId, Trail trail) {
		if (tr.existsById(trailId)) {
			return tr.save(trail);
		}
		return null;
	}

	@Override
	public boolean destroy(int trailId) {
		tr.deleteById(trailId);
		if (tr.existsById(trailId)) {
			return false;
		} else {
			return false;
		}
	}

}
