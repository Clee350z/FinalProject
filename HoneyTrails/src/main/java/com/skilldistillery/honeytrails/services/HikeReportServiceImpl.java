package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.honeytrails.entities.Condition;
import com.skilldistillery.honeytrails.entities.HikeReport;
import com.skilldistillery.honeytrails.entities.Trail;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.HikeReportRepository;

public class HikeReportServiceImpl implements HikeReportService {

	@Autowired
	private HikeReportRepository hrRepo;

	@Override
	public List<HikeReport> allHikeRports() {
		return hrRepo.findAll();
	}

	@Override
	public HikeReport showReport(int reportId) {
		Optional<HikeReport> reportOpt = hrRepo.findById(reportId);
		HikeReport report = null;
		if (reportOpt.isPresent()) {
			report = reportOpt.get();
		}
		return report;
	}

	@Override
	public HikeReport createReport(HikeReport report) {
		if (report.getTrails() == null) {
			Trail trail = new Trail();
			trail.setId(1);
			report.setTrails(trail);
		}
		if (report.getCondition() == null) {
			Condition con = new Condition();
			con.setId(1);
			report.setCondition(con);
		}
		if (report.getUser() == null) {
			User user = new User();
			user.setId(1);
			report.setUser(user);
		}
		hrRepo.saveAndFlush(report);
		return report;
	}

	@Override
	public HikeReport updateReport(int reportId, HikeReport report) {
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
	public boolean delete(int reportId) {
		boolean deleted = false;
		if (hrRepo.existsById(reportId)) {
			hrRepo.deleteById(reportId);
			deleted = true;
		}
		return deleted;
	}

}
