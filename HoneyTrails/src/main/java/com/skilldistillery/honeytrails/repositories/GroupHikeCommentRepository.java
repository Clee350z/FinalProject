package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;

public interface GroupHikeCommentRepository extends JpaRepository<GroupHikeComment, Integer>{

	
	GroupHikeComment findByUser_Username(String username);
}
