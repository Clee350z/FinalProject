package com.skilldistillery.honeytrails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.honeytrails.entities.HikePhoto;

public interface HikePhotoRepository extends JpaRepository<HikePhoto, Integer> {
	
	List<HikePhoto> findByHikeReport_Id(int reportId);
	
	HikePhoto findByIdAndHikeReport_Id(int photoId, int reportId);

}
