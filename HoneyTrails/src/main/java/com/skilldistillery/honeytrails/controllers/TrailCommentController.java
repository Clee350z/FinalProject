package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("trailcomments/{id}")
	public TrailComment getCommentById(@PathVariable int id, HttpServletResponse res) {
		TrailComment trailComment = tcSvc.show(id);
		if (trailComment == null) {
			res.setStatus(400);
		}
		return trailComment;
	}

	@PostMapping("trailcomments")
	public TrailComment addTrailComment(@RequestBody TrailComment trailComment, HttpServletResponse res,
			HttpServletRequest req, Principal principal) {
		try {
			tcSvc.create(principal.getName(), trailComment);
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
}
