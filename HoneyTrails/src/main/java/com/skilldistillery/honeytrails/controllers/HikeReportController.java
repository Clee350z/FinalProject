package com.skilldistillery.honeytrails.controllers;

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
	public HikeReport showReport(@PathVariable int reportId, HttpServletResponse res, String username) {
		HikeReport report = hkSer.showReport(username, reportId);
		if(report == null) {
			res.setStatus(404);
		}
		return report;
	}
	
	@PostMapping("hikes")
	public HikeReport addReport(@RequestBody HikeReport report, String username, HttpServletResponse res, HttpServletRequest req) {
		try {
			hkSer.createReport(username, report);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(report.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALID JSON FOR NEW REPORT");
			res.setStatus(400);
			report = null;
		}
		return report;
	}
	
	public HikeReport updateReport(@PathVariable int reportId, @RequestBody HikeReport report, String username, HttpServletResponse res, HttpServletRequest req ) {
		try {
			report = hkSer.updateReport(username, reportId, report);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}
}
