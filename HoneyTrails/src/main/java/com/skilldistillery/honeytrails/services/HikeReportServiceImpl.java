package com.skilldistillery.honeytrails.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.Condition;
import com.skilldistillery.honeytrails.entities.HikeReport;
import com.skilldistillery.honeytrails.entities.Trail;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.HikeReportRepository;
import com.skilldistillery.honeytrails.repositories.TrailRepository;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class HikeReportServiceImpl implements HikeReportService {

	@Autowired
	private HikeReportRepository hrRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TrailRepository tRepo;

	@Override
	public Set<HikeReport> allHikeRports(String username) {
		return hrRepo.findByUser_Username(username);
	}

	@Override
	public HikeReport showReport(String username, int reportId) {
		return hrRepo.findByIdAndUser_Username(reportId, username);
	}

	@Override
	public HikeReport createReport(String username, HikeReport report, int trailId) {
		User user = userRepo.findByUsername(username);
		Optional<Trail> trail = tRepo.findById(trailId);
		if(trail.isPresent()) {
			report.setTrails(trail.get());
		}
		if (report.getUser() != null) {
			report.setUser(user);
		}
		hrRepo.saveAndFlush(report);
		return report;
	}

	@Override
	public HikeReport updateReport(String username, int reportId, HikeReport report) {
		Optional<HikeReport> reportOpt = hrRepo.findById(reportId);
		HikeReport managed = null;
		if (reportOpt.isPresent()) {
			managed = reportOpt.get();
			managed.setHikeTitle(report.getHikeTitle());
			managed.setReport(report.getReport());
			managed.setDateCreated(report.getDateCreated());
			managed.setHikedDate(report.getHikedDate());
			managed.setRating(report.getRating());
			if (report.getTrails() != null) {
				managed.setTrails(report.getTrails());
			}
			if (report.getCondition() != null) {
				managed.setCondition(report.getCondition());
			}
			if (report.getUser() != null) {
				managed.setUser(report.getUser());
			}
		}
		hrRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public boolean delete(String username, int reportId) {
		boolean deleted = false;
		if (hrRepo.existsById(reportId)) {
			hrRepo.deleteById(reportId);
			deleted = true;
		}
		return deleted;
	}

}
