package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.Difficulty;
import com.skilldistillery.honeytrails.services.DifficultyService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class DifficultyController {
	
	@Autowired
	private DifficultyService difSvc;
	
	/*---------------------------------------------------------------------
	 * get all difficulties
	 ---------------------------------------------------------------------*/
	
	@GetMapping("trails/difficulties")
	public List<Difficulty> getAllDifficulties(){
		return difSvc.index();
	}
	
}
