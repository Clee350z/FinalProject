package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
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

import com.skilldistillery.honeytrails.entities.GroupHikeComment;
import com.skilldistillery.honeytrails.services.GroupHikeCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class GroupHikeCommentController {
	
	@Autowired
	private GroupHikeCommentService ghcServ;
	
	
	@GetMapping("grouphikes/comments")
	public List<GroupHikeComment> getAllGhcComments(HttpServletResponse res) {
		
		List<GroupHikeComment> comments = ghcServ.getAllGroupHikeComments();
		if(comments.size() <= 0) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return comments;
	}
	
	@GetMapping("grouphikes/{groupHikeId}/comments")
	public List<GroupHikeComment> getAllCommentsByGroupHike(@PathVariable int groupHikeId) {
		List<GroupHikeComment> comments = ghcServ.getAllGroupHikeCommentsByGroupHike(groupHikeId);
		return comments;
	}
	
	@GetMapping("grouphikes/{groupHikeId}/comments/{commentId}")
	public GroupHikeComment getGHCById(@PathVariable int commentId, HttpServletResponse res) {
		GroupHikeComment ghc = ghcServ.getGroupHikeCommentById(commentId);
		if(ghc == null) {
			res.setStatus(404);
		} 
			return ghc;
		
	}
	
	@PostMapping("grouphikes/{groupHikeId}/comments")
	public GroupHikeComment createGHC(@PathVariable int groupHikeId, @RequestBody GroupHikeComment ghc, HttpServletResponse res, Principal principal) {
		GroupHikeComment newGHC = ghcServ.addGroupHikeComment(ghc, principal.getName(), groupHikeId);
		if(newGHC == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return newGHC;
		
	}
	
	@PutMapping("grouphikes/{groupHikeId}/comments/{commentId}")
	public GroupHikeComment updateGHC(@PathVariable int commentId, @PathVariable int groupHikeId, @RequestBody GroupHikeComment ghc, HttpServletResponse res, Principal principal) {
		GroupHikeComment updatedGHC = ghcServ.updateGroupHikeCommentById(ghc, commentId, principal.getName(), groupHikeId);
		if(updatedGHC != ghc) {
			res.setStatus(200);
			return updatedGHC;
		} else {
			res.setStatus(400);
			
		}
		return updatedGHC;
	}
	
	@DeleteMapping("grouphikes/comments/{commentId}")
	public void deleteGHC(@PathVariable int commentId, Principal principal) {
		ghcServ.deleteGroupHikeCommentById(commentId, principal.getName());
	}
}
