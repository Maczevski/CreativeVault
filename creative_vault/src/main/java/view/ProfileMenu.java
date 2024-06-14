package view;

import java.util.Scanner;

import controller.UsersController;
import model.entities.UsersEntity;

public class ProfileMenu {
	UsersController usersController = new UsersController();
	Scanner scan = new Scanner(System.in);

	public void showProfileMenu(UsersEntity User) {

		int option = 0;
		do {
			System.out.println("======================== UPDATE PROFILE =======================\n");
			System.out.println("1. Update Email");
			System.out.println("2. Update Password");
			System.out.println("3. Delete Profile");
			System.out.println("4. Return to menu");
			System.out.println("===============================================================\n");
			System.out.print("Choose an option: ");
			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}
			switch (option) {
			case 1:
				updateUserEmail(User);
				break;
			case 2:
				updateUserPassword(User);
				break;
			case 3:
				deleteUserProfile(User);
				break;
			case 4:
				System.out.println("Returning to initial menu...");
				break;
			default:
				if (option < 0 || option > 3) {
					System.out.println("Invalid value. Try again");
				}
				break;
			}
		} while (option != 4);

	}

	public void updateUserEmail(UsersEntity user) {

		boolean updated = false;

		do {
			System.out.println("Enter your new email: ");
			String email = scan.next();
			scan.nextLine();


			if (usersController.verifyEmail(email) == null) {
				
				UsersEntity updatedUser = usersController.updateEmail(user, email);
				if (updatedUser != null) {
					System.out.println("Email updated successfully to: " + email);
					updated = true;
				} else {
					System.out.println("Error updating. Press 0 to go back or another key to try again");
					if (scan.next().equals("0")) {
						return;
					}
				}
			} else {
				System.out.println("Email already registered. Try another one");
			}

		} while (!updated);

	}

	public void updateUserPassword(UsersEntity user) {

		boolean updated = false;

		do {
			System.out.println("Enter your password: ");
			String oldPassword = scan.nextLine();
			if (usersController.verifyPassword(user, oldPassword)) {
				System.out.println("Enter your new password: ");
				String newPassword = scan.nextLine();

				UsersEntity updatedUser = usersController.updatePassword(user, newPassword);

				if (updatedUser != null) {
					System.out.println("Password updated successfully");
					updated = true;
				} else {
					System.out.println("Error updating password. Press 0 to go back or another key to try again");
					if (scan.next().equals("0")) {
						scan.nextLine();
						return;
					}
				}
			} else {
				System.out.println("Invalid password. Try again");
			}
		} while (!updated);

	}

	public void deleteUserProfile(UsersEntity user) {
		int option = 0;
		
		while(option != 2) {
			
			System.out.println("Are you sure you want to delete your profile? :( ");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.print("Your choice: ");
			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}
			if(option == 1) {
				do {
					
					System.out.print("Enter your password: ");
					String password = scan.nextLine();
					
					if(usersController.verifyPassword(user, password)) {
						
						boolean deleted = usersController.deleteUserProfile(user);
						if(deleted) {
							System.out.println("\nGoodBye!\n");
							System.exit(0);
						}else {
							System.out.println("\nUser not deleted\n");
						}
					}else {
						System.out.println("\nIncorrect password. Try again\n");
					}
					
				}while(true);
				
			}else if(option == 2) {
				return;
			}else {
				System.out.println("Invalid option");
			}
		}

	}

}
