package com.ride.dao;

import java.util.List;
import java.util.Scanner;

import com.ride.entity.Driver;
import com.ride.entity.Vehicle;
import com.ride.enums.VehicleType;
import com.ride.main.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class VehicleDao {
	static Scanner sc = new Scanner(System.in);
	static EntityManager em = Connection.getEntityManagerFactory().createEntityManager();

	public void insert(long id) {

		try {
			Driver driver = em.find(Driver.class, id);
			Vehicle v = new Vehicle();
			if (driver != null) {
				System.out.println("Select Vehicle Type:");
				System.out.println("1. MINI");
				System.out.println("2. BIKE");
				System.out.println("3. AUTO");
				System.out.println("4. SEDAN");
				System.out.println("5. XUV");

				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					v.setType(VehicleType.MINI);
					break;

				case 2:
					v.setType(VehicleType.BIKE);
					break;

				case 3:
					v.setType(VehicleType.AUTO);
					break;

				case 4:
					v.setType(VehicleType.SEDAN);
					break;

				case 5:
					v.setType(VehicleType.XUV);
					break;

				default:
					System.out.println("Invalid Vehicle Type!");
				}
				System.out.print("Enter Vehicle Name -: ");
				String name = sc.nextLine();
				sc.nextLine();
				System.out.print("Enter Vehicle Number -: ");
				String number = sc.nextLine();
				
				v.setName(name);
				v.setVehicle_No(number);
				v.setDriver(driver);
				
				em.getTransaction().begin();
				em.persist(v);
				em.getTransaction().commit();
			}else {
				System.out.println("No DriverFound With ID: " + id);
			}
			
		} catch (Exception e) {
			System.out.println(" Enter Valid Value!!");
			return ;
		}
	}

	public void displayAll() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> root = query.from(Vehicle.class);
		query.select(root);

		TypedQuery<Vehicle> typedQuery = em.createQuery(query);

		List<Vehicle> list = typedQuery.getResultList();

		for (Vehicle u : list) {
			System.out.println("----------------------");
			System.out.println("Vehicle ID: "+u.getId());
			System.out.println("Vehicle Name: "+u.getName());
			System.out.println("Vehicle Number: "+u.getVehicle_No());
			System.out.println("Vehicle Type: "+u.getType());
			System.out.println("Driver Name: "+u.getDriver().getName());
			

			System.out.println("======================");
		}
	}

	public void displayById(long id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> root = query.from(Vehicle.class);
		query.select(root).where(cb.equal(root.get("id"), id));

		TypedQuery<Vehicle> typedQuery = em.createQuery(query);

		List<Vehicle> list = typedQuery.getResultList();

		for (Vehicle u : list) {
			System.out.println(u.getId());
			System.out.println(u.getName());
			System.out.println(u.getVehicle_No());
			System.out.println(u.getType());
			
			System.out.println("======================");
		}
	}

	public void updateData(long id) {

		try {
			Vehicle vehicle = em.find(Vehicle.class, id);
			if (vehicle != null) {
				System.out.print("Do you want to update Vehicle Name? (y/n)");
				char c = sc.next().charAt(0);
				sc.nextLine(); // clear buffer

				if (c == 'y' || c == 'Y') {
					System.out.print("Enter New Vehicle Name: ");
					vehicle.setName(sc.nextLine());
				}
				System.out.print("Do you want to update Vehicle Number? (y/n)");
				char c2 = sc.next().charAt(0);
				sc.nextLine(); // clear buffer

				if (c2 == 'y' || c2 == 'Y') {
					System.out.print("Enter New Vehicle Number: ");
					vehicle.setVehicle_No(sc.nextLine());
				}
				

				System.out.print("Do you want to update Vehicle Type? (y/n)");
				char c1 = sc.next().charAt(0);
				if (c1 == 'y' || c1 == 'Y') {
					System.out.println("Select Vehicle Type:");
					System.out.println("1. MINI");
					System.out.println("2. BIKE");
					System.out.println("3. AUTO");
					System.out.println("4. SEDAN");
					System.out.println("5. XUV");

					int choice = sc.nextInt();

					switch (choice) {
					case 1:
						vehicle.setType(VehicleType.MINI);
						break;

					case 2:
						vehicle.setType(VehicleType.BIKE);
						break;

					case 3:
						vehicle.setType(VehicleType.AUTO);
						break;

					case 4:
						vehicle.setType(VehicleType.SEDAN);
						break;

					case 5:
						vehicle.setType(VehicleType.XUV);
						break;

					default:
						System.out.println("Invalid Vehicle Type!");
					}
				}

				em.getTransaction().begin();
				em.merge(vehicle);
				em.getTransaction().commit();
				System.out.println("Data update Succcessfully.");
			} else {
				System.out.println("No data found to update.");
			}
		} catch (Exception e) {
			System.out.println("Enter Valid Values!!");
			return;
		}
	}

	public void deleteData(long id) {

		Vehicle vehicle = em.find(Vehicle.class, id);
		if (vehicle != null) {
			em.getTransaction().begin();
			em.remove(vehicle);
			em.getTransaction().commit();
		} else {
			System.out.println("No data found to Delete.");
		}
	}

}
