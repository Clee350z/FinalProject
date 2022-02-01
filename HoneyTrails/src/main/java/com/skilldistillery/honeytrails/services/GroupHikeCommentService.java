package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;

public interface GroupHikeCommentService {
	
	List<GroupHikeComment> getAllGroupHikeComments();
	
	GroupHikeComment getGroupHikeCommentById(int groupHikeCommentId);
	
//	GroupHikeComment getGroupHikeCommentByUsername(String username);
	
	GroupHikeComment addGroupHikeComment(GroupHikeComment groupHikeComment, String username, int groupHikeId);
	
	GroupHikeComment updateGroupHikeCommentById(GroupHikeComment groupHikeComment, int groupHikeCommentId, String username, int groupHikeId);
	
	void deleteGroupHikeCommentById(int groupHikeCommentId, String username);

	List<GroupHikeComment> getAllGroupHikeCommentsByGroupHike(int groupHikeId);
	

}
