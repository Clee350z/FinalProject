package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.HikeReportComment;

public interface HikeReportCommentService {

	List<HikeReportComment> getAllComments(int reportId);

	HikeReportComment CommentById(int commentId, int reportId);

	HikeReportComment addComment(HikeReportComment comment, String username, int reportId);

	HikeReportComment updateComment(HikeReportComment comment, String username, int reportId, int commentId);
	
	boolean deleteComment(int commentId, String username);

}
