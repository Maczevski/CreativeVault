package view;


import java.util.Scanner;

import controller.UsersController;
import model.entities.RolesEntity;
import model.entities.UsersEntity;

public class Menu {
	private UsersController userController;
	private ArtistMenu artistMenu = new ArtistMenu();
	private ArtWorksMenu artMenu = new ArtWorksMenu();
	private ProfileMenu profileMenu = new ProfileMenu();
	private PortfolioMenu portfolioMenu = new PortfolioMenu();

	private Scanner scan = new Scanner(System.in);

	public Menu() {
		this.userController = new UsersController();
	}

	public void showInitialMenu() {
		int option;
		do {
			System.out.println();
			System.out.println("================== WELCOME TO CREATIVE VAULT ==================\n");
			System.out.println("1- Login");
			System.out.println("2- Create Account");
			System.out.println("3- Exit");
			System.out.println("\n===============================================================\n");
			System.out.println();
			System.out.print("Choose an option: ");

			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}

			switch (option) {
			case 1: {
				loginMenu();
				break;
			}
			case 2: {
				createAccountMenu();
				break;
			}
			case 3: {
				System.out.println("\nLeaving the system...");
				return;
			}

			default:
				if (option < 0 || option > 2) {
					System.out.println("Invalid value. Try again");
				}
				break;
			}

		} while (option != 0);
	}

	public void loginMenu() {

		boolean verified = false;
		do {
			String email;
			String password;

			System.out.println("============================ LOGIN ============================\n");
			System.out.println("\nEmail: ");
			email = scan.nextLine();
			UsersEntity userLogin = userController.verifyEmail(email);
			if (userLogin != null) {
				System.out.println("\nPassword: ");
				password = scan.nextLine();
				
				
				if (userController.verifyPassword(userLogin, password)) {
					System.out.println("You're logged in!");
					
					for(RolesEntity role : userLogin.getRoles()) {
						System.out.println(role.getId() + " " + role.getRole());
					}
					
					System.out.println("===============================================================\n");
					showUserMenu(userLogin);
					verified = true;
				} else {
					System.out.println("Invalid password. Try again");
					System.out.println("===============================================================\n");
				}

			} else {
				System.out.println("Email not found. Try again");
				System.out.println("===============================================================\n");

			}
		} while (verified == false);

	}

	public void createAccountMenu() {
		boolean registered = false;
		do {
			String email;
			String password;

			System.out.println("======================== CREATE ACCOUNT ========================\n");
			System.out.println("Email: ");
			email = scan.nextLine();
			UsersEntity userInData = userController.verifyEmail(email);
			
			if (userInData == null) {
				System.out.println("\nPassword: ");
				password = scan.nextLine();
				
				UsersEntity user = userController.registerUser(new UsersEntity(email, password));		
				
				if (user != null) {
					System.out.println("User Registered");
					System.out.println("===============================================================\n");
					showUserMenu(user);
					registered = true;
				}else {
					System.err.println("ERROR");
				}
			} else {
				System.out.println("Email already exists. Try again or go to login");
				System.out.println("===============================================================\n");
			}
		} while (registered == false);
	}

	public void showUserMenu(UsersEntity user) {
		
		if (userController.hasArtistRole(user)) {
			showArtistMenu(user);
			System.out.println("Artist");
		} else {
			showDefaultMenu(user);
			System.out.println("Not Artist");
		}
	}

	public void showArtistMenu(UsersEntity user) {
		int option;
		do {
			System.out.println();
			System.out.println("======================== CREATIVE VAULT ======================\n");
			System.out.println("1. Find artists");//Not started
			System.out.println("2. Find art works");//Not started
			System.out.println("3. Update profile");//Not started
			System.out.println("4. My Portfolio");//Not started 
			System.out.println("5. Exit");
			System.out.println("===============================================================\n");
			System.out.println();
			System.out.print("Choose an option: ");

			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}

			switch (option) {
			case 1: {
				artistMenu.findArtistsMenu(user);
				break;
			}
			case 2: {
				artMenu.findArtsMenu(user);
				break;
			}
			case 3: {
				 profileMenu.showProfileMenu(user);
				break;
			}
			case 4: {
				portfolioMenu.showPortfolioMenu(user);;
				break;
			}
			case 5: {
				System.out.println("Leaving system...");
				System.exit(0);
			}

			default:
				if (option < 0 || option > 5) {
					System.out.println("Invalid value. Try again");
				}
				break;
			}
		} while (option != 5);
	}

	public void showDefaultMenu(UsersEntity user) {
		int option;
		do {
			System.out.println();
			System.out.println("======================== CREATIVE VAULT ======================\n");
			System.out.println("1. Find artists");//Done
			System.out.println("2. Find art works");//doing
			System.out.println("3. Update profile");//doing
			System.out.println("4. Become Artist"); //doing
			System.out.println("5. Exit"); // :P
			System.out.println("===============================================================\n");
			System.out.println();
			System.out.print("Choose an option: ");

			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}

			switch (option) {
			case 1: {
				artistMenu.findArtistsMenu(user);
				break;
			}
			case 2: {
				artMenu.findArtsMenu(user);
				break;
			}
			case 3: {
				 profileMenu.showProfileMenu(user);
				break;
			}
			case 4: {
				boolean isArtist = artistMenu.becomeArtist(user);
				if(isArtist){
					user = userController.findUserById(user);
					showArtistMenu(user);
				}
				break;
			}
			case 5: {
				System.out.println("\nLeaving system...");
				System.exit(0);
			}

			default:
				if (option < 0 || option > 5) {
					System.out.println("Invalid value. Try again");
				}
				break;
			}
		} while (option != 5);
	}

}
