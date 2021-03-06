package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.HikeReportComment;

public interface HikeReportCommentService {

	List<HikeReportComment> getAllComments();
	
	List<HikeReportComment> getAllHikeReportCommentsByHikeReport(int hikeReportId);

	HikeReportComment CommentById(int commentId, int reportId);

	HikeReportComment addComment(HikeReportComment comment, String username, int reportId);

	HikeReportComment updateComment(HikeReportComment comment, String username, int reportId, int commentId);
	
	boolean deleteComment(int commentId, int reportId, String username);

}
