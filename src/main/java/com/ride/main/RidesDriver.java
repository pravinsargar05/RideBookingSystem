package com.ride.main;

import java.util.Scanner;



import com.ride.service.DriverService;
import com.ride.service.RidesService;
import com.ride.service.TransactionService;
import com.ride.service.UsersService;
import com.ride.service.VehicleService;

public class RidesDriver {

    public void MeinMenu() {

        Scanner sc = new Scanner(System.in);

        char continueChoice;

        do {

            System.out.println("---------RIDE BOOKING APP-------------");
            System.out.println("1. Manage User");
            System.out.println("2. Manage Driver");
            System.out.println("3. Manage Vehicle");
            System.out.println("4. Manage Rides");
            System.out.println("5. Manage Transaction");
            System.out.println("6. Reports");
            System.out.println("7. Advance Features");
            System.out.println("8. Exit");
            System.out.println("-------------------------------------");
            System.out.print("Enter Your Choice: ");

            int choice =0;
            try {
            	choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Enter Valid Input...");
					MeinMenu();
			}
            switch (choice) {
                case 1:
                    new UsersService().usersMenu();
                    break;

                case 2:
                    new DriverService().driverMenu();
                    break;

                case 3:
                    new VehicleService().VehicleMenu();
                    break;

                case 4:
                	new RidesService().rideMenu();
                    break;

                case 5:
                	new TransactionService().TransactionMenu();
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    System.out.println("---- Thank You ----");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }

            System.out.print("\nDo you want to go in main menu? (y/n): ");
            continueChoice = sc.next().charAt(0);

        } while (continueChoice == 'y' || continueChoice == 'Y');

        System.out.println("---- Thank You for Using Ride Booking App ----");

    } 

    public static void main(String[] args) {
        RidesDriver ridesDriver = new RidesDriver();
        ridesDriver.MeinMenu();
    }
}