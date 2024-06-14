package model.services;

import java.util.List;

import model.entities.ArtTypesEntity;
import model.entities.ArtWorksEntity;
import model.entities.ArtistsEntity;
import model.entities.PortfoliosEntity;
import model.entities.UsersEntity;
import model.repositories.ArtWorksRepository;

public class ArtWorksService {
	private ArtWorksRepository artWorksRepository;
	private ArtTypesService artTypesService = new ArtTypesService();
	private UsersService usersService = new UsersService();

	public ArtWorksService() {
		this.artWorksRepository = new ArtWorksRepository();
	}

	public ArtWorksEntity createArtWork(ArtWorksEntity artWork) {
		return (ArtWorksEntity) artWorksRepository.create(artWork);
	}

	public ArtWorksEntity updateArtWork(ArtWorksEntity artWork) {
		return (ArtWorksEntity) artWorksRepository.update(artWork);
	}

	public void deleteArtWork(ArtWorksEntity artWork) {
		ArtWorksEntity artInDB = findArtWorkById(artWork.getId());
		artWorksRepository.delete(artInDB);
	}

	public ArtWorksEntity findArtWorkById(Long id) {
		return (ArtWorksEntity) artWorksRepository.findById(id);
	}

	public ArtWorksEntity findArtByTitle(String title) {
		return artWorksRepository.findByTitle(title);
	}
	
	public List<ArtWorksEntity> findArtsByArtist(ArtistsEntity artist) {
		return artWorksRepository.findArtsByArtist(artist);
	}
	
	public List<ArtWorksEntity> findAllArts(){
		return artWorksRepository.findAll();
	}
	
	public ArtWorksEntity registerArtWork(ArtWorksEntity art, String type, UsersEntity user ) {
		UsersEntity userInDB = usersService.findUserById(user);
		ArtTypesEntity artType = artTypesService.findArtTypeByType(type);
		
		art.setPortfolio(userInDB.getArtist().getPortfolio());

		if(artType != null) {
			art.setArtType(artType);
			return createArtWork(art);
		}
		return null;
	}
	
	public boolean verifyArtWorks(UsersEntity user) {
		UsersEntity userInDB = usersService.findUserById(user);
		if (userInDB != null && userInDB.getArtist() != null) {
	        ArtistsEntity artist = userInDB.getArtist();
	        PortfoliosEntity portfolio = artist.getPortfolio();
	        
	        if (portfolio != null && portfolio.getArtWorks() != null && !portfolio.getArtWorks().isEmpty()) {
	            return true; 
	        }
	    }
		return false;
	}
	
	public boolean belongsToArtist(UsersEntity user, ArtWorksEntity art) {
		if(art == null) {
			return false;
		}
		UsersEntity userInDB = usersService.findUserById(user);
		List<ArtWorksEntity> userArts = userInDB.getArtist().getPortfolio().getArtWorks();
		for(ArtWorksEntity a : userArts ) {
			if(a == art) {
				return true;
			}
		}
		return false;
	}
	
	public ArtWorksEntity findArtistArt(UsersEntity user, String title) {
		UsersEntity userInDB = usersService.findUserById(user);
		List<ArtWorksEntity> userArts = userInDB.getArtist().getPortfolio().getArtWorks();
		for(ArtWorksEntity a : userArts ) {
			if(a.getTitle().equals(title)) {
				return a;
			}
		}
		return null;
	}
	
}
