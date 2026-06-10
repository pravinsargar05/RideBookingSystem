package com.ride.dao;

import java.util.List;
import java.util.Scanner;

import com.ride.entity.Driver;
import com.ride.enums.DriverStatus;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class DriverDao {
	static Scanner sc = new Scanner(System.in);
	static EntityManager em = Persistence.createEntityManagerFactory("pravin").createEntityManager();

	public void insert() {

		try {
			Driver d = new Driver();
			System.out.print("Enter Driver Name -:  ");
			String name = sc.nextLine();
			sc.nextLine();
			System.out.print("Enter Driver Email -: ");
			String Email = sc.nextLine();
			System.out.print("Enter Driver Phone -: ");
			long phone = sc.nextLong();
			System.out.print("Enter lAtitude -: ");
			double latitude = sc.nextDouble();
			System.out.print("Enter LongAtitude -: ");
			double longatitude = sc.nextDouble();

			d.setName(name);
			d.setEmail(Email);
			d.setPhone(phone);
			d.setLatitude(latitude);
			d.setLongitude(longatitude);
			System.out.println("Select Driver Status:");
			System.out.println("1. AVAILABLE");
			System.out.println("2. BUSY");
			System.out.println("3. OFFLINE");

			System.out.print("Enter Your Choice: ");
			int ch = sc.nextInt();

			switch (ch) {
			case 1:
				d.setDriverStatus(DriverStatus.AVALIBLE);
				break;

			case 2:
				d.setDriverStatus(DriverStatus.BUZY);
				break;

			case 3:
				d.setDriverStatus(DriverStatus.OFFLINE);
				break;

			default:
				System.out.println("Invalid Status Choice!");
			}

			em.getTransaction().begin();
			em.persist(d);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(" Enter Valid Values!!");
			return;
		}
	}

	public void displayAll() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
		Root<Driver> root = query.from(Driver.class);
		query.select(root);

		TypedQuery<Driver> typedQuery = em.createQuery(query);

		List<Driver> list = typedQuery.getResultList();

		for (Driver u : list) {
			System.out.println("----------------------");
			System.out.println("Driver ID: " + u.getId());
			System.out.println("Driver Name: " + u.getName());
			System.out.println("Driver Email: " + u.getEmail());
			System.out.println("Driver Phone: " + u.getPhone());
			System.out.println("Driver Status: " + u.getDriverStatus());
			System.out.println("Vehicle ID: " + u.getVehicle().getId());
			System.out.println("Vehicle Type: " + u.getVehicle().getType());
			System.out.println("Vehicle Number: " + u.getVehicle().getVehicle_No());
			System.out.println("Vehicle Name: " + u.getVehicle().getName());

			System.out.println("======================");
		}
	}

	public void displayById(long id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
		Root<Driver> root = query.from(Driver.class);
		query.select(root).where(cb.equal(root.get("id"), id));

		TypedQuery<Driver> typedQuery = em.createQuery(query);

		List<Driver> list = typedQuery.getResultList();

		for (Driver u : list) {
			System.out.println("----------------------");
			System.out.println("Driver ID: " + u.getId());
			System.out.println("Driver Name: " + u.getName());
			System.out.println("Driver Email: " + u.getEmail());
			System.out.println("Driver Phone: " + u.getPhone());
			System.out.println("Driver Status: " + u.getDriverStatus());
			System.out.println("Vehicle ID: " + u.getVehicle().getId());
			System.out.println("Vehicle Type: " + u.getVehicle().getType());
			System.out.println("Vehicle Number: " + u.getVehicle().getVehicle_No());
			System.out.println("Vehicle Name: " + u.getVehicle().getName());
			System.out.println("======================");
		}
	}

	public void updateData(long id) {

		try {
			Driver users = em.find(Driver.class, id);
			if (users != null) {
				int choice;

				while (true) {
					System.out.println("\n===== Update User =====");
					System.out.println("1. Update Name");
					System.out.println("2. Update Email");
					System.out.println("3. Update Phone");
					System.out.println("4. Update Latitude");
					System.out.println("5. Update Longitude");
					System.out.println("6. Update Status");
					System.out.println("7. Exit");
					System.out.print("Enter your choice: ");

					choice = sc.nextInt();
					sc.nextLine(); // clear buffer

					switch (choice) {

					case 1:
						System.out.print("Enter New Name: ");
						users.setName(sc.nextLine());
						break;

					case 2:
						System.out.print("Enter New Email: ");
						users.setEmail(sc.nextLine());
						break;

					case 3:
						System.out.print("Enter New Phone: ");
						users.setPhone(sc.nextLong());
						break;

					case 4:
						System.out.print("Enter New Latitude: ");
						users.setLatitude(sc.nextDouble());
						break;

					case 5:
						System.out.print("Enter New Longitude: ");
						users.setLongitude(sc.nextDouble());
						break;
					case 6:
						System.out.println("Select Driver Status:");
						System.out.println("1. AVAILABLE");
						System.out.println("2. BUSY");
						System.out.println("3. OFFLINE");

						System.out.print("Enter Your Choice: ");
						int ch = sc.nextInt();

						switch (ch) {
						case 1:
							users.setDriverStatus(DriverStatus.AVALIBLE);
							break;

						case 2:
							users.setDriverStatus(DriverStatus.BUZY);
							break;

						case 3:
							users.setDriverStatus(DriverStatus.OFFLINE);
							break;

						default:
							System.out.println("Invalid Status Choice!");
						}
						break;

					case 7:
						System.out.println("Exiting update menu...");
						break;

					default:
						System.out.println("Invalid Choice!");
					}

				}
			} else {
				System.out.println("No data found to update.");
			}

			em.getTransaction().begin();
			em.merge(users);
			em.getTransaction().commit();
			System.out.println("Data update Succcessfully.");

		} catch (Exception e) {
			System.out.println("Enter Valid Values!!");
			return ;
		}
	}

	public void deleteData(long id) {

		Driver users = em.find(Driver.class, id);
		if (users != null) {
			em.getTransaction().begin();
			em.remove(users);
			em.getTransaction().commit();
		} else {
			System.out.println("No data found to Delete.");
		}
	}
}
