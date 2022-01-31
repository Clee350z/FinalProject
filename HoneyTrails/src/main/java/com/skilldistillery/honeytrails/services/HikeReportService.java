package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.HikeReport;

public interface HikeReportService {
	
	List<HikeReport> allHikeRports();
	
	HikeReport showReport(String username, int reportId);
	
	HikeReport createReport(String username, HikeReport report, int trailId);
	
	HikeReport updateReport(String username, int reportId, HikeReport report, int trailId);
	
	boolean delete(String username, int reportId);

}
