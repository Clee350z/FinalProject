package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;
import com.skilldistillery.honeytrails.repositories.GroupHikeCommentRepository;
import com.skilldistillery.honeytrails.repositories.GroupHikeRepository;

@Service
public class GroupHikeCommentServiceImpl implements GroupHikeCommentService {

	@Autowired
	private GroupHikeCommentRepository ghcRepo;
	
	@Autowired
	private GroupHikeRepository ghRepo;
	
//	@Override
//	public List<GroupHikeComment> getAllGroupHikeCommentsByGroupHikeId(int groupHikeId) {
//		if(!ghRepo.existsById(groupHikeId)) {
//			return null;
//		}
//		return ghcRepo.findByGroupHike_Id(groupHikeId);
//		
//	}
	
	@Override
	public List<GroupHikeComment> getAllGroupHikeCommentsByGroupHikeId(int groupHikeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupHikeComment getGroupHikeCommentById(int groupHikeCommentId) {
		Optional<GroupHikeComment> commentOpt = ghcRepo.findById(groupHikeCommentId);
		if(commentOpt.isPresent()) {
			return commentOpt.get();
		}
		return null;
	}

//	@Override
//	public GroupHikeComment getGroupHikeCommentByUsername(String username) {
//		return ghcRepo.findByUser_Username(username);
//	}

	@Override
	public GroupHikeComment addGroupHikeComment(GroupHikeComment groupHikeComment) {
		return ghcRepo.save(groupHikeComment);
	}

	@Override
	public GroupHikeComment updateGroupHikeCommentById(int groupHikeCommentId) {
		return null;
	}

	@Override
	public void deleteGroupHikeCommentById(int groupHikeCommentId) {
		ghcRepo.deleteById(groupHikeCommentId);
	}


}
