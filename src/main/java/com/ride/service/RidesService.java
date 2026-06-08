package com.ride.service;

import java.util.List;
import java.util.Scanner;

import com.ride.dao.RidesDao;
import com.ride.entity.Rides;
import com.ride.entity.Vehicle;
import com.ride.enums.VehicleType;
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
			System.out.println("7. Fetch Compeleted Ride ");
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
				long userId = sc.nextLong();

				while (!ridesDao.checkUser(userId)) {
					System.out.print("Invalid User Id. Enter Again: ");
					userId = sc.nextLong();
				}
				
				Boolean vs = false;
				while(!vs) {
					System.out.println("Enter VehicleType:  ");
					System.out.println("1.SEDAN");
					System.out.println("2.XUV");
					System.out.println("3.BIKE");
					System.out.println("4.AUTO");
					System.out.println("5.MINI");
					int ch = sc.nextInt();
					switch(ch) {
					case 1:
						List<Vehicle> list = ridesDao.displaySedan();
						if(list == null || list.isEmpty()) {
							System.out.println("No Sedan records found...");
						}else {
							for(Vehicle v: list) {
								System.out.println("Vehicle Id: "+v.getId());
								System.out.println("Vehicle Name: "+v.getName());
								System.out.println("Driver ID: : "+v.getDriver().getId());
								System.out.println("Driver NAme: "+v.getDriver().getName());
								System.out.println("-------------------------------------------");
								vs=true;
							}
						}
						break;
					case 2:
						List<Vehicle> list1 = ridesDao.displayXUV();
						if(list1 == null || list1.isEmpty()) {
							System.out.println("No XUV records found...");
						}else {
							for(Vehicle v: list1) {
								System.out.println("Vehicle Id: "+v.getId());
								System.out.println("Vehicle Name: "+v.getName());
								System.out.println("Driver ID: : "+v.getDriver().getId());
								System.out.println("Driver NAme: "+v.getDriver().getName());
								System.out.println("-------------------------------------------");
								vs=true;
							}
						}
						break;
					case 3:
						List<Vehicle> list3 = ridesDao.displayBike();
	
						if (list3 == null || list3.isEmpty()) {
						    System.out.println("No Bike records found...");
						    
						} else {
						    for (Vehicle v : list3) {
						        System.out.println("Vehicle Id: " + v.getId());
						        System.out.println("Vehicle Name: " + v.getName());
						        System.out.println("Driver ID: " + v.getDriver().getId());
						        System.out.println("Driver Name: " + v.getDriver().getName());
						        System.out.println("-------------------------------------------");
						        vs=true;
						    }
						}
						
						break;
						
					case 4:
						List<Vehicle> list4 = ridesDao.displayAuto();
						if(list4 == null || list4.isEmpty()) {
							System.out.println("No Auto records found...");
						}
						else {
							for(Vehicle v: list4) {
								System.out.println("Vehicle Id: "+v.getId());
								System.out.println("Vehicle Name: "+v.getName());
								System.out.println("Driver ID: : "+v.getDriver().getId());
								System.out.println("Driver NAme: "+v.getDriver().getName());
								System.out.println("-------------------------------------------");
								vs=true;
							}
						}
						break;
						
					case 5:
						List<Vehicle> list5 = ridesDao.displayMini();
						if(list5 == null || list5.isEmpty()) {
							System.out.println("No Mini records found...");
						}else {
							for(Vehicle v: list5) {
								System.out.println("Vehicle Id: "+v.getId());
								System.out.println("Vehicle Name: "+v.getName());
								System.out.println("Driver ID: : "+v.getDriver().getId());
								System.out.println("Driver NAme: "+v.getDriver().getName());
								System.out.println("-------------------------------------------");
								vs=true;
							}
						}
						break;
					}
			}
				System.out.print("Enter Source: ");
				String source = sc.nextLine();
				sc.nextLine();
				System.out.print("Enter Destination: ");
				String destination = sc.nextLine();
				
				System.out.print("Enter Vehicle Id: ");
				long vehicleId = sc.nextInt();

				while (!ridesDao.checkVehicle(vehicleId)) {
					System.out.print("Invalid Vehicle Id. Enter Again: ");
					vehicleId = sc.nextLong();
				}

				System.out.print("Enter Distance: ");
				double distance = sc.nextDouble();

				ridesDao.insert(source, destination, distance, userId, vehicleId);
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

			case 3:{ 
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
							System.out.println("Driver Status: " + r.getDriver().getDriverStatus());
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
				}break;
				}

			case 4: {
				System.out.print("Enter Ride ID: ");
				 int id = sc.nextInt();
				ridesDao.cancelRide(id);
				break;
			}

			case 5: {
				System.out.print("Enter Ride ID: ");
				int id = sc.nextInt();
				if(ridesDao.checkRide(id)) {
					System.out.print("Enter Ride Source: ");
					source = sc.nextLine();
					sc.nextLine();
					System.out.print("Enter Ride Destination: ");
					destination = sc.nextLine();
					System.out.print("Enter Ride Distance: ");
					 distance = sc.nextDouble();
					ridesDao.updateRide(id, source, destination, distance);
				}
				break;
				
			}
			case 6: {
		
						List<Rides> list = ridesDao.displayCancel();

						for (Rides r : list) {

							System.out.println("User ID: " + r.getUsers().getId());
							System.out.println("User ID: " + r.getUsers().getName());
							System.out.println("Driver ID: " + r.getDriver().getId());
							System.out.println("Driver Name: " + r.getDriver().getName());
							System.out.println("Driver Status: " + r.getDriver().getDriverStatus());
							System.out.println("Ride ID: " + r.getId());
							System.out.println("Ride Status: " + r.getStatus());
							System.out.println("Ride Source: " + r.getSource());
							System.out.println("Ride Destination: " + r.getDestination());
							System.out.println("Ride Fare: " + r.getFare());
							System.out.println("=============================================");
						
						System.out.println("Displaying Cancel Rides....");
						break;
					}
						break;
				}
			case 7: {
				
				List<Rides> list = ridesDao.displayComplete();

				for (Rides r : list) {

					System.out.println("User ID: " + r.getUsers().getId());
					System.out.println("User ID: " + r.getUsers().getName());
					System.out.println("Driver ID: " + r.getDriver().getId());
					System.out.println("Driver Name: " + r.getDriver().getName());
					System.out.println("Driver Status: " + r.getDriver().getDriverStatus());
					System.out.println("Ride ID: " + r.getId());
					System.out.println("Ride Status: " + r.getStatus());
					System.out.println("Ride Source: " + r.getSource());
					System.out.println("Ride Destination: " + r.getDestination());
					System.out.println("Ride Fare: " + r.getFare());
					System.out.println("=============================================");
				
				System.out.println("Displaying....");
				break;
			}
				break;
		}
			case 8: {
				System.out.print("Enter Driver Id -:  ");
				long id = sc.nextInt();
				while (true) {
					if (!ridesDao.checkDriver(id)) {
						System.out.print("Enter RE-Driver Id -:  ");
						id = sc.nextInt();
					} else {
						List<Rides> list = ridesDao.displayDriverId(id);

						for (Rides r : list) {

							System.out.println("User ID: " + r.getUsers().getId());
							System.out.println("User Name: " + r.getUsers().getName());
							System.out.println("Driver ID: " + r.getDriver().getId());
							System.out.println("Driver Name: " + r.getDriver().getName());
							System.out.println("Driver Status: " + r.getDriver().getDriverStatus());
							System.out.println("Ride ID: " + r.getId());
							System.out.println("Ride Status: " + r.getStatus());
							System.out.println("Ride Source: " + r.getSource());
							System.out.println("Ride Destination: " + r.getDestination());
							System.out.println("Ride Fare: " + r.getFare());
							System.out.println("=============================================");
						}
						System.out.println("Displaying Ride with Driver ID: " + id);
						break;
					}
				}
				break;
			}
			case 9:{
				RidesDriver rideDriver = new RidesDriver();
				rideDriver.MeinMenu();
				System.out.println("Returning to Main Menu...");
				break;
			}

			default: {
				System.out.println("Invalid Choice...");
			}
			}
			System.out.print("\nDo you want to continue Operations? (y/n): ");
			continueChoice = sc.next().charAt(0);
		} while (continueChoice == 'y' || continueChoice == 'Y');
	}
}
