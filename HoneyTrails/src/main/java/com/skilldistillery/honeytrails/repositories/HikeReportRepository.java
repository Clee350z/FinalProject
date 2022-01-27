package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.HikeReport;

public interface HikeReportRepository extends JpaRepository<HikeReport, Integer> {

}
