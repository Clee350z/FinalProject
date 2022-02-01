package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<HikeReport> allHikeRports() {
		return hrRepo.findAll();
	}

	@Override
	public HikeReport showReport(String username, int reportId) {
		return hrRepo.findByIdAndUser_Username(reportId, username);
	}

	@Override
	public HikeReport createReport(String username, HikeReport report, int trailId) {
		User user = userRepo.findByUsername(username);
		Optional<Trail> trail = tRepo.findById(trailId);
		if (trail.isPresent()) {
			report.setTrail(trail.get());
		}
			report.setUser(user);
		hrRepo.saveAndFlush(report);
		return report;
	}

	@Override
	public HikeReport updateReport(String username, int reportId, HikeReport report, int trailId) {
		HikeReport managed = hrRepo.findByIdAndUser_Username(reportId, username);
		Optional<Trail> trail = tRepo.findById(trailId);
		if (trail.isPresent()) {
			report.setTrail(trail.get());
		}
		if (managed != null) {
			managed.setHikeTitle(report.getHikeTitle());
			managed.setReport(report.getReport());
			managed.setDateCreated(report.getDateCreated());
			managed.setHikedDate(report.getHikedDate());
			managed.setRating(report.getRating());
			if (report.getCondition() != null) {
				managed.setCondition(report.getCondition());
			}
		}
		hrRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public boolean delete(String username, int reportId) {
		boolean deleted = false;
		HikeReport report = hrRepo.findByIdAndUser_Username(reportId, username);
		if (report != null) {
			hrRepo.deleteById(reportId);
			deleted = true;
		}
		return deleted;
	}
	
	@Override
	public Set<HikeReport> getHikeReportByTrailId(int trailId){
		return hrRepo.findByTrail_Id(trailId);
	}

}
