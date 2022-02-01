package com.skilldistillery.honeytrails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.GroupHikeComment;

public interface GroupHikeCommentRepository extends JpaRepository<GroupHikeComment, Integer>{

	
List<GroupHikeComment> findByGroupHikeId(int groupHikeId);
}
