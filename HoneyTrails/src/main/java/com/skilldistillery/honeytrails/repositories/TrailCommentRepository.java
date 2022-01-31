package com.skilldistillery.honeytrails.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.TrailComment;

public interface TrailCommentRepository extends JpaRepository<TrailComment, Integer> {
	
	Set<TrailComment> findByUser_Username(String username);

}
