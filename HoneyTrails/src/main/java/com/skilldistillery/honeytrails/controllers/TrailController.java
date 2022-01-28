package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Trail> getAllTrails(HttpServletResponse res){
		List<Trail> trails = trailSvc.getAllTrails();
		if(trails != null) {
			res.setStatus(200);
		}else {
			res.setStatus(404);
		}
		return trails;
	}
	
	/*---------------------------------------------------------------------
	 * get trail by id
	 ---------------------------------------------------------------------*/

	@GetMapping("trails/{id}")
	public Trail getTrailById(@PathVariable int id, HttpServletResponse res) {
		Trail trail = trailSvc.getTrailById(id);
		if (trail == null) {
			res.setStatus(400);
		}
		return trail;
	}
	
	/*---------------------------------------------------------------------
	 * create a trail
	 ---------------------------------------------------------------------*/
	
	@PostMapping("trails")
	public Trail addTrail(@RequestBody Trail trail, HttpServletResponse res, HttpServletRequest req) {
		try {
			trailSvc.addTrail(trail);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(trail.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID JSON");
			res.setStatus(400);
			trail = null;
		}
		return trail;
	}
	
	/*---------------------------------------------------------------------
	 * edit a trail
	 ---------------------------------------------------------------------*/
	
	@PutMapping("trails/{id}")
	public Trail editExercise(@PathVariable int id, @RequestBody Trail trail, HttpServletResponse res) {
		try {
			if (trailSvc.getTrailById(id) != null) {
				trailSvc.updateTrail(id, trail);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return trail;
	}
	
	/*---------------------------------------------------------------------
	 * delete a trail
	 ---------------------------------------------------------------------*/
	
	@DeleteMapping("trails/{id}")
	public void deleteExercise(@PathVariable int id, HttpServletResponse res) {
		try {
		if(trailSvc.deleteTrail(id)) {
			res.setStatus(204);
		}else {
			res.setStatus(404);
		}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	
	
	
	
	
}
