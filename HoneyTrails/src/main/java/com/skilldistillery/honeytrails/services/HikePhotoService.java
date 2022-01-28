package com.skilldistillery.honeytrails.services;

import java.util.List;

import com.skilldistillery.honeytrails.entities.HikePhoto;

public interface HikePhotoService {
	
	List<HikePhoto> allHikePhotos(int reportId);
	
	HikePhoto showPhoto(int photoId);
	
	HikePhoto createPhoto(HikePhoto photo);
	
	HikePhoto updatePhoto(int photoId, HikePhoto photo);
	
	boolean delete(int photoId);

}
