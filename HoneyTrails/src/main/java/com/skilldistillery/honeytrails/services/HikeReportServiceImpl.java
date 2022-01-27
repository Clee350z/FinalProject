package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.honeytrails.entities.HikeReport;
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
		if(reportOpt.isPresent()) {
			report = reportOpt.get();
		}
		
		return report;
	}

	@Override
	public HikeReport createReport(HikeReport report) {
//		if(report)
		return null;
	}

	@Override
	public HikeReport updateReport(int reportId, HikeReport report) {
		Optional<HikeReport> reportOpt = hrRepo.findById(null);
				HikeReport managed = null;
		return null;
	}

	@Override
	public boolean delete(int reportId) {
		// TODO Auto-generated method stub
		return false;
	}

}
