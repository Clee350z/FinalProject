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

import com.skilldistillery.honeytrails.entities.HikePhoto;
import com.skilldistillery.honeytrails.services.HikePhotoService;

@RestController
@RequestMapping("api")
public class HikePhotoController {

	@Autowired
	private HikePhotoService photoSer;

	@GetMapping("hikes/{reportId}/photos")
	public List<HikePhoto> index(@PathVariable int reportId) {
		return photoSer.allHikePhotos(reportId);
	}

	@GetMapping("hikes/{reportId}/photos/{photoId}")
	public HikePhoto showPhoto(@PathVariable int reportId, @PathVariable int photoId, HttpServletRequest req,
			HttpServletResponse res) {
		HikePhoto photo = photoSer.showPhoto(photoId, reportId);
		if (photo == null) {
			res.setStatus(404);
		}
		StringBuffer url = req.getRequestURL();
		url.append("/").append(photo.getId());
		res.setHeader("Location", url.toString());
		return photo;
	}

	@PostMapping("hikes/{reportId}/photos")
	public HikePhoto addPhoto(@PathVariable int reportId, @RequestBody HikePhoto photo, HttpServletRequest req,
			HttpServletResponse res) {
		try {
			photoSer.createPhoto(photo, reportId);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(photo.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			photo = null;
		}
		return photo;
	}

	@PutMapping("hikes/{reportId}/photos/{photoId}")
	public HikePhoto updatePhoto(@PathVariable int photoId, @RequestBody HikePhoto photo, @PathVariable int reportId,
			HttpServletRequest req, HttpServletResponse res) {
		try {
			photo = photoSer.updatePhoto(photoId, photo, reportId);
			if(photo == null) {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		StringBuffer url = req.getRequestURL();
		url.append("/").append(photo.getId());
		res.setHeader("Location", url.toString());
		return photo;
	}
	
	@DeleteMapping("hikes/{reportId}/photos/{photoId}")
	public void destroy(@PathVariable int photoId, @PathVariable int reportId, HttpServletRequest req, HttpServletResponse res) {
	if(photoSer.delete(photoId, reportId)) {
		res.setStatus(204);
	}
	else {
		res.setStatus(404);
	}
	}
}
