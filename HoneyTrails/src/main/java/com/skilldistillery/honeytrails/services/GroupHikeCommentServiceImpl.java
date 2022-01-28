package com.skilldistillery.honeytrails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.GroupHikeCommentRepository;
import com.skilldistillery.honeytrails.repositories.GroupHikeRepository;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class GroupHikeCommentServiceImpl implements GroupHikeCommentService {

	@Autowired
	private GroupHikeCommentRepository ghcRepo;

	@Autowired
	private GroupHikeRepository ghRepo;

	@Autowired
	private UserRepository uRepo;

//	@Override
//	public List<GroupHikeComment> getAllGroupHikeCommentsByGroupHikeId(int groupHikeId) {
//		if(!ghRepo.existsById(groupHikeId)) {
//			return null;
//		}
//		return ghcRepo.findByGroupHike_Id(groupHikeId);
//		
//	}

	@Override
	public List<GroupHikeComment> getAllGroupHikeComments() {
		return ghcRepo.findAll();
	}

	@Override
	public GroupHikeComment getGroupHikeCommentById(int groupHikeCommentId) {
		GroupHikeComment comment = ghcRepo.findById(groupHikeCommentId).get();
		if (comment != null) {
			return comment;
		}
		return null;
	}

	@Override
	public GroupHikeComment addGroupHikeComment(GroupHikeComment groupHikeComment, String username) {
		User user = uRepo.findByUsername(username);
		groupHikeComment.setUserId(user);

		return ghcRepo.saveAndFlush(groupHikeComment);

	}

	@Override
	public GroupHikeComment updateGroupHikeCommentById(GroupHikeComment groupHikeComment, int groupHikeCommentId,
			String username) {
		User user = uRepo.findByUsername(username);
		if (user == groupHikeComment.getUserId()) {
			if (ghcRepo.existsById(groupHikeCommentId)) {
				if (groupHikeComment != null) {
					return ghcRepo.saveAndFlush(groupHikeComment);
				}
			}
		}
		return null;
	}

	@Override
	public void deleteGroupHikeCommentById(int groupHikeCommentId, String username) {
		User user = uRepo.findByUsername(username);
		if (user == ghcRepo.findById(groupHikeCommentId).get().getUserId()) {
			ghcRepo.deleteById(groupHikeCommentId);
		}
	}

}
