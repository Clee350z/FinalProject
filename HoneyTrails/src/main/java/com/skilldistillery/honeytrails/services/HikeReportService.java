package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.HikeReport;

public interface HikeReportService {
	
	List<HikeReport> allHikeRports();
	
	HikeReport showReport(int reportId);
	
	HikeReport createReport(HikeReport report);
	
	HikeReport updateReport(int reportId, HikeReport report);
	
	boolean delete(int reportId);

}
