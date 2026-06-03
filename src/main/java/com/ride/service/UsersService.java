package com.ride.service;

import com.ride.dao.UsersDao;
import com.ride.entity.Users;
import com.ride.main.RidesDriver;

import java.util.List;
import java.util.Scanner;

public class UsersService {

	Scanner sc = new Scanner(System.in);
	UsersDao userDao = new UsersDao();

	public void usersMenu() {

		
		char continueChoice = 'n';
		do {

			System.out.println("\n===== USERS OPERATION =====");
			System.out.println("1. Register User");
			System.out.println("2. View All Users ");
			System.out.println("3. Update Users ");
			System.out.println("4. Delete Users ");
			System.out.println("5. View User By Id ");
			System.out.println("6. Back to Main Menu ");
			System.out.println("7. Exit");
			System.out.println("----------------------------");
			System.out.print("Enter Your Choice: ");

			int choice2;
			while(true) {
				
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

			case 1: {
				
				System.out.println("Enter User Name -:  ");
				String name = sc.nextLine();
//				for(int i=0;i<name.length();i++) {
//					char ch = name.charAt(i);
//					if(!Character.isLetter(ch)) {
//						System.out.println("Invalid Name..Enter Agian..");
//						System.out.println("Enter User rRE-Name -:  ");
//						name = sc.nextLine();
//					}
//				}
				sc.nextLine();
				System.out.println("Enter User Email -: ");
				String Email = sc.nextLine();
				System.out.println("Enter User Phone -: ");
				long phone = sc.nextLong();
				System.out.println("Enter Wallet Balance -: ");
				double W_bal = sc.nextDouble();
				userDao.insert(name,Email,phone,W_bal);
				break;
			}

			case 2: {
				List<Users> list = userDao.displayAll();
				for(Users u:list) {
					System.out.println("User ID: "+u.getId());
					System.out.println("User Name: "+u.getName());
					System.out.println("User Email: "+u.getEmail());
					System.out.println("User Phone: "+u.getPhone());
					System.out.println("User Wallet Balance: "+u.getWallet_balancd());
					System.out.println("=============================================");
				}
				System.out.println("Displaying All Users...");
				break;
			}

			case 3: {
				System.out.print("Enter User ID: ");
				int id = sc.nextInt();

				if (userDao.checkUser(id)) {

				    int choice;

				    do {
				        System.out.println("\n===== UPDATE USER =====");
				        System.out.println("1. Update Name");
				        System.out.println("2. Update Email");
				        System.out.println("3. Update Phone");
				        System.out.println("4. Save & Exit");
				        System.out.print("Enter Choice: ");

				        choice = sc.nextInt();
				        sc.nextLine();

				        switch (choice) {

				        case 1:
				            System.out.print("Enter New Name: ");
				            String name = sc.nextLine();
				            userDao.updateName(id, name);
				            break;

				        case 2:
				            System.out.print("Enter New Email: ");
				            String email = sc.nextLine();
				            userDao.updateEmail(id, email);
				            break;

				        case 3:
				            System.out.print("Enter New Phone: ");
				            long phone = sc.nextLong();
				            userDao.updatePhone(id, phone);
				            break;

				        case 4:
				            System.out.println("Exit...");
				            usersMenu();
				            break;

				        default:
				            System.out.println("Invalid Choice!");
				        }

				    } while (choice != 4);

				} else {
				    System.out.println("User not found.");
				    break;
				}
			}

			case 4: {
				System.out.print("Enter User ID: ");
				int id = sc.nextInt();
				userDao.deleteData(id);
				System.out.println("User Deleted Successfully...");
				break;
			}

			case 5: {
				System.out.print("Enter User ID: ");
				int id = sc.nextInt();
				List<Users> list = userDao.displayById(id);
				
				for(Users u:list) {
					System.out.println("User ID: "+u.getId());
					System.out.println("User Name: "+u.getName());
					System.out.println("User Email: "+u.getEmail());
					System.out.println("User Phone: "+u.getPhone());
					System.out.println("User Wallet Balance: "+u.getWallet_balancd());
					System.out.println("=============================================");
				}
				System.out.println("Displaying User with ID: " + id);
				break;
			}

			case 6: {
				RidesDriver rideDriver = new RidesDriver();
				rideDriver.MeinMenu();
				System.out.println("Returning to Main Menu...");
				break;
			}

			case 7: {
				System.out.println("Thank U...");
				System.exit(0);
			}
			case 8: {
				usersMenu();
				break;
			}

			default: {
				System.out.println("Invalid Choice...");
			}
			}
			System.out.print("\nDo you want to continue Driver Operations? (y/n): ");
			continueChoice = sc.next().charAt(0);
		}while(continueChoice=='y'||continueChoice=='Y');
}

}