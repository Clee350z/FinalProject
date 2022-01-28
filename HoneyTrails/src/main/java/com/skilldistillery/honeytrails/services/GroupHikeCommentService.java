package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;

public interface GroupHikeCommentService {
	
	List<GroupHikeComment> getAllGroupHikeComments();
	
	GroupHikeComment getGroupHikeCommentById(int groupHikeCommentId);
	
//	GroupHikeComment getGroupHikeCommentByUsername(String username);
	
	GroupHikeComment addGroupHikeComment(GroupHikeComment groupHikeComment, String username, int groupHikeCommentId);
	
	GroupHikeComment updateGroupHikeCommentById(GroupHikeComment groupHikeComment, int groupHikeCommentId, String username);
	
	void deleteGroupHikeCommentById(int groupHikeCommentId, String username);

	

}
