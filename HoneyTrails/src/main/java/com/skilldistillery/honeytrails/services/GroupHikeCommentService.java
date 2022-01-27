package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;

public interface GroupHikeCommentService {
	
	List<GroupHikeComment> getAllGroupHikeComments();
	
	GroupHikeComment getGroupHikeCommentById(int groupHikeCommentId);
	
	GroupHikeComment getGroupHikeCommentByUsername(String username);
	
	GroupHikeComment addGroupHikeComment(GroupHikeComment groupHikeComment);
	
	GroupHikeComment updateGroupHikeCommentById(int groupHikeCommentId);
	
	GroupHikeComment deleteGroupHikeCommentById(int groupHikeCommentId);
	

}
