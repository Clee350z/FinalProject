package com.skilldistillery.honeytrails.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.honeytrails.entities.TrailComment;
import com.skilldistillery.honeytrails.entities.User;
import com.skilldistillery.honeytrails.repositories.TrailCommentRepository;
import com.skilldistillery.honeytrails.repositories.UserRepository;

@Service
public class TrailCommentServiceImpl implements TrailCommentService {

	@Autowired
	TrailCommentRepository tcr;

	@Autowired
	UserRepository ur;

	@Override
	public List<TrailComment> index() {
		return tcr.findAll();
	}

	@Override
	public TrailComment show(int tcId) {
		TrailComment tc = tcr.findById(tcId).get();
		return tc;
	}

	@Override
	public TrailComment create(String username, TrailComment tc) {
		User user = ur.findByUsername(username);
		if (user != null) {
			tc.setUser(user);
			return tcr.saveAndFlush(tc);
		}
		return null;
	}

	@Override
	public TrailComment update(String username, int tcId, TrailComment tc) {
		if(username.equals(tc.getUser().getUsername())) {
			if(tcr.existsById(tcId)) {
				return tcr.save(tc);
			}
		}
		return null;
	}

	@Override
	public boolean delete(String username, int tcId) {
		TrailComment tc = tcr.findById(tcId).get();
		if(tc.getUser().getUsername().equals(username)) {
			tcr.deleteById(tcId);
			return true;
		}
		return false;
	}

	@Override
	public Set<TrailComment> getCommentsByUser_Username(String username) {
		return tcr.findByUser_Username(username);
	}

}
