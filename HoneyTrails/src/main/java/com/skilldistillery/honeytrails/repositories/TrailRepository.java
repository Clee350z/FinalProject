package com.skilldistillery.honeytrails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.honeytrails.entities.Trail;

public interface TrailRepository extends JpaRepository<Trail, Integer> {
	
	@Query(value = "SELECT * FROM trail ORDER BY rand() LIMIT :h", nativeQuery = true)
	List<Trail> getRandomTrails(@Param("h") int howMany);
}
