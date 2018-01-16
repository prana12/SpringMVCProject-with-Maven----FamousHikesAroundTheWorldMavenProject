package com.project.hikes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hikes.dao.HikeDao;
import com.project.hikes.entity.HikeReview;
import com.project.hikes.entity.HikeTrail;
import com.project.hikes.entity.HikeUser;

@Service
public class HikeServiceImpl implements HikeService {

	@Autowired
	private HikeDao hikeDao;

	@Override
	@Transactional
	public List<HikeTrail> getAllHikeTrails() {
		return hikeDao.getAllHikeTrails();
	}

	@Override
	@Transactional
	public List<HikeTrail> getRecentHikeTrails() {
		return hikeDao.getRecentHikeTrails();
	}

	@Override
	@Transactional
	public void saveHikeTrail(HikeTrail hikeTrail) {
		hikeDao.saveHikeTrail(hikeTrail);
	}

	@Override
	@Transactional
	public HikeTrail getHikeTrail(int id) {
		return hikeDao.getHikeTrail(id);
	}

	@Override
	@Transactional
	public void deleteHikeTrail(int id) {
		hikeDao.deleteHikeTrail(id);
	}

	@Override
	@Transactional
	public void saveReview(int trailId, HikeReview review) {

		HikeUser hikeUser = getAuthenticatedCurrentUser();

		//find current user then set as created by on the review
		/*System.out.println("Checking user in review... ");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null){
			Object principalUser = authentication.getPrincipal();
			if(principalUser instanceof HikeUser){
				hikeUser = (HikeUser)principalUser;
				System.out.println("ID is "+hikeUser.getUserId()
						+" and email: "+hikeUser.getEmail());
			}  	
		}*/

		//add user for a review
		review.setCreatedBy(hikeUser);

		// find Trail info and add the review
		HikeTrail trail = getHikeTrail(trailId);
		trail.addReview(review);
		//hikeDao.saveHikeTrail(trail);
	}

	@Override
	@Transactional
	public List<HikeReview> getRelatedReviews(int trailId) {
		return hikeDao.getRelatedReviews(trailId);
	}

	@Override
	@Transactional
	public void deleteHikeReview(int reviewId) {
		hikeDao.deleteHikeReview(reviewId);
	}

	@Override
	public HikeUser getAuthenticatedCurrentUser() {
		HikeUser hikeUser = null;

		//find current user then set as created by on the review
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null){
			Object principalUser = authentication.getPrincipal();
			if(principalUser instanceof HikeUser){
				hikeUser = (HikeUser)principalUser;
				/*System.out.println("ID is "+hikeUser.getUserId()
						+" and email: "+hikeUser.getEmail());*/
			}  	
		}
		
		return hikeUser;
	}
	

}
