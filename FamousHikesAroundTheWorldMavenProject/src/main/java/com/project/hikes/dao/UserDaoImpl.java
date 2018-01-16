package com.project.hikes.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.hikes.entity.HikeUser;
import com.project.hikes.entity.Role;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public HikeUser findByUsername(String username) {
		//System.out.println("user dao: In findByUsername");
		
		Session session = sessionFactory.getCurrentSession();
		//Query<User> theQuery = session.createQuery("from User where username=:name");
		//theQuery.setParameter("name", username);
		//User user = theQuery.executeUpdate();
		//return user;
		
		HikeUser user = session.get(HikeUser.class, username);
		//System.out.println("user dao: sending user with name: "+user.getFirstName());
		return user;
	}

	@Override
	public HikeUser findUserByEmail(String email) {
		
		HikeUser user = null;
		
		//System.out.println("user dao: In findByEmail");
		Session session = sessionFactory.getCurrentSession();
		
		/*Query<User> theQuery = session.createQuery("from User where where email=:theEmail");
		theQuery.setParameter("theEmail", email);
		user = theQuery.getSingleResult();*/
		/*List<User> results = theQuery.getResultList();
		if(!results.isEmpty()){
			user = results.get(0);
		}*/
		
		Criteria cr = session.createCriteria(HikeUser.class);
		cr.add(Restrictions.eq("email", email));
		List<HikeUser> results = cr.list();
		if(!results.isEmpty()){
			user = results.get(0);
		}
		
		/*Query<User> query = session.createQuery("from User where email = :email");
		query.setParameter("email", email);
		User user1 = query.uniqueResult();*/
		
		
		/*User user2 = entityManager.createQuery(
				  "SELECT u from User u WHERE u.email = :email", User.class).
				  setParameter("email", email).getSingleResult();
		*/
		
		//System.out.println("In User DAO, FOUND user with email: "+user.getEmail());
		return user;
	}

	@Override
	public void saveUser(HikeUser user) {
		System.out.println("In DAO, saving User: "+user.getEmail());
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	@Override
	public Role findUserRole(int id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, id);
		System.out.println("ROLE FOR USER IS "+role.getRole());
		return role;
	}

}
