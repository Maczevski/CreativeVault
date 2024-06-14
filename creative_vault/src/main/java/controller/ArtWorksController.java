package controller;

import java.util.List;

import model.entities.ArtWorksEntity;
import model.entities.ArtistsEntity;
import model.entities.UsersEntity;
import model.services.ArtWorksService;

public class ArtWorksController {
	private ArtWorksService artWorkService;

	public ArtWorksController() {
		this.artWorkService = new ArtWorksService();
	}

	public ArtWorksEntity createArtWork(ArtWorksEntity artWork) {
		return (ArtWorksEntity) artWorkService.createArtWork(artWork);
	}

	public ArtWorksEntity updateArtWork(ArtWorksEntity artWork) {
		return (ArtWorksEntity) artWorkService.updateArtWork(artWork);
	}

	public void deleteArtWork(ArtWorksEntity artWork) {
		artWorkService.deleteArtWork(artWork);
	}

	public ArtWorksEntity findArtWorkById(Long id) {
		return (ArtWorksEntity) artWorkService.findArtWorkById(id);
	}

	public ArtWorksEntity findArtByTitle(String title) {
		return artWorkService.findArtByTitle(title);
	}

	public List<ArtWorksEntity> findArtsByArtist(ArtistsEntity artist) {
		return artWorkService.findArtsByArtist(artist);
	}

	public List<ArtWorksEntity> findAllArts() {
		return artWorkService.findAllArts();
	}

	public ArtWorksEntity registerArtWork(ArtWorksEntity art, String type, UsersEntity user) {

		return artWorkService.registerArtWork(art, type, user);
	}
	
	public boolean verifyArtWorks(UsersEntity user) {
		return artWorkService.verifyArtWorks(user);
	}
	
	public boolean belongToArtist(UsersEntity user, ArtWorksEntity art) {
		return artWorkService.belongsToArtist(user, art);
	}
	
	
	public ArtWorksEntity findArtistArt(UsersEntity user, String title) {
		return artWorkService.findArtistArt(user, title);
	}

	
}
