package com.ride.service;

import java.util.Scanner;

import com.ride.dao.DriverDao;
import com.ride.main.RidesDriver;

public class DriverService {
	Scanner sc = new Scanner(System.in);
    DriverDao driverDao = new DriverDao();
    
    public void driverMenu() {

    	
        int choice=0;
        char continueChoice;
        do {

            System.out.println("\n===== DRIVER OPERATIONS =====");
            System.out.println("1. Register Driver ");
            System.out.println("2. View All Driver ");
            System.out.println("3. Update Driver ");
            System.out.println("4. Delete Driver ");
            System.out.println("5. View Driver By Id ");
            System.out.println("6. Back to Main Menu ");
            System.out.println("7. Exit");
            System.out.println("----------------------------");
            System.out.print("Enter Your Choice: ");

            try {
            	choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Enter Valid Input...");
				driverMenu();
			}

            switch (choice) {

                case 1: { driverDao.insert();
                    System.out.println("Driver Registered Successfully...");
                    break;
                }

                case 2:{
							driverDao.displayAll();
						System.out.println("Displaying All Drivers...");
						break;
                }

                case 3: {
                	System.out.print("Enter Driver ID: ");
            		long id = sc.nextInt();
            		driverDao.updateData(id);
                    System.out.println("Driver Data Updated Successfully...");
                    break;
                }

                case 4: {
                	System.out.print("Enter Driver ID: ");
            		long id = sc.nextInt();
            		driverDao.deleteData(id);
                	System.out.println("Driver Deleted Successfully...");
                    break;
                }
                
                case 5: {
                		System.out.print("Enter Driver ID: ");
                		long id = sc.nextInt();
                		driverDao.displayById(id);
	                  System.out.println("Displaying Driver with ID: " + id);
                	break;
                }
                
                case 6: { RidesDriver rideDriver = new RidesDriver();
                			rideDriver.MeinMenu();
                	System.out.println("Returning to Main Menu...");
                	break;
                }
                
                case 7: {
                	System.out.println("Thank U...");
                	System.exit(0);
                }

                default: {
                    System.out.println("Invalid Choice...");
                }
            }
            System.out.print("\nDo you want to continue Operations? (y/n): ");
             continueChoice = sc.next().charAt(0);

        } while (continueChoice == 'y' || continueChoice == 'Y');
        System.out.println("Returning to Main Menu...");
    }
}
