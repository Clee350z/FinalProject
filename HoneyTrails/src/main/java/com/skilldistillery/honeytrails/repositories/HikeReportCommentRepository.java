package com.skilldistillery.honeytrails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.HikeReportComment;

public interface HikeReportCommentRepository extends JpaRepository<HikeReportComment, Integer> {

	List<HikeReportComment> getByHikeReportId_Id(int reportId);

	HikeReportComment findByHikeReportId_Id(int reportId);
	
	HikeReportComment findByIdAndUserId_Username(int commentId, String username);
}
