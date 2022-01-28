package com.skilldistillery.honeytrails.services;

import java.util.Set;

import com.skilldistillery.honeytrails.entities.HikeReport;

public interface HikeReportService {
	
	Set<HikeReport> allHikeRports(String username);
	
	HikeReport showReport(String username, int reportId);
	
	HikeReport createReport(String username, HikeReport report, int trailId);
	
	HikeReport updateReport(String username, int reportId, HikeReport report, int trailId);
	
	boolean delete(String username, int reportId);

}
