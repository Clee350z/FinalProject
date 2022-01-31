package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.HikePhoto;

public interface HikePhotoService {
	
	List<HikePhoto> allHikePhotos();
	
	HikePhoto showPhoto(int photoId, int reportId);
	
	HikePhoto createPhoto(HikePhoto photo, int reportId);
	
	HikePhoto updatePhoto(int photoId, HikePhoto photo, int reportId);
	
	boolean delete(int photoId, int reportId);

}
