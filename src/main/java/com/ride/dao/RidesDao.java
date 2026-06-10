package com.ride.dao;

import java.util.List;
import java.util.Scanner;

import com.ride.entity.Driver;
import com.ride.entity.Rides;
import com.ride.entity.Users;
import com.ride.entity.Vehicle;
import com.ride.enums.DriverStatus;
import com.ride.enums.RideStatus;
import com.ride.enums.VehicleType;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class RidesDao {
	static Scanner sc = new Scanner(System.in);
	static EntityManager em = Persistence.createEntityManagerFactory("pravin").createEntityManager();
	static Rides r = new Rides();
	static Driver d = new Driver();
	static Users u = new Users();
	double fare;
	public void insert(String source, String destination, double distance, long userId, long vehicleId) {

		
		
		Users user = em.find(Users.class, userId);
		Vehicle vehicle = em.find(Vehicle.class, vehicleId);
		
		if (user == null) {
			System.out.println("User not found");
			return;
		}

		if (vehicle == null) {
			System.out.println("Vehicle not found");
			return;
		}
		
		if(vehicle.getType()==VehicleType.BIKE) {
			 fare = distance * 20;
		}else if(vehicle.getType()==VehicleType.AUTO) {
			 fare = distance * 25;
		}else if(vehicle.getType()==VehicleType.MINI) {
			 fare = distance * 30;
		}else if(vehicle.getType()==VehicleType.SEDAN) {
			 fare = distance * 35;
		}else if(vehicle.getType()==VehicleType.XUV) {
			 fare = distance * 40;
		}
		Rides ride = new Rides(); // Create new ride
		
		ride.setSource(source);
		ride.setDestination(destination);
		ride.setFare(fare);
		ride.setStatus(RideStatus.REQUESTED);

		ride.setUsers(user);
		ride.setDriver(vehicle.getDriver());

		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(ride);
		et.commit();

		System.out.println("Ride Booked Successfully...");
	}
	public List<Vehicle> displaySedan() {

		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> from = query.from(Vehicle.class);
		query.select(from).where(cb.equal(from.get("type"), VehicleType.SEDAN));

		
		return em.createQuery(query).getResultList();
	}

	public List<Vehicle> displayBike() {

		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> from = query.from(Vehicle.class);
		query.select(from).where(cb.equal(from.get("type"), VehicleType.BIKE));

		
		return em.createQuery(query).getResultList();
		

	}
	public List<Vehicle> displayXUV() {

		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> from = query.from(Vehicle.class);
		query.select(from).where(cb.equal(from.get("type"), VehicleType.XUV));

		
		return em.createQuery(query).getResultList();
	}
	
	public List<Vehicle> displayAuto() {

		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> from = query.from(Vehicle.class);
		query.select(from).where(cb.equal(from.get("type"), VehicleType.AUTO));

		
		return em.createQuery(query).getResultList();

	}
	public List<Vehicle> displayMini() {

		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> from = query.from(Vehicle.class);
		query.select(from).where(cb.equal(from.get("type"), VehicleType.MINI));

		
		return em.createQuery(query).getResultList();

	}
	public boolean checkUser(long id) {
		Users users = em.find(Users.class, id);
		if (users != null) {
			return true;
		} else {
			System.out.println("No data found.");
			return false;
		}

	}
	public boolean checkVehicle(long id) {
		Vehicle users = em.find(Vehicle.class, id);
		if (users != null) {
			return true;
		} else {
			System.out.println("No data found.");
			return false;
		}

	}


	public boolean checkDriver(long id) {
		Driver driver = em.find(Driver.class, id);
		if (driver != null) {
			return true;
		} else {
			System.out.println("No data found.");
			return false;
		}

	}

	public boolean checkRide(long id) {
		Rides ride = em.find(Rides.class, id);
		if (ride != null) {
			return true;
		} else {
			System.out.println("No data found.");
			return false;
		}

	}

	public void acceptRide(long id) {

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

	public List<Rides> displayCancel() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rides> query = cb.createQuery(Rides.class);
		Root<Rides> root = query.from(Rides.class);
		query.select(root).where(cb.equal(root.get("status"), "CANCELLED"));

		TypedQuery<Rides> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

	}

	public List<Rides> displayComplete() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rides> query = cb.createQuery(Rides.class);
		Root<Rides> root = query.from(Rides.class);
		query.select(root).where(cb.equal(root.get("status"), "COMPLETED"));

		TypedQuery<Rides> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

	}

	public List<Rides> displayById(long id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rides> query = cb.createQuery(Rides.class);
		Root<Rides> root = query.from(Rides.class);
		query.select(root).where(cb.equal(root.get("users").get("id"), id));

		TypedQuery<Rides> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}
	public List<Rides> displayDriverId(long id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rides> query = cb.createQuery(Rides.class);
		Root<Rides> root = query.from(Rides.class);
		query.select(root).where(cb.equal(root.get("driver").get("id"), id));

		TypedQuery<Rides> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

	public void cancelRide(long id) {
		Rides ride = em.find(Rides.class, id);
		if (ride != null) {
			Driver driver = ride.getDriver();

			EntityTransaction et = em.getTransaction();
			et.begin();

			ride.setStatus(RideStatus.CANCELLED);

			if (driver != null) {
				driver.setDriverStatus(DriverStatus.AVALIBLE);
			}

			et.commit();

			System.out.println("Ride Cancel Successfully.");
		}
	}

	public void updateRide(long id, String source, String destination, double dist) {

		Rides ride = em.find(Rides.class, id);
		double fare = dist * 25;
		if (ride != null) {
			em.getTransaction().begin();

			ride.setSource(source);
			ride.setDestination(destination);
			ride.setFare(dist);

			em.getTransaction().commit();

			System.out.println("Ride updated successfully.");
		}

	}
}