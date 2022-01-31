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
	public List<HikePhoto> allHikePhotos() {
		return hkRepo.findAll();
	}

	@Override
	public HikePhoto showPhoto(int photoId, int reportId) {
		hkRepo.findByHikeReport_Id(reportId);
		Optional<HikePhoto> phoOpt = hkRepo.findById(photoId);
		HikePhoto photo = null;
		if (phoOpt.isPresent()) {
			photo = phoOpt.get();
		}
		return photo;
	}

	@Override
	public HikePhoto createPhoto(HikePhoto photo, int reportId) {
		Optional<HikeReport> report = hrRepo.findById(reportId);
		if(report.isPresent()) {
			photo.setHikeReport(report.get());
		}
		hkRepo.saveAndFlush(photo);
		return photo;
	}

	@Override
	public HikePhoto updatePhoto(int photoId, HikePhoto photo, int reportId) {
		Optional<HikeReport> report = hrRepo.findById(reportId);
		if(report.isPresent()) {
			photo.setHikeReport(report.get());
		}
		Optional<HikePhoto> phoOpt = hkRepo.findById(photoId);
		HikePhoto managed = null;
		if(phoOpt.isPresent()) {
			managed = phoOpt.get();
			managed.setImageUrl(photo.getImageUrl());
			managed.setTitle(photo.getTitle());
			managed.setDescription(photo.getDescription());
		}
		hkRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public boolean delete(int photoId, int reportId) {
		boolean deleted = false;
		HikePhoto p = hkRepo.findByIdAndHikeReport_Id(photoId, reportId);
		if(p != null) {
			hkRepo.deleteById(photoId);
			deleted = true;
		}
		return deleted;
	}

}
