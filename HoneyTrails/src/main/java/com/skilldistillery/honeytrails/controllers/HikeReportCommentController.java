package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
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

import com.skilldistillery.honeytrails.entities.HikeReportComment;
import com.skilldistillery.honeytrails.services.HikeReportCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class HikeReportCommentController {

	@Autowired
	private HikeReportCommentService commentSer;

	@GetMapping("hikereports/comments")
	public List<HikeReportComment> index() {
		return commentSer.getAllComments();
	}
	
	@GetMapping("hikereports/{hikeReportId}/comments")
	public List<HikeReportComment> getAllHikeReportCommentsByHikeReport(@PathVariable int hikeReportId) {
		List<HikeReportComment> comments = commentSer.getAllHikeReportCommentsByHikeReport(hikeReportId);
		return comments;
	}

	@GetMapping("trails/{trailId}/hikereports/{reportId}/comments/{commentId}")
	public HikeReportComment showComment(@PathVariable int reportId, @PathVariable int commentId,
			HttpServletRequest req, HttpServletResponse res) {
		HikeReportComment comment = commentSer.CommentById(commentId, reportId);
		if (comment == null) {
			res.setStatus(404);
		}
		StringBuffer url = req.getRequestURL();
		url.append("/").append(comment.getId());
		res.setHeader("Location", url.toString());
		return comment;
	}

	@PostMapping("trails/{trailId}/hikereports/{reportId}/comments")
	public HikeReportComment addcomment(@RequestBody HikeReportComment comment, @PathVariable int reportId,
			 HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			commentSer.addComment(comment, principal.getName(), reportId);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(comment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}
	
	@PutMapping("trails/{trailId}/hikereports/{reportId}/comments/{commentId}")
	public HikeReportComment updateComment(@PathVariable int reportId, @RequestBody HikeReportComment comment,
			@PathVariable int commentId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			comment = commentSer.updateComment(comment, principal.getName(), reportId, commentId);
			if(comment == null) {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		StringBuffer url = req.getRequestURL();
		url.append("/").append(comment.getId());
		res.setHeader("Location", url.toString());
		return comment;
	}
	
	@DeleteMapping("trails/{trailId}/hikereports/{reportId}/comments/{commentId}")
	public void destroy(@PathVariable int reportId, @PathVariable int commentId, HttpServletResponse res, 
			HttpServletRequest req, Principal principal) {
		if(commentSer.deleteComment(commentId, reportId, principal.getName())) {
			res.setStatus(204);
		}
		else {
			res.setStatus(404);
		}
	}
}
