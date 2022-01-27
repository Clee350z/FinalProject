package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.TrailComment;

public interface TrailCommentRepository extends JpaRepository<TrailComment, Integer> {

}
