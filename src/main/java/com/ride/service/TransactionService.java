package com.ride.service;

import java.util.List;
import java.util.Scanner;

import com.ride.dao.TransactionDao;
import com.ride.entity.Rides;
import com.ride.entity.Transaction;
import com.ride.entity.Users;
import com.ride.main.RidesDriver;


public class TransactionService {
	Scanner sc = new Scanner(System.in);
	TransactionDao tr = new TransactionDao();
	RidesDriver dr = new RidesDriver();
	public void TransactionMenu() {

		char continueChoice = 'n';
		do {

			System.out.println("\n===== TRANSACTION OPERATION =====");
			System.out.println("1. Complete Transaction");
			System.out.println("2. Cancel Transaction ");
			System.out.println("3. Fetch By User  ");
			System.out.println("4. Fetch By Ride ");
			System.out.println("5. Fetch Pendding Transaction ");
			System.out.println("6. Fetch Cancel Transaction ");
			System.out.println("7. Fetch completed Transaction ");
			System.out.println("8. Return");
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
				System.out.print("Enter User ID: ");
				long u = sc.nextInt();
				
				System.out.println("Enter Transaction Type: ");
				System.out.println("1.Cash");
				System.out.println("2.UPI");
				System.out.println("3.Card");
				int ch = sc.nextInt();
				
				
				switch (ch) {
				case 1:
					tr.insertCash(u);
					break;
				case 2:
					tr.insertUpi(u);
					break;
				case 3:
					tr.insertCard(u);
					break;

				default:
					break;
				}
				
				break;
			case 2:
				System.out.print("Enter User ID: ");
				long u1 = sc.nextLong();
				tr.cancelTran(u1);
				break;
			case 3:
				System.out.print("Enter User ID: ");
				long u2 = sc.nextLong();

				List<Transaction> list = tr.fetchByUser(u2);

				System.out.println("================================================================================================");
				System.out.printf("%-10s %-15s %-12s %-15s %-15s %-10s%n",
				        "UserID",
				        "UserName",
				        "TransID",
				        "Status",
				        "Type",
				        "Fare");
				System.out.println("------------------------------------------------------------------------------------------------");

				for (Transaction li : list) {

				    List<Rides> rides = li.getUsers().getRides();

				    for (Rides r : rides) {

				        System.out.printf("%-10d %-15s %-12d %-15s %-15s %-10.2f%n",
				                li.getUsers().getId(),
				                li.getUsers().getName(),
				                li.getId(),
				                li.getStatus(),
				                li.getType(),
				                r.getFare());
				    }
				}

				System.out.println("================================================================================================");
				break;
			case 4:
				System.out.print("Enter Ride ID: ");
				long d = sc.nextLong();

				List<Transaction> list2 = tr.fetchByRide(d);

				System.out.println("=================================================================================================");
				System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s %-10s%n",
				        "RideID",
				        "Fare",
				        "UserID",
				        "UserName",
				        "TransID",
				        "Status",
				        "Type");
				System.out.println("-------------------------------------------------------------------------------------------------");

				for (Transaction li : list2) {

				    for (Rides r : li.getUsers().getRides()) {

				        if (r.getId() == d) {

				            System.out.printf("%-10d %-10.2f %-10d %-15s %-15d %-15s %-10s%n",
				                    r.getId(),
				                    r.getFare(),
				                    li.getUsers().getId(),
				                    li.getUsers().getName(),
				                    li.getId(),
				                    li.getStatus(),
				                    li.getType());
				        }
				    }
				}

				System.out.println("=================================================================================================");
				break;
			case 5:
				  List<Users> users= tr.fetchPendding();
				  System.out.println("====================================================================================================================");
				  System.out.printf("%-10s %-15s %-25s %-10s %-20s %-20s %-10s%n",
				          "UserID",
				          "UserName",
				          "Email",
				          "RideID",
				          "Source",
				          "Destination",
				          "Fare");
				  System.out.println("--------------------------------------------------------------------------------------------------------------------");

				  for (Users user : users) {

				      List<Rides> rides = user.getRides();

				      for (Rides r : rides) {

				          System.out.printf("%-10d %-15s %-25s %-10d %-20s %-20s %-10.2f%n",
				                  user.getId(),
				                  user.getName(),
				                  user.getEmail(),
				                  r.getId(),
				                  r.getSource(),
				                  r.getDestination(),
				                  r.getFare());
				      }
				  }

				  System.out.println("====================================================================================================================");
				break;
			case 6:
				List<Transaction> cancel = tr.fetchCancel();
				System.out.println("================================================================================================================");
				System.out.printf("%-10s %-15s %-10s %-15s %-10s %-10s %-15s %-10s%n",
				        "TransID",
				        "Type",
				        "UserID",
				        "UserName",
				        "RideID",
				        "DriverID",
				        "DriverName",
				        "Fare");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");

				for (Transaction li : cancel) {

				    List<Rides> rides = li.getUsers().getRides();

				    for (Rides r : rides) {

				        System.out.printf("%-10d %-15s %-10d %-15s %-10d %-10d %-15s %-10.2f%n",
				                li.getId(),
				                li.getType(),
				                li.getUsers().getId(),
				                li.getUsers().getName(),
				                r.getId(),
				                r.getDriver().getId(),
				                r.getDriver().getName(),
				                r.getFare());
				    }
				}

				System.out.println("================================================================================================================");
				break;
			case 7:	
					List<Transaction> list3 = tr.fetchComplete();
					System.out.println("================================================================================================================");
					System.out.printf("%-10s %-10s %-15s %-15s %-10s %-10s %-15s %-10s%n",
					        "TransID",
					        "UserID",
					        "UserName",
					        "Type",
					        "RideID",
					        "DriverID",
					        "DriverName",
					        "Fare");
					System.out.println("-----------------------------------------------------------------------------------------------------------------");

					for (Transaction li : list3) {

					    List<Rides> rides = li.getUsers().getRides();

					    for (Rides r : rides) {

					        System.out.printf("%-10d %-10d %-15s %-15s %-10d %-10d %-15s %-10.2f%n",
					                li.getId(),
					                li.getUsers().getId(),
					                li.getUsers().getName(),
					                li.getType(),
					                r.getId(),
					                r.getDriver().getId(),
					                r.getDriver().getName(),
					                r.getFare());
					    }
					}

					System.out.println("================================================================================================================");
				break;
			case 8:
				dr.MeinMenu();
				break;
			default:
				System.out.println("Invalid Choice......Enter Valid Data: ");
				TransactionMenu();
				break;
			}System.out.print("\nDo you want to continue Operations? (y/n): ");
			continueChoice = sc.next().charAt(0);
		}while(continueChoice == 'y' || continueChoice == 'Y');
	}
}
