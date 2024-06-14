package view;

import java.util.List;
import java.util.Scanner;

import controller.ArtRatingsController;
import controller.ArtWorksController;
import model.entities.ArtRatingsEntity;
import model.entities.ArtWorksEntity;
import model.entities.UsersEntity;

public class ArtWorksMenu {
	
	private ArtWorksController artsController = new ArtWorksController();
	
	private ArtRatingsController artRatingsController = new ArtRatingsController();
	
	private Scanner scan = new Scanner(System.in);

	public void findArtsMenu(UsersEntity user) {
		int option = 0;
		do {
			System.out.println("========================== ART WORKS ==========================\n");
			System.out.println("1. Show all art works");
			System.out.println("2. Find art by title");
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
				showAllArts(artsController.findAllArts());
				break;
			case 2:
				findArtByTitle(user);
				break;
			case 3:
				System.out.println("Returning to initial menu...");
				break;
			default:
				if (option < 0 || option > 3) {
					System.out.println("Invalid value. Try again");
				}
				break;
			}
		} while (option != 3);

	}

	public void showAllArts(List<ArtWorksEntity> artWorks) {
		if (!artWorks.isEmpty() && artWorks != null) {
			System.out.printf("%-20s |%-20s\\n", "TITLE", "PORTFOLIO");
			for (ArtWorksEntity art : artWorks) {
				System.out.printf("%-20s |%-20s\n\n", art.getTitle(), art.getPortfolio().getPortfolioName());
			}
		} else {
			System.out.println("No art works found");
		}
	}

	public void findArtByTitle(UsersEntity user) {

		System.out.print("Enter the title: ");
		String artistName = scan.nextLine();
		ArtWorksEntity art = artsController.findArtByTitle(artistName);
		if (art != null) {
			System.out.printf("\n%-20s |%-20s |\n\n", art.getTitle(),
					art.getPortfolio().getPortfolioName());
			int option = 0;

			do {
				System.out.println(
						"=========================== |" + art.getTitle() + "| ===========================\n");
				System.out.println("1. Show details");
				System.out.println("2. Show ratings");
				System.out.println("3. Rate artwork");
				System.out.println("4. Back to Art Works");
				System.out.println("=========================================s======================\n");
				System.out.print("Choose an option: ");
				try {
					option = Integer.parseInt(scan.nextLine());
				} catch (NumberFormatException e) {
					option = 0;
				}
				switch (option) {
				case 1:
					showArtDetails(art);
					break;
				case 2:
					showArtRatings(art);
					break;
				case 3:
					rateArt(art, user);
					break;
				case 4:
					System.out.println("Thanks for visiting!");
					;
					break;
				default:
					if (option < 0 || option > 4) {
						System.out.println("Invalid value. Try again");
					}
					break;
				}
			} while (option != 4);

		} else {
			System.out.println("\nArtist not found\n");
		}
	}

	public void showArtDetails(ArtWorksEntity art) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Artist: " + art.getPortfolio().getArtist().getArtistName());
		System.out.println("Portfolio: " + art.getPortfolio().getPortfolioName());
		System.out.println("Upload date: " + art.getUploadDate());
		System.out.println("Art type: "+ art.getArtType().getType());
		System.out.println("----------------------------------------------------------------------");

		
	}

	public void showArtRatings(ArtWorksEntity artist) {
		List<ArtRatingsEntity> artRatings = artRatingsController.findRatingsByArtist(artist);
		if (artRatings == null || artRatings.isEmpty()) {
			System.out.println("No ratings found.");
		} else {
			System.out.println("Ratings:\n");
			for (ArtRatingsEntity rating : artRatings) {
				System.out.println(
						rating.getUser().getEmail() + ": " + rating.getScore() + " " + rating.getArtComment());
			}
		}
	}

	public void rateArt(ArtWorksEntity art, UsersEntity user) {
		if (artRatingsController.verifyRating(art, user)) {
			System.out.println("Cannot rate artwork again.");
			return;
		}
		float score;
		String comment;

		do {
			
			ArtRatingsEntity rating = null;
			System.out.print("Score: ");
			try {
				score = Float.parseFloat(scan.nextLine());
			} catch (NumberFormatException e) {
				score = 0;
			}
			
			if (score < 1 || score > 5) {
	            System.out.println("Invalid Score. Enter a value between 1 and 5.");
	            continue; // Reiniciar loop
	        }
			
			System.out.print("Comment: ");
			comment = scan.nextLine();

			rating = artRatingsController.rateArt(art, user, score, comment);
			if (rating != null) {
				System.out.println("Rating saved");
				return;
			} else {
				System.out.println("Rating not saved. Press 0 to go back or another key to try again: ");
				if(scan.next().equals("0")) {
					return;
				}
			}

		} while (true);
	}

}
