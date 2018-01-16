package com.project.hikes.dao;

import java.util.List;

import com.project.hikes.entity.HikeReview;
import com.project.hikes.entity.HikeTrail;

public interface HikeDao {
	public List<HikeTrail> getAllHikeTrails();
	public List<HikeTrail> getRecentHikeTrails();
	public void saveHikeTrail(HikeTrail hikeTrail);
	public HikeTrail getHikeTrail(int id);
	public void deleteHikeTrail(int id);
	
	public List<HikeReview> getRelatedReviews(int trailId); 
	public void deleteHikeReview(int reviewId);
	
}
