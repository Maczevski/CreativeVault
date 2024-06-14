package model.services;

import java.util.List;

import model.entities.ArtistRatingsEntity;
import model.entities.ArtistsEntity;
import model.entities.UsersEntity;
import model.repositories.ArtistRatingsRepository;

public class ArtistRatingsService {
	private ArtistRatingsRepository artistRatingsRepository;
	private ArtistsService artistsService;

	public ArtistRatingsService() {
		this.artistRatingsRepository = new ArtistRatingsRepository();
		this.artistsService = new ArtistsService();
	}

	public ArtistRatingsEntity createRating(ArtistRatingsEntity rating) {
		return (ArtistRatingsEntity) artistRatingsRepository.create(rating);
	}

	public ArtistRatingsEntity updateRating(ArtistRatingsEntity rating) {
		return (ArtistRatingsEntity) artistRatingsRepository.update(rating);
	}

	public void deleteRating(ArtistRatingsEntity rating) {
		artistRatingsRepository.delete(rating);
	}

	public ArtistRatingsEntity findRatingById(Long id) {
		return (ArtistRatingsEntity) artistRatingsRepository.findById(id);
	}

	public List<ArtistRatingsEntity> findRatingsByArtist(ArtistsEntity artist) {
		return artistRatingsRepository.findRatingsByArtist(artist);
	}

	public ArtistRatingsEntity rateArtist(ArtistsEntity artist, UsersEntity user, float score, String comment) {
		if (score >= 1 && score <= 5) {
			ArtistRatingsEntity rating = new ArtistRatingsEntity();
			rating.setArtist(artist);
			rating.setUser(user);
			rating.setScore(score);
			rating.setArtistComment(comment);
			
			ArtistRatingsEntity newRating = (ArtistRatingsEntity) artistRatingsRepository.create(rating);
			
	        //Forçando a atualização
			ArtistsEntity artistToUpdate = artistsService.findArtistById(artist);
			List<ArtistRatingsEntity> artistRatings = artistToUpdate.getArtistRatings();
			artistRatings.add(newRating);
			artistToUpdate.setArtistRatings(artistRatings);
			artistsService.updateArtist(artistToUpdate);			
			return newRating;
		} else {
			return null;
		}
	}

	public boolean verifyRating(ArtistsEntity artist, UsersEntity user) {
		ArtistsEntity artistInDB = artistsService.findArtistById(artist);
		
		List<ArtistRatingsEntity> ratings = artistInDB.getArtistRatings();

		if (!ratings.isEmpty()) {
			for (ArtistRatingsEntity rating : ratings) {
				if (rating.getUser().getId() == user.getId()) {
					return true;
				}
			}
		}
		return false;
	}

}
