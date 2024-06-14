package model.services;

import java.util.List;

import model.entities.ArtRatingsEntity;
import model.entities.ArtWorksEntity;
import model.entities.UsersEntity;
import model.repositories.ArtRatingsRepository;

public class ArtRatingsService {
	private ArtRatingsRepository artRatingsRepository;
	private ArtWorksService artWorksService = new ArtWorksService();

	public ArtRatingsService() {
		this.artRatingsRepository = new ArtRatingsRepository();
	}

	public ArtRatingsEntity createRating(ArtRatingsEntity rating) {
		return (ArtRatingsEntity) artRatingsRepository.create(rating);
	}

	public ArtRatingsEntity updateRating(ArtRatingsEntity rating) {
		return (ArtRatingsEntity) artRatingsRepository.update(rating);
	}

	public void deleteRating(ArtRatingsEntity rating) {
		artRatingsRepository.delete(rating);
	}

	public ArtRatingsEntity findRatingById(Long id) {
		return (ArtRatingsEntity) artRatingsRepository.findById(id);
	}

	public List<ArtRatingsEntity> findRatingsByArt(ArtWorksEntity art) {
		return artRatingsRepository.findRatingsByArt(art);
	}
	
	public ArtRatingsEntity rateArt(ArtWorksEntity art, UsersEntity user, float score, String comment) {
		if(score >= 1 && score <= 5 ) {
			ArtRatingsEntity rating = new ArtRatingsEntity();
        rating.setArtWork(art);
        rating.setUser(user);
        rating.setScore(score);
        rating.setArtComment(comment);
        
        ArtRatingsEntity newRating = (ArtRatingsEntity)artRatingsRepository.create(rating);
        
        //Forçando a atualização
        ArtWorksEntity artToUpdate = artWorksService.findArtWorkById(art.getId());
        List<ArtRatingsEntity> artRatings = art.getArtRatings();
        artRatings.add(newRating);
        artToUpdate.setArtRatings(artRatings);
        artWorksService.updateArtWork(artToUpdate);
        return newRating;
		}else {
			return null;
		}
    }
	
	public boolean verifyRating(ArtWorksEntity art, UsersEntity user) {
		ArtWorksEntity artInDB = artWorksService.findArtWorkById(art.getId());
		List<ArtRatingsEntity> ratings = artInDB.getArtRatings();
		if (!ratings.isEmpty()) {
			for (ArtRatingsEntity rating : ratings) {
				if (rating.getUser().getId() == user.getId()) {
					return true;
				}
			}
		}
		return false;
	}
}
