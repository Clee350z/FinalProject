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
@CrossOrigin({ "http://localhost:4300" })
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
	
	@GetMapping("grouphikes/comments/{grouphikecommentid}")
	public GroupHikeComment getGHCById(@PathVariable int grouphikecommentid, HttpServletResponse res) {
		GroupHikeComment ghc = ghcServ.getGroupHikeCommentById(grouphikecommentid);
		if(ghc == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return ghc;
	}
	
	@PostMapping("grouphikes/comments")
	public GroupHikeComment createGHC(@RequestBody GroupHikeComment ghc, HttpServletResponse res, Principal principal) {
		GroupHikeComment newGHC = ghcServ.addGroupHikeComment(ghc, principal.getName());
		if(newGHC == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return newGHC;
		
	}
	
	@PutMapping("grouphikes/comments/{grouhikecommentid}")
	public GroupHikeComment updateGHC(@PathVariable int grouphikecommentid, @RequestBody GroupHikeComment ghc, HttpServletResponse res, Principal principal) {
		GroupHikeComment updatedGHC = ghcServ.updateGroupHikeCommentById(ghc, grouphikecommentid, principal.getName());
		if(updatedGHC != ghc) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		return updatedGHC;
	}
	
	@DeleteMapping("grouphikes/comments/{grouphikecommentid}")
	public void deleteGHC(@PathVariable int grouphikecommentid, Principal principal) {
		ghcServ.deleteGroupHikeCommentById(grouphikecommentid, principal.getName());
	}
}
