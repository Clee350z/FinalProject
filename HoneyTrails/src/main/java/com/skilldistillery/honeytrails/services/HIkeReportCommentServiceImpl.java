package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.HikeReport;
import com.skilldistillery.honeytrails.entities.HikeReportComment;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.HikeReportCommentRepository;
import com.skilldistillery.honeytrails.repositories.HikeReportRepository;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class HIkeReportCommentServiceImpl implements HikeReportCommentService {

	@Autowired
	private HikeReportCommentRepository comRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private HikeReportRepository hikeRepo;

	@Override
	public List<HikeReportComment> getAllComments() {
		return comRepo.findAll();
	}

	@Override
	public HikeReportComment CommentById(int commentId, int reportId) {
		comRepo.findByHikeReportId_Id(reportId);
		Optional<HikeReportComment> comOpt = comRepo.findById(commentId);
		HikeReportComment comment = null;
		if (comOpt.isPresent()) {
			comment = comOpt.get();
		}
		return comment;
	}

	@Override
	public HikeReportComment addComment(HikeReportComment comment, String username, int reportId) {
		User user = userRepo.findByUsername(username);
		Optional<HikeReport> report = hikeRepo.findById(reportId);
		if (report.isPresent()) {
			comment.setHikeReportId(report.get());
		}
		comment.setUserId(user);
		comRepo.saveAndFlush(comment);
		return comment;
	}

	@Override
	public HikeReportComment updateComment(HikeReportComment comment,
			String username, int reportId, int commentId) {
		HikeReportComment managed = comRepo.findByIdAndUserId_Username(commentId, username);
		
		if(managed != null) {
			managed.setCommentBox(comment.getCommentBox());
			managed.setCreateDate(comment.getCreateDate());
		}
		comRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public boolean deleteComment(int commentId, int reportId, String username) {
		boolean deleted = false;
		HikeReportComment comment = comRepo.findByIdAndUserId_Username(commentId, username);
		if(comment != null) {
			comRepo.deleteById(commentId);
			deleted = true;
		}
		return deleted;
	}

}
