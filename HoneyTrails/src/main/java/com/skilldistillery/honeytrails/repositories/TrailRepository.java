package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.Trail;

public interface TrailRepository extends JpaRepository<Trail, Integer> {

}
