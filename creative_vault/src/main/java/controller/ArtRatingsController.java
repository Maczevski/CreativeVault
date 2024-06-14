package controller;

import java.util.List;

import model.entities.ArtRatingsEntity;
import model.entities.ArtWorksEntity;
import model.entities.UsersEntity;
import model.services.ArtRatingsService;

public class ArtRatingsController {
	private ArtRatingsService artRatingsService;

	public ArtRatingsController() {
		this.artRatingsService = new ArtRatingsService();
	}

	public ArtRatingsEntity createRating(ArtRatingsEntity rating) {
		return (ArtRatingsEntity) artRatingsService.createRating(rating);
	}

	public ArtRatingsEntity updateRating(ArtRatingsEntity rating) {
		return (ArtRatingsEntity) artRatingsService.updateRating(rating);
	}

	public void deleteRating(ArtRatingsEntity rating) {
		artRatingsService.deleteRating(rating);
	}

	public ArtRatingsEntity findRatingById(Long id) {
		return (ArtRatingsEntity) artRatingsService.findRatingById(id);
	}

	public List<ArtRatingsEntity> findRatingsByArtist(ArtWorksEntity art) {
		return artRatingsService.findRatingsByArt(art);
	}

	public ArtRatingsEntity rateArt(ArtWorksEntity art, UsersEntity user, float score, String comment) {
		return artRatingsService.rateArt(art, user, score, comment);
	}
	
	public boolean verifyRating(ArtWorksEntity art, UsersEntity user) {
		
		return artRatingsService.verifyRating(art, user);
		}
	}
