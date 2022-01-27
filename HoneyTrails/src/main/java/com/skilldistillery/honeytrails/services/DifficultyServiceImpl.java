package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.honeytrails.entities.Difficulty;
import com.skilldistillery.honeytrails.repositories.DifficultyRepository;

public class DifficultyServiceImpl implements DifficultyService {

	@Autowired
	private DifficultyRepository dr;

	@Override
	public List<Difficulty> index() {
		return dr.findAll();
	}

	@Override
	public Difficulty show(int difficultyId) {
		Difficulty diff = dr.findById(difficultyId).get();
		return diff;
	}

	@Override
	public Difficulty create(Difficulty difficulty) {
		return dr.saveAndFlush(difficulty);
	}

	@Override
	public Difficulty update(int difficultyId, Difficulty difficulty) {
		if (dr.existsById(difficultyId)) {
			return dr.save(difficulty);
		}
		return null;
	}

	@Override
	public boolean destroy(int difficultyId) {
		dr.deleteById(difficultyId);
		if (dr.existsById(difficultyId)) {
			return false;
		} else {
			return true;
		}
	}

}
