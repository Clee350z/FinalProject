package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.honeytrails.entities.HikeReport;
import com.skilldistillery.honeytrails.services.HikeReportService;

@RestController
@RequestMapping("api")
public class HikeReportController {
	
	@Autowired
	private HikeReportService hkSer;
	
	@GetMapping("hikes")
	public List<HikeReport> index(){
		return hkSer.allHikeRports();
	}

}
