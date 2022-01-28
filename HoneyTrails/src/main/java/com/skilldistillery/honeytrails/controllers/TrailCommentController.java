package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
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

import com.skilldistillery.honeytrails.entities.TrailComment;
import com.skilldistillery.honeytrails.services.TrailCommentService;

@RestController
@RequestMapping("api")
public class TrailCommentController {

	@Autowired
	private TrailCommentService tcSvc;
	
	/*---------------------------------------------------------------------
	 * get all trail comments
	 ---------------------------------------------------------------------*/

	@GetMapping("trailcomments")
	public List<TrailComment> getAllComments(HttpServletResponse res) {
		List<TrailComment> trailComments = tcSvc.index();
		if (trailComments != null) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
		return trailComments;
	}
	
	/*---------------------------------------------------------------------
	 * get trail comment by id
	 ---------------------------------------------------------------------*/

	@GetMapping("trails/{trailId}/trailcomments/{commentId}")
	public TrailComment getCommentById(@PathVariable int commentId, HttpServletResponse res) {
		TrailComment trailComment = tcSvc.show(commentId);
		if (trailComment == null) {
			res.setStatus(400);
		}
		return trailComment;
	}
	
	/*---------------------------------------------------------------------
	 * create a trail comment
	 ---------------------------------------------------------------------*/

	@PostMapping("trails/{trailId}/comments")
	public TrailComment addTrailComment(@PathVariable int trailId, @RequestBody TrailComment trailComment, HttpServletResponse res,
			HttpServletRequest req, Principal principal) {
		try {
			tcSvc.create(trailId, principal.getName(), trailComment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(trailComment.getTrail().getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID JSON");
			res.setStatus(400);
			trailComment = null;
		}
		return trailComment;
	}
	
	/*---------------------------------------------------------------------
	 * edit a trail comment
	 ---------------------------------------------------------------------*/

	@PutMapping("trails/{trailId}/comments/{commentId}")
	public TrailComment editTrailComment(@PathVariable int commentId, @RequestBody TrailComment trailComment,
			HttpServletResponse res, Principal principal) {
		try {
			if (tcSvc.show(commentId) != null) {
				tcSvc.update(principal.getName(), commentId, trailComment);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return trailComment;
	}
	
	/*---------------------------------------------------------------------
	 * delete a trail comment
	 ---------------------------------------------------------------------*/

	@DeleteMapping("trails/{trailId}/comments/{commentId}")
	public void deleteTrailComment(@PathVariable int commentId, HttpServletResponse res, Principal principal) {
		try {
			if (tcSvc.delete(principal.getName(), commentId)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
