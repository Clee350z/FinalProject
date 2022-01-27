package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.HikeReportComment;

public interface GroupHikeCommentService {
	
	List<HikeReportComment> getAllGroupHikeCommentsByGroupHikeId(int groupHikeId);
	
	HikeReportComment getGroupHikeCommentById(int groupHikeCommentId);
	
//	HikeReportComment getGroupHikeCommentByUsername(String username);
	
	HikeReportComment addGroupHikeComment(HikeReportComment hikeReportComment);
	
	HikeReportComment updateGroupHikeCommentById(int groupHikeCommentId);
	
	void deleteGroupHikeCommentById(int groupHikeCommentId);

	

}
