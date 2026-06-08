package com.ride.service;

import java.util.Scanner;


import com.ride.dao.VehicleDao;
import com.ride.main.RidesDriver;

public class VehicleService {
	Scanner sc = new Scanner(System.in);
    VehicleDao vehicleDao = new VehicleDao();
    
    public void VehicleMenu() {

    	
        int choice=0;
        char continueChoice;
        do {

            System.out.println("\n===== VEHICLE OPERATIONS =====");
            System.out.println("1. Assign Vehicle ");
            System.out.println("2. View All Vehicle ");
            System.out.println("3. Update Vehicle ");
            System.out.println("4. Delete Vehicle ");
            System.out.println("5. View By Id Vehicle ");
            System.out.println("6. Back to Main Menu ");
            System.out.println("7. Exit");
            System.out.println("----------------------------");
            System.out.print("Enter Your Choice: ");

            try {
            	choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Enter Valid Input");
				VehicleMenu();
			}
           
            switch (choice) {

                case 1: { 
		                System.out.print("Enter Driver ID: ");
		        		long id = sc.nextInt();
		        		vehicleDao.insert(id);
                    System.out.println(" Vehicle Assign Successfully...");
                    break;
                }

                case 2:{
                		vehicleDao.displayAll();
						System.out.println("Displaying All Vehicles...");
						break;
                }

                case 3: {
                	System.out.print("Enter Veicle ID: ");
            		long id = sc.nextLong();
            		vehicleDao.updateData(id);
                    System.out.println("Vehicle Data Updated Successfully...");
                    break;
                }

                case 4: {
                	System.out.print("Enter Vehicle ID: ");
            		long id = sc.nextLong();
            		vehicleDao.deleteData(id);
                	System.out.println("Vehicle Data Deleted Successfully...");
                    break;
                }
                
                case 5: {
                		System.out.print("Enter Vehicle ID: ");
                		long id = sc.nextLong();
                		vehicleDao.displayById(id);
	                  System.out.println("Displaying Vehicle Data with ID: " + id);
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
            }System.out.print("\nDo you want to continue Vehicle Operations? (y/n): ");
            continueChoice = sc.next().charAt(0);

        } while (continueChoice == 'y' || continueChoice == 'Y');
    }
}
