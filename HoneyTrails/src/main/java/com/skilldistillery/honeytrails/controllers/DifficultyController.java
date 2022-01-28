package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.Difficulty;
import com.skilldistillery.honeytrails.services.DifficultyService;

@RestController
@RequestMapping("api")
public class DifficultyController {
	
	@Autowired
	private DifficultyService difSvc;
	
	/*---------------------------------------------------------------------
	 * get all difficulties
	 ---------------------------------------------------------------------*/
	
	@GetMapping("difficulties")
	public List<Difficulty> getAllDifficulties(){
		return difSvc.index();
	}
	
}
