package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.honeytrails.entities.Condition;
import com.skilldistillery.honeytrails.repositories.ConditionRepository;

public class ConditionServiceImpl implements ConditionService {

	@Autowired
	private ConditionRepository condRepo;
	
	@Override
	public List<Condition> index() {
		return condRepo.findAll();
	}

}
