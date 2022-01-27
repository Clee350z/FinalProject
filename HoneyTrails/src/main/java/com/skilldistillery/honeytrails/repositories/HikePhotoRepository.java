package com.skilldistillery.honeytrails.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.HikePhoto;

public interface HikePhotoRepository extends JpaRepository<HikePhoto, Integer> {

}
