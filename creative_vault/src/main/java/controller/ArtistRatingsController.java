package controller;

import java.util.List;

import model.entities.ArtistRatingsEntity;
import model.entities.ArtistsEntity;
import model.entities.UsersEntity;
import model.services.ArtistRatingsService;

public class ArtistRatingsController {

    private ArtistRatingsService artistRatingsService;

    public ArtistRatingsController() {
        this.artistRatingsService = new ArtistRatingsService();
    }

    public ArtistRatingsEntity createRating(ArtistRatingsEntity rating) {
        return artistRatingsService.createRating(rating);
    }

    public ArtistRatingsEntity updateRating(ArtistRatingsEntity rating) {
        return artistRatingsService.updateRating(rating);
    }

    public void deleteRating(ArtistRatingsEntity rating) {
        artistRatingsService.deleteRating(rating);
    }

    public ArtistRatingsEntity findRatingById(Long id) {
        return artistRatingsService.findRatingById(id);
    }

    public List<ArtistRatingsEntity> findRatingsByArtist(ArtistsEntity artist) {
        return artistRatingsService.findRatingsByArtist(artist);
    }
    
    public ArtistRatingsEntity rateArtist(ArtistsEntity artist, UsersEntity user, float score, String comment) {
    	return artistRatingsService.rateArtist(artist, user, score, comment);
    }
    
    public boolean verifyRating(ArtistsEntity artist, UsersEntity user) {
    	return artistRatingsService.verifyRating(artist, user);
    }
}
