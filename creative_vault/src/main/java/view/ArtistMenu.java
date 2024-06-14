package view;

import java.util.List;
import java.util.Scanner;

import controller.ArtWorksController;
import controller.ArtistRatingsController;
import controller.ArtistsController;
import model.entities.ArtWorksEntity;
import model.entities.ArtistRatingsEntity;
import model.entities.ArtistsEntity;
import model.entities.UsersEntity;

public class ArtistMenu {

	private ArtistsController artistController = new ArtistsController();
	private ArtWorksController artController = new ArtWorksController();
	private ArtistRatingsController artistRatingController = new ArtistRatingsController();
	private Scanner scan = new Scanner(System.in);

	public boolean becomeArtist(UsersEntity user) {
		do {

			System.out.print("Name: ");
			String name = scan.nextLine();

			if (!artistController.verifyName(name)) {

				System.out.print("Enter your portfolio's name: ");
				String portfolioName = scan.nextLine();
				System.out.print("Enter your portfolio's description: ");
				String description = scan.nextLine();

				boolean success = artistController.registerArtist(name, portfolioName, description, user);

				if (success) {
					System.out.println("Artist Registered");
					return true;
				} else {
					System.err.println("\nError. Artist not registred :(\n");
					return false;
				}

			} else {
				System.out.println("Artist name alredy exists. Try again");
			}

		} while (true);
	}

	public void findArtistsMenu(UsersEntity user) {
		int option = 0;
		do {
			System.out.println("=========================== ARTISTS ===========================\n");
			System.out.println("1. Show all artists");
			System.out.println("2. Find artist by name");
			System.out.println("3. Return to menu");
			System.out.println("===============================================================\n");
			try {
				option = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				option = 0;
			}
			switch (option) {
			case 1:
				showAllArtists();
				break;
			case 2:
				findArtistByName(user);
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

	public void showAllArtists() {
		List<ArtistsEntity> artists =  artistController.findAllArtists();
		if (!artists.isEmpty() && artists != null) {
			System.out.printf("%-20s |%-20s |%20s\\n", "NAME", "PORTFOLIO", "ART WORKS NUMBER");
			for (ArtistsEntity artist : artists) {
				System.out.printf("%-20s |%-20s |%-2d\n\n", artist.getArtistName(),
						artist.getPortfolio().getPortfolioName(), artist.getPortfolio().getArtWorks().size());
			}
		} else {
			System.out.println("No artists found");
		}
	}

	public void findArtistByName(UsersEntity user) {

		System.out.println("Enter artist name: ");
		String artistName = scan.nextLine();
		
		ArtistsEntity artist = artistController.findArtistByName(artistName);
		if (artist != null) {
			System.out.printf("\n%-20s |%-20s |%-2d\n", artist.getArtistName(),
					artist.getPortfolio().getPortfolioName(), artist.getPortfolio().getArtWorks().size());
			int option = 0;

			do {
				System.out.println(
						"=========================== |" + artist.getArtistName() + "| ===========================\n");
				System.out.println("1. Show arts");
				System.out.println("2. Show ratings");
				System.out.println("3. Rate artist");
				System.out.println("4. Back to Artists");
				System.out.println("===============================================================\n");
				try {
					option = Integer.parseInt(scan.nextLine());
				} catch (NumberFormatException e) {
					option = 0;
				}
				switch (option) {
				case 1:
					showArtistArts(artist);
					break;
				case 2:
					showArtistRatings(artist);
					break;
				case 3:
					rateArtist(artist, user);
					break;
				case 4:
					System.out.println(artist.getArtistName() + " says goodbye! =)");
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

	public void showArtistArts(ArtistsEntity artist) {
		List<ArtWorksEntity> artistArts = artController.findArtsByArtist(artist);
		if (artistArts == null || artistArts.isEmpty()) {
			System.out.println("No art works found.");
		} else {
			System.out.println("Art Works:\n");
			for (ArtWorksEntity art : artistArts) {
				System.out.println(art.getArtType().getType() + ": " + art.getTitle());
			}
		}
	}

	public void showArtistRatings(ArtistsEntity artist) {
		List<ArtistRatingsEntity> artistRating = artistRatingController.findRatingsByArtist(artist);
		if (artistRating == null || artistRating.isEmpty()) {
			System.out.println("No ratings found.");
		} else {
			System.out.println("Ratings:\n");
			for (ArtistRatingsEntity rating : artistRating) {
				System.out.println(
						rating.getUser().getEmail() + ": " + rating.getScore() + " " + rating.getArtistComment());
			}
		}
	}

	public void rateArtist(ArtistsEntity artist, UsersEntity user) {

		if(artistRatingController.verifyRating(artist, user)) {
			System.out.println("Cannot rate artist again.");
			return;
		}
		
		float score;
		String comment;

		do {
			ArtistRatingsEntity rating = null;
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
			
			rating = artistRatingController.rateArtist(artist, user, score, comment);

			if (rating != null) {
				System.out.println("Rating saved");
				return;
			} else {
				System.out.println("Rating not saved. Press 0 to go back or another key to try again");
				if(scan.next().equals("0")) {
					return;
				}			}

		} while (true);

	}

}
