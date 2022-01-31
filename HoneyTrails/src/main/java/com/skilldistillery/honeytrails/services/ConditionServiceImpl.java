package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.Condition;
import com.skilldistillery.honeytrails.repositories.ConditionRepository;

@Service
public class ConditionServiceImpl implements ConditionService {

	@Autowired
	private ConditionRepository condRepo;
	
	@Override
	public List<Condition> index() {
		return condRepo.findAll();
	}

}
