package com.ride.dao;

import java.util.List;
import java.util.Scanner;

import com.ride.entity.Rides;
import com.ride.entity.Driver;
import com.ride.entity.Users;
import com.ride.enums.DriverStatus;
import com.ride.enums.RideStatus;
import com.ride.main.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class RidesDao {
	static Scanner sc = new Scanner(System.in);
	static EntityManager em = Connection.getEntityManagerFactory().createEntityManager();
	static Rides r = new Rides();
	static Driver d = new Driver();
	static Users u = new Users();
	final int fare = 25;

	public void insert(String source, String destination, double distance, int userId, int driverId) {

		double fare = distance * 25;

		Users user = em.find(Users.class, userId);
		Driver driver = em.find(Driver.class, driverId);

		if (user == null) {
			System.out.println("User not found");
			return;
		}

		if (driver == null) {
			System.out.println("Driver not found");
			return;
		}

		Rides ride = new Rides(); // Create new ride

		ride.setSource(source);
		ride.setDestination(destination);
		ride.setFare(fare);
		ride.setStatus(RideStatus.REQUESTED);

		ride.setUsers(user);
		ride.setDriver(driver);

		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(ride);
		et.commit();

		System.out.println("Ride Booked Successfully...");
	}

	public boolean checkUser(int id) {
		Users users = em.find(Users.class, id);
		if (users != null) {
			return true;
		} else {
			System.out.println("No data found to update.");
			return false;
		}

	}

	public boolean checkDriver(int id) {
		Driver driver = em.find(Driver.class, id);
		if (driver != null) {
			return true;
		} else {
			System.out.println("No data found to update.");
			return false;
		}

	}

	public boolean checkRide(int id) {
		Rides ride = em.find(Rides.class, id);
		if (ride != null) {
			return true;
		} else {
			System.out.println("No data found to update.");
			return false;
		}

	}

	public void acceptRide(int id) {

		Rides ride = em.find(Rides.class, id);

		if (ride != null) {

			Driver driver = ride.getDriver();

			EntityTransaction et = em.getTransaction();
			et.begin();

			ride.setStatus(RideStatus.ACCEPTED);

			if (driver != null) {
				driver.setDriverStatus(DriverStatus.BUZY);
			}

			et.commit();

			System.out.println("Ride Accepted Successfully.");
		}
	}

	public List<Rides> displayAll() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rides> query = cb.createQuery(Rides.class);
		Root<Rides> root = query.from(Rides.class);
		query.select(root);

		TypedQuery<Rides> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

	}

	public List<Rides> displayById(int id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rides> query = cb.createQuery(Rides.class);
		Root<Rides> root = query.from(Rides.class);
		query.select(root).where(cb.equal(root.get("users").get("id"), id));

		TypedQuery<Rides> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

//	public void updateName(String Name) {
//
//		users.setName(Name);
//		em.getTransaction().begin();
//		em.merge(users);
//		em.getTransaction().commit();
//		System.out.println("update Name Succcessfully.");
//	}
//
//	public void updateEmail(String Email) {
//
//		users.setEmail(Email);
//		em.getTransaction().begin();
//		em.merge(users);
//		em.getTransaction().commit();
//		System.out.println("update Email Succcessfully.");
//	}
//
//	public void updatePhone(Long phone) {
//
//		users.setPhone(phone);
//		em.getTransaction().begin();
//		em.merge(users);
//		em.getTransaction().commit();
//		System.out.println("update Phone Succcessfully.");
//	}
//
//	public void deleteData(int id) {
//
//		Users users = em.find(Users.class, id);
//		if (users != null) {
//			em.getTransaction().begin();
//			em.remove(users);
//			em.getTransaction().commit();
//		} else {
//			System.out.println("No data found to Delete.");
//		}
//	}

}
