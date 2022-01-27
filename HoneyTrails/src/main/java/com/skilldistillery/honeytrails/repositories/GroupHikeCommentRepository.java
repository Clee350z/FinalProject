package com.skilldistillery.honeytrails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.HikeReportComment;

public interface GroupHikeCommentRepository extends JpaRepository<HikeReportComment, Integer>{

	
//	List<HikeReportComment> findByGroupHike_Id(int groupHikeId);
	
//	HikeReportComment findByUser_Username(String username);
}
