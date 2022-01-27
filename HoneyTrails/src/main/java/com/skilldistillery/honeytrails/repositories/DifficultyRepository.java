package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.Difficulty;


public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {

}
