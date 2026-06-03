package com.ride.dao;

import java.util.List;
import java.util.Scanner;

import com.ride.entity.Users;
import com.ride.main.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UsersDao {
	static Scanner sc = new Scanner(System.in);
	static EntityManager em = Connection.getEntityManagerFactory().createEntityManager();
	Users users = new Users();

	public void insert(String name,String Email,long phone,double W_bal) {

		
			Users u = new Users();
			
			u.setName(name);
			u.setEmail(Email);
			u.setPhone(phone);
			u.setWallet_balancd(W_bal);
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			System.out.println("User Registered Successfully...");
		
	}
	
	public List<Users> displayAll() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Users> query = cb.createQuery(Users.class);
		Root<Users> root = query.from(Users.class);
		query.select(root);

		 TypedQuery<Users> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

		
	}

	public List<Users> displayById(int id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Users> query = cb.createQuery(Users.class);
		Root<Users> root = query.from(Users.class);
		query.select(root).where(cb.equal(root.get("id"), id));

		TypedQuery<Users> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

	public boolean checkUser(int id) {

	    Users user = em.find(Users.class, id);

	    if (user != null) {
	        return true;
	    } else {
	        System.out.println("No data found.");
	        return false;
	    }
	}
	public void updateName(int id, String name) {

	    Users user = em.find(Users.class, id);

	    if (user != null) {
	        em.getTransaction().begin();

	        user.setName(name);

	        em.getTransaction().commit();

	        System.out.println("Name updated successfully.");
	    }
	}
	public void updateEmail(int id, String email) {

	    Users user = em.find(Users.class, id);

	    if (user != null) {
	        em.getTransaction().begin();

	        user.setEmail(email);

	        em.getTransaction().commit();

	        System.out.println("Email updated successfully.");
	    }
	}
	public void updatePhone(int id, long phone) {

	    Users user = em.find(Users.class, id);

	    if (user != null) {
	        em.getTransaction().begin();

	        user.setPhone(phone);

	        em.getTransaction().commit();

	        System.out.println("Phone updated successfully.");
	    }
	}
	public void deleteData(int id) {

		Users users = em.find(Users.class, id);
		if (users != null) {
			em.getTransaction().begin();
			em.remove(users);
			em.getTransaction().commit();
		} else {
			System.out.println("No data found to Delete.");
		}
	}

}
