package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.Trail;
import com.skilldistillery.honeytrails.services.TrailService;

@RestController
@RequestMapping("api")
public class TrailController {
	
	@Autowired
	private TrailService trailSvc;
	
	/*---------------------------------------------------------------------
	 * get all trails
	 ---------------------------------------------------------------------*/
	
	@GetMapping("trails")
	public List<Trail> getAllTrails(){
		return trailSvc.index();
	}
	
	/*---------------------------------------------------------------------
	 * get trail by id
	 ---------------------------------------------------------------------*/

	@GetMapping("trails/{id}")
	public Trail getTrailById(@PathVariable int id, HttpServletResponse res) {
		Trail trail = trailSvc.show(id);
		if (trail == null) {
			res.setStatus(400);
		}
		return trail;
	}
	
	
	
}
