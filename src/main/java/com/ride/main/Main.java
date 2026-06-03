package com.ride.main;

import java.util.Scanner;

import com.ride.service.UsersService;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
	
		  do {

	            System.out.println("\n========= MENU =========");
	            System.out.println("1. Users");
	            System.out.println("2. Driver");
	            System.out.println("3. Vehicle");
	            System.out.println("4. Rides");
	            System.out.println("5. Transaction Status");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");

	            choice = sc.nextInt();

	            switch (choice) {

	                case 1: {
	                			UsersService usersService = new UsersService();
								usersService.usersMenu();
	                    System.out.println("Users Module Opened...");
	                    break;
	                }

	                case 2: {
	                    System.out.println("Driver Module Opened...");
	                    break;
	                }

	                case 3: {
	                    System.out.println("Vehicle Module Opened...");
	                    break;
	                }

	                case 4: {
	                    System.out.println("Rides Module Opened...");
	                    break;
	                }

	                case 5: {
	                    System.out.println("Transaction Status Module Opened...");
	                    break;
	                }

	                case 6: {
	                    System.out.println("Thank U...");
	                    System.exit(0);
	                    break;
	                }

	                default: {
	                    System.out.println("Invalid Choice! Please try again.");
	                }
	            }

	        } while (choice != 0);

	        sc.close();
	}
}
