package com.skilldistillery.honeytrails.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.HikeReport;

public interface HikeReportRepository extends JpaRepository<HikeReport, Integer> {
	
	HikeReport findByIdAndUser_Username(int id, String username);
	
	Set<HikeReport> findByUser_Username(String username);

}
