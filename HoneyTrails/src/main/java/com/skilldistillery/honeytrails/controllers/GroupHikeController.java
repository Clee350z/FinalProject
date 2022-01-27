package com.skilldistillery.honeytrails.controllers;

import java.util.List;

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

import com.skilldistillery.honeytrails.entities.GroupHike;
import com.skilldistillery.honeytrails.services.GroupHikeService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class GroupHikeController {
	
	@Autowired GroupHikeService ghServ;
	
	@GetMapping("grouphikes")
	public List<GroupHike> index(HttpServletResponse res) {
		List<GroupHike> groupHikes = ghServ.getAllGroupHikes();
		if(groupHikes.size() <= 0) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return groupHikes;
	}
	
	@GetMapping("grouphikes/{grouphikeid}")
	public GroupHike groupHikeById(@PathVariable int grouphikeid, HttpServletResponse res) {
		GroupHike groupHike = ghServ.getGroupHikeById(grouphikeid);
		if(groupHike == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return groupHike;
	}
	
	@GetMapping("grouphikes/eventname/{eventname}")
	public GroupHike groupHikeByEventName(@PathVariable String eventname, HttpServletResponse res) {
		GroupHike groupHikeByName = ghServ.getGroupHikeByEventName(eventname);
		if(groupHikeByName == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(200);
		}
		return groupHikeByName;
	}
	
	@PostMapping("grouphikes")
	public GroupHike createGroupHike(@RequestBody GroupHike groupHike, HttpServletResponse res) {
		GroupHike newGroupHike = ghServ.addGroupHike(groupHike);
		if(newGroupHike == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return newGroupHike;
	}
	
	@PutMapping("grouphikes/{grouphikeid}")
	public GroupHike updateGroupHike(@RequestBody GroupHike groupHike, @PathVariable int grouphikeid, HttpServletResponse res) {
		GroupHike updatedGroupHike = ghServ.updateGroupHikeById(groupHike, grouphikeid);
		if(updatedGroupHike != groupHike) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		return updatedGroupHike;
	}
	
	@DeleteMapping("groupHikes/{grouhikeid}")
	public void deletedGroupHike(@PathVariable int grouphikeid) {
		ghServ.deleteGroupHikeById(grouphikeid);
	}

}