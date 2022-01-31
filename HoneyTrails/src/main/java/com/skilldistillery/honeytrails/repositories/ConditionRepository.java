package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.Condition;

public interface ConditionRepository extends JpaRepository<Condition, Integer> {

}
