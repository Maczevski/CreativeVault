package view;

import java.time.LocalDateTime;
import java.util.Scanner;
import controller.ArtWorksController;
import controller.PortfoliosController;
import model.entities.ArtWorksEntity;
import model.entities.PortfoliosEntity;
import model.entities.UsersEntity;

public class PortfolioMenu {

	private Scanner scan = new Scanner(System.in);
	private ArtWorksController artWorksController = new ArtWorksController();
	private PortfoliosController portfoliosController = new PortfoliosController();
	
	public void showPortfolioMenu(UsersEntity user) {

		int option = 0;
		do {
			System.out.println("======================== MY PORTFOLIO =======================\n");
			System.out.println("1. Add ArtWork"); //Done
			System.out.println("2. Update Artwork");//Done
			System.out.println("3. Delete Artwork");//Done
			System.out.println("4. Update Portfolio");//Done
			System.out.println("5. Return to menu");
			System.out.println("===============================================================\n");
			System.out.print("Choose an option: ");

			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}

			switch (option) {
			case 1:
				showAddArtWork(user);
				break;
			case 2:
				updateArtWork(user);
				break;
			case 3:
				showDeleteArtWork(user);
				break;
			case 4:
				showUpdatePortfolio(user);
				break;
			case 5:
				System.out.println("Returning to initial menu...");
				return;
			default:
				if (option < 0 || option > 3) {
					System.out.println("Invalid value. Try again");
				}
				break;
			}
		} while (option != 4);

	}
	
	public void showAddArtWork(UsersEntity user) {
		int option = 0;
		while(option != 7) {
			System.out.println("Select the type of artwork you want to add");
			System.out.println("1. Painting");
			System.out.println("2. Drawing");
			System.out.println("3. Illustration");
			System.out.println("4. Sculpture");
			System.out.println("5. Poem");
			System.out.println("6. Song");
			System.out.println("7. None - Return to my portfolio");
			System.out.print("Choose an option: ");
			
			option = scan.nextInt();
			scan.nextLine();
			
			switch (option) {
			case 1:
				addArtWork(user, "painting");
				break;
			case 2:
				addArtWork(user, "drawing");
				break;
			case 3:
				addArtWork(user, "illustration");
				break;
			case 4:
				addArtWork(user, "sculpture");
				break;
			case 5:
				addArtWork(user, "poem");
				break;
			case 6:
				addArtWork(user, "song");
				break;
			case 7:
				System.out.println("Returning to portfolio...");
				break;

			default:
				System.out.println("Invalid option. Try again");
				break;
			}
			
		}


		
	}

	public void addArtWork(UsersEntity user, String type) {

		System.out.println("Enter artwork title: ");
		String name = scan.nextLine();
		System.out.println("Enter artwork description: ");
		String description = scan.nextLine();

		ArtWorksEntity artWork = new ArtWorksEntity();
		artWork.setTitle(name);
		artWork.setDescription(description);
		artWork.setUploadDate(LocalDateTime.now());

		ArtWorksEntity newArtWork = artWorksController.registerArtWork(artWork, type, user);

		if (newArtWork != null) {
			System.out.println("Artwork added to your portfolio: " + newArtWork.getTitle());
		} else {
			System.out.println("Error adding artwork.");
		}
	}

	//Criar funcao para verificar se existem artes, no service.
	
	public void updateArtWork(UsersEntity user) {
		if(!artWorksController.verifyArtWorks(user)) {
			System.out.println("You don't have arts to uptadate.");
			return;
		}
		do {
			System.out.print("Enter the title of the artwork you want to update: ");
			String title = scan.nextLine();
			
			ArtWorksEntity art = artWorksController.findArtistArt(user, title);
			
			if (artWorksController.belongToArtist(user, art)) {
				int option = 0;
				do {
					System.out.println("What do you want to update?");
					System.out.println("1. Title");
					System.out.println("2. Description");
					System.out.println("3. Nothing. Return to My Portfolio");
					System.out.print("Choose an option: ");
					try {
						option = Integer.parseInt(scan.nextLine());
					} catch (NumberFormatException e) {
						option = 0;
					}
					switch (option) {
					case 1:
						updateArtTitle(art);
						return;
					case 2:
						updateArtDescription(art);
						return;
					case 3:
						System.out.println("Returning to My Portfolio...");
						return;
					default:
						System.out.println("Invalid option. Try again");
						break;
					}
				}while(option != 3);
				
			}else {
				System.out.println("Artwork not found. Try again.");
			}
		}while(true);

	}
	
	public void updateArtTitle(ArtWorksEntity art){
		System.out.println("Enter new title: ");
		String newTitle = scan.nextLine();
		art.setTitle(newTitle);
		
		ArtWorksEntity updatedArt = artWorksController.updateArtWork(art);
		if(updatedArt != null && updatedArt.getTitle().equals(newTitle)) {
			System.out.println("Title updtated");
		}else {
			System.out.println("Error updating art.");
		}
	}
	
	public void updateArtDescription(ArtWorksEntity art){
		System.out.println("Enter new description: ");
		String newDescription = scan.nextLine();
		art.setDescription(newDescription);
		
		ArtWorksEntity updatedArt = artWorksController.updateArtWork(art);
		if(updatedArt != null && updatedArt.getDescription().equals(newDescription)) {
			System.out.println("Description updtated succesfully");
		}else {
			System.out.println("Error updating description.");
		}
	}
	
	public void showDeleteArtWork(UsersEntity user) {
		if(!artWorksController.verifyArtWorks(user)) {
			System.out.println("You don't have arts to uptadate.");
			return;
		}
		
		System.out.print("Enter the title of the artwork you want to delete: ");
		String title = scan.nextLine();
		
		ArtWorksEntity artToDelete = artWorksController.findArtistArt(user, title);
		if(artToDelete != null) {
			artWorksController.deleteArtWork(artToDelete);
			System.out.println("Artwork deleted");
		}else {
			System.out.println("Art not found");
		}
		
	}
	
	public void showUpdatePortfolio(UsersEntity user) {
		
		int option = 0;
		do {
			System.out.println("====================== UPDATE PORTFOLIO ======================\n");
			System.out.println("1. Update name"); 
			System.out.println("2. Update description");
			System.out.println("3. Return to menu");
			System.out.println("===============================================================\n");
			System.out.print("Choose an option: ");
			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}
			
			switch (option) {
			case 1:
				updatePortfolioName(user);
				break;
			case 2:
				updatePortfolioDescription(user);
				break;
			case 3:
				System.out.println("Returning to my portfolio...");
				return;
			default:
				if (option < 0 || option > 3) {
					System.out.println("Invalid value. Try again");
				}
				break;
			}
		} while (option != 4);
	}
	
	public void updatePortfolioName(UsersEntity user){
		System.out.println("Enter a new name for your portfolio: ");
		String newName = scan.nextLine();
		
		user.getArtist().getPortfolio().setPortfolioName(newName);
		
		PortfoliosEntity updatedPortfolio = portfoliosController.updatePortfolioName(user, newName);
		if(updatedPortfolio != null && updatedPortfolio.getPortfolioName() == newName) {
			System.out.println("Portfolio name updtated");
		}else {
			System.out.println("Error updating name.");
		}
		
	}
	
	public void updatePortfolioDescription(UsersEntity user){
		System.out.println("Enter a new description for your portfolio: ");
		String newDescription = scan.nextLine();
		
		user.getArtist().getPortfolio().setPortfolioName(newDescription);
		
		PortfoliosEntity updatedPortfolio = portfoliosController.updatePortfolioDescription(user, newDescription);
		if(updatedPortfolio != null && updatedPortfolio.getDescription() == newDescription) {
			System.out.println("Portfolio description updtated");
		}else {
			System.out.println("Error updating description.");
		}
	}

}
