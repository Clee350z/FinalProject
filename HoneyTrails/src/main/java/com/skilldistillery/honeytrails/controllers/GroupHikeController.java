package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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
import com.skilldistillery.honeytrails.entities.User;
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
	
	@GetMapping("grouphikes/{groupHikeId}")
	public GroupHike groupHikeById(@PathVariable int groupHikeId, HttpServletResponse res) {
		GroupHike groupHike = ghServ.getGroupHikeById(groupHikeId);
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
	
	
//	@GetMapping("grouphikes/{groupHikeId}/users")
//	public List<User> getUsersForGroupHike(@PathVariable int groupHikeId, HttpServletResponse res) {
//		List<User> users = ghServ.getUsersByGroupHikeId(groupHikeId);
//		if(users.size() <= 0) {
//			res.setStatus(404);
//		} else {
//			res.setStatus(200);
//		}
//		return users;
//	}
	
	@PostMapping("trails/{trailId}/grouphikes")
	public GroupHike createGroupHike(@PathVariable int trailId, @RequestBody GroupHike groupHike, HttpServletResponse res, Principal principal) {
		GroupHike newGroupHike = ghServ.addGroupHike(groupHike, principal.getName(), trailId);
		if(newGroupHike == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return newGroupHike;
	}
	
	@PutMapping("trails/{trailId}/grouphikes/adduser")
	public GroupHike addUsersToGroupHike(@PathVariable int trailId, @RequestBody GroupHike groupHike, HttpServletResponse res, Principal principal) {
		GroupHike userAddedToGroupHike = ghServ.addUsersToGroupHike(groupHike, principal.getName(), trailId);
		if(userAddedToGroupHike == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return userAddedToGroupHike;
	}
	
	@PutMapping("grouphikes/{groupHikeId}")
	public GroupHike updateGroupHike(@RequestBody GroupHike groupHike, @PathVariable int groupHikeId, HttpServletResponse res, Principal principal) {
		GroupHike updatedGroupHike = ghServ.updateGroupHikeById(groupHike, groupHikeId, principal.getName());
		if(updatedGroupHike != groupHike) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		return updatedGroupHike;
	}
	
	@PutMapping("grouphikes/hide/{groupHikeId}")
	public GroupHike hideGroupHike(@RequestBody GroupHike groupHike, @PathVariable int groupHikeId, HttpServletResponse res, Principal principal) {
		GroupHike updatedGroupHike = ghServ.hideGroupHikeById(groupHike, groupHikeId, principal.getName());
		if(updatedGroupHike != groupHike) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		return updatedGroupHike;
	}
	
	@DeleteMapping("grouphikes/{groupHikeId}")
	public void deletedGroupHike(@PathVariable int groupHikeId, Principal principal) {
		ghServ.deleteGroupHikeById(groupHikeId, principal.getName());
	}
	
	@GetMapping("trails/{trailId}/grouphikes")
	public Set<GroupHike> getGroupHikesByTrailId(@PathVariable int trailId){
		return ghServ.findGroupHikeByTrailId(trailId);
	}

}
