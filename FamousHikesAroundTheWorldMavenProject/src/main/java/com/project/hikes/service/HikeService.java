package com.project.hikes.service;

import java.util.List;

import com.project.hikes.entity.HikeReview;
import com.project.hikes.entity.HikeTrail;
import com.project.hikes.entity.HikeUser;

public interface HikeService {

	public List<HikeTrail> getAllHikeTrails();
	public List<HikeTrail> getRecentHikeTrails();
	public void saveHikeTrail(HikeTrail hikeTrail);
	public HikeTrail getHikeTrail(int id);
	public void deleteHikeTrail(int id);
	
	public void saveReview(int trailId,HikeReview review);
	public List<HikeReview> getRelatedReviews(int trailId); 
	public void deleteHikeReview(int reviewId);
	
	public HikeUser getAuthenticatedCurrentUser();
	
}
