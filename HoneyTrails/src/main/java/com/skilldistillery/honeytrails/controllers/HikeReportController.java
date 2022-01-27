package com.skilldistillery.honeytrails.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("hikes/{reportId}")
	public HikeReport showReport(@PathVariable int reportId, HttpServletResponse res) {
		HikeReport report = hkSer.showReport(reportId);
		if(report == null) {
			res.setStatus(404);
		}
		return report;
	}

}
