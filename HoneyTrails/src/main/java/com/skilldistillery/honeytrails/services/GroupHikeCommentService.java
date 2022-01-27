package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;

public interface GroupHikeCommentService {
	
	List<GroupHikeComment> getAllGroupHikeCommentsByGroupHikeId(int groupHikeId);
	
	GroupHikeComment getGroupHikeCommentById(int groupHikeCommentId);
	
//	GroupHikeComment getGroupHikeCommentByUsername(String username);
	
	GroupHikeComment addGroupHikeComment(GroupHikeComment groupHikeComment);
	
	GroupHikeComment updateGroupHikeCommentById(int groupHikeCommentId);
	
	void deleteGroupHikeCommentById(int groupHikeCommentId);

	

}
