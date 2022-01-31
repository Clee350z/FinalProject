package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.Condition;
import com.skilldistillery.honeytrails.services.ConditionService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class ConditionController {
	
	@Autowired
	private ConditionService conSer;
	
	@GetMapping("trails/conditions")
	public List<Condition> index(){
		return conSer.index();
	}

}
