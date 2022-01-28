package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.HikePhoto;
import com.skilldistillery.honeytrails.entities.HikeReport;
import com.skilldistillery.honeytrails.repositories.HikePhotoRepository;
import com.skilldistillery.honeytrails.repositories.HikeReportRepository;

@Service
public class HikePhotoServiceImpl implements HikePhotoService {

	@Autowired
	private HikePhotoRepository hkRepo;
	
	@Autowired
	private HikeReportRepository hrRepo;

	@Override
	public List<HikePhoto> allHikePhotos(int reportId) {
		return hkRepo.findByHikeReport_Id(reportId);
	}

	@Override
	public HikePhoto showPhoto(int photoId) {
		Optional<HikePhoto> phoOpt = hkRepo.findById(photoId);
		HikePhoto photo = null;
		if (phoOpt.isPresent()) {
			photo = phoOpt.get();
		}
		return photo;
	}

	@Override
	public HikePhoto createPhoto(HikePhoto photo) {
		if(photo.getHikeReport() == null) {
			HikeReport report = new HikeReport();
			report.setId(1);
			photo.setHikeReport(report);
		}
		hkRepo.saveAndFlush(photo);
		return photo;
	}

	@Override
	public HikePhoto updatePhoto(int photoId, HikePhoto photo) {
		Optional<HikePhoto> phoOpt = hkRepo.findById(photoId);
		HikePhoto managed = null;
		if(phoOpt.isPresent()) {
			managed = phoOpt.get();
			managed.setImageUrl(photo.getImageUrl());
			managed.setTitle(photo.getTitle());
			managed.setDescription(photo.getDescription());
			if(photo.getHikeReport() != null) {
				managed.setHikeReport(photo.getHikeReport());
			}
		}
		hkRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public boolean delete(int photoId) {
		boolean deleted = false;
		if(hkRepo.existsById(photoId)) {
			hkRepo.deleteById(photoId);
			deleted = true;
		}
		return deleted;
	}

}
