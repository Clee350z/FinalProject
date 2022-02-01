package com.skilldistillery.honeytrails.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

import com.skilldistillery.honeytrails.entities.HikeReport;
import com.skilldistillery.honeytrails.services.HikeReportService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class HikeReportController {

	@Autowired
	private HikeReportService hkSer;

	@GetMapping("hikereports")
	public List<HikeReport> index(HttpServletRequest req, HttpServletResponse res) {
		return hkSer.allHikeRports();
	}

	@GetMapping("hikereports/{reportId}")
	public HikeReport showReport(@PathVariable int reportId, HttpServletResponse res, HttpServletRequest req,
			Principal principal) {
		HikeReport report = hkSer.showReport(principal.getName(), reportId);
		if (report == null) {
			res.setStatus(404);
		}
		StringBuffer url = req.getRequestURL();
		url.append("/").append(report.getId());
		res.setHeader("Location", url.toString());
		return report;
	}

	@PostMapping("trails/{trailId}/hikereports")
	public HikeReport addReport(@RequestBody HikeReport report, Principal principal, HttpServletResponse res,
			HttpServletRequest req, @PathVariable int trailId) {
		try {
			hkSer.createReport(principal.getName(), report, trailId);
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

	@PutMapping("trails/{trailId}/hikereports/{reportId}")
	public HikeReport updateReport(@PathVariable int reportId, @PathVariable int trailId,
			@RequestBody HikeReport report, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		try {
			report = hkSer.updateReport(principal.getName(), reportId, report, trailId);
			if (report == null) {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		StringBuffer url = req.getRequestURL();
		url.append("/").append(report.getId());
		res.setHeader("Location", url.toString());
		return report;
	}

	@DeleteMapping("hikereports/{reportId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int reportId,
			Principal principal) {
		if (hkSer.delete(principal.getName(), reportId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
	
	@GetMapping("trails/{trailId}/hikereports")
	public Set<HikeReport> getHikeReportByTrailId(@PathVariable int trailId){
		return hkSer.getHikeReportByTrailId(trailId);
	}
}
