package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin({"*", "http://localhost:4300"})
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

	@GetMapping("trails/{trailId}")
	public Trail getTrailById(@PathVariable int trailId, HttpServletResponse res, HttpServletRequest req) {
		try {
		Trail trail = trailSvc.getTrailById(trailId);
		return trail;
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("Cannot find trail");
			res.setStatus(404);
			return null;
		}
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
	
	@PutMapping("trails/{trailId}")
	public Trail editExercise(@PathVariable int trailId, @RequestBody Trail trail, HttpServletResponse res) {
		try {
			if (trailSvc.getTrailById(trailId) != null) {
				trailSvc.updateTrail(trailId, trail);
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
	
	@DeleteMapping("trails/{trailId}")
	public void deleteExercise(@PathVariable int trailId, HttpServletResponse res) {
		try {
		if(trailSvc.deleteTrail(trailId)) {
			res.setStatus(204);
		}else {
			res.setStatus(404);
		}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@GetMapping("trails/random/{howMany}")
	public List<Trail> getRandomTrails(@PathVariable int howMany){
		return trailSvc.getRandomTrail(howMany);
	}
	
	
	
	
	
	
}
