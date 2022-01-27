package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.HikePhoto;
import com.skilldistillery.honeytrails.services.HikePhotoService;

@RestController
@RequestMapping("api")
public class HikePhotoController {
	
	@Autowired
	private HikePhotoService photoSer;
	

	@GetMapping("photos")
	public List<HikePhoto> index(){
		return photoSer.allHikePhotos();
	}
}
