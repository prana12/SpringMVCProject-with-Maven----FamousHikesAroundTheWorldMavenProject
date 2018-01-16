package com.project.hikes.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.hikes.entity.HikeReview;
import com.project.hikes.entity.HikeTrail;

@Repository
public class HikeDaoImpl implements HikeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<HikeTrail> getAllHikeTrails() {
		Session session = sessionFactory.getCurrentSession();
		Query<HikeTrail> query = session.createQuery("from HikeTrail order by createDate desc");
		List<HikeTrail> trails = query.getResultList();
		return trails;
	}

	@Override
	public List<HikeTrail> getRecentHikeTrails() {
		Session session = sessionFactory.getCurrentSession();
		Query<HikeTrail> query = session.createQuery("from HikeTrail order by createDate desc");
		query.setMaxResults(6);
		List<HikeTrail> trails = query.getResultList();
		return trails;
	}

	@Override
	public void saveHikeTrail(HikeTrail hikeTrail) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(hikeTrail);
	}

	@Override
	public HikeTrail getHikeTrail(int theId) {
		Session session = sessionFactory.getCurrentSession();
		HikeTrail hikeTrail = session.get(HikeTrail.class, theId);
		return hikeTrail;
	}

	@Override
	public void deleteHikeTrail(int theId) {
		Session session = sessionFactory.getCurrentSession();
		/*Query theQuery = session.createQuery("delete from HikeTrail where id=:deleteId");
		theQuery.setParameter("deleteId", theId);
		theQuery.executeUpdate();*/
		
		HikeTrail hikeTrail = session.get(HikeTrail.class, theId);
		session.delete(hikeTrail);
	}

	@Override
	public List<HikeReview> getRelatedReviews(int trailId) {
		Session session = sessionFactory.getCurrentSession();
		/*Criteria cr = session.createCriteria(HikeReview.class);
		cr.add(Restrictions.eq("trail_id", trailId));
		List<HikeReview> results = cr.list();*/
		
		String sql = "SELECT * FROM reviews WHERE trail_id=:theId ORDER BY create_date DESC";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(HikeReview.class);
		query.setParameter("theId", trailId);
		List results = query.list();
		
		return results;
	}

	@Override
	public void deleteHikeReview(int reviewId) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("delete from HikeReview where id=:deleteId");
		theQuery.setParameter("deleteId", reviewId);
		theQuery.executeUpdate();
	}

}
