package com.ride.service;

import java.util.List;
import java.util.Scanner;

import com.ride.dao.RidesDao;
import com.ride.entity.Rides;
import com.ride.main.RidesDriver;

public class RidesService {

	Scanner sc = new Scanner(System.in);
	RidesDao ridesDao = new RidesDao();

	public void rideMenu() {

		char continueChoice = 'n';
		do {

			System.out.println("\n===== RIDE OPERATION =====");
			System.out.println("1. Book Ride");
			System.out.println("2. Accept Ride ");
			System.out.println("3. Fetch Ride by Users ");
			System.out.println("4. Cancel Ride ");
			System.out.println("5. Update Ride ");
			System.out.println("6. Fetch Cancel Ride ");
			System.out.println("7. Complted Ride ");
			System.out.println("8. Fetch Ride by Driver ");
			System.out.println("9. Return");
			System.out.println("----------------------------");
			System.out.print("Enter Your Choice: ");
			int choice2;
			while (true) {

				if (sc.hasNextInt()) {
					choice2 = sc.nextInt();
					break;
				} else {
					System.out.println("invalid Input!");
					sc.nextLine();
					System.out.print("RE-Enter Your choice : ");
				}
			}

			switch (choice2) {

			case 1:
				System.out.print("Enter User Id: ");
				int userId = sc.nextInt();

				while (!ridesDao.checkUser(userId)) {
					System.out.print("Invalid User Id. Enter Again: ");
					userId = sc.nextInt();
				}

				sc.nextLine(); // clear buffer

				System.out.print("Enter Source: ");
				String source = sc.nextLine();

				System.out.print("Enter Destination: ");
				String destination = sc.nextLine();

				System.out.print("Enter Driver Id: ");
				int driverId = sc.nextInt();

				while (!ridesDao.checkDriver(driverId)) {
					System.out.print("Invalid Driver Id. Enter Again: ");
					driverId = sc.nextInt();
				}

				System.out.print("Enter Distance: ");
				double distance = sc.nextDouble();

				ridesDao.insert(source, destination, distance, userId, driverId);
				break;
			case 2: {
				System.out.print("Enter Ride Id: ");
				int id = sc.nextInt();

				while (!ridesDao.checkRide(id)) {
					System.out.print("Invalid Ride Id. Enter Again: ");
					id = sc.nextInt();
				}

				ridesDao.acceptRide(id);
				break;

			}

			case 3: {
				System.out.print("Enter User Id -:  ");
				int id = sc.nextInt();
				while (true) {
					if (!ridesDao.checkUser(id)) {
						System.out.print("Enter RE-User Id -:  ");
						id = sc.nextInt();
					} else {
						List<Rides> list = ridesDao.displayById(id);

						for (Rides r : list) {

							System.out.println("User ID: " + r.getUsers().getId());
							System.out.println("User ID: " + r.getUsers().getName());
							System.out.println("Driver ID: " + r.getDriver().getId());
							System.out.println("Driver Name: " + r.getDriver().getName());
							System.out.println("Ride ID: " + r.getId());
							System.out.println("Ride Status: " + r.getStatus());
							System.out.println("Ride Source: " + r.getSource());
							System.out.println("Ride Destination: " + r.getDestination());
							System.out.println("Ride Fare: " + r.getFare());
							System.out.println("=============================================");
						}
						System.out.println("Displaying User with ID: " + id);
						break;
					}
				}
			}

			case 4: {
//				System.out.print("Enter User ID: ");
//				int id = sc.nextInt();
//				userDao.deleteData(id);
//				System.out.println("User Deleted Successfully...");
				break;
			}

			case 5: {
//				System.out.print("Enter User ID: ");
//				int id = sc.nextInt();
//				List<Rides> list = ridesDao.displayById(id);
//
//				for (Rides r : list) {
//					System.out.println("User ID: " + u.getId());
//					System.out.println("User Name: " + u.getName());
//					System.out.println("User Email: " + u.getEmail());
//					System.out.println("User Phone: " + u.getPhone());
//					System.out.println("User Wallet Balance: " + u.getWallet_balancd());
//					System.out.println("=============================================");
//				}
//				System.out.println("Displaying User with ID: " + id);
				break;
			}

			case 6: {
//				RidesDriver rideDriver = new RidesDriver();
//				rideDriver.MeinMenu();
//				System.out.println("Returning to Main Menu...");
				break;
			}

			case 7: {
				System.out.println("Thank U...");
				System.exit(0);
			}
			case 8: {
//				usersMenu();
				break;
			}

			default: {
				System.out.println("Invalid Choice...");
			}
			}
			System.out.print("\nDo you want to continue Driver Operations? (y/n): ");
			continueChoice = sc.next().charAt(0);
		} while (continueChoice == 'y' || continueChoice == 'Y');
	}
}
