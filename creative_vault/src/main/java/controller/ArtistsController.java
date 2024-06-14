package controller;

import java.util.List;

import model.entities.ArtistsEntity;
import model.entities.UsersEntity;
import model.services.ArtistsService;

public class ArtistsController {
	
private ArtistsService service;
	
	public ArtistsController() {
		this.service = new ArtistsService();
	}
	
	public ArtistsEntity createArtist(ArtistsEntity entity) {
			return (ArtistsEntity)service.createArtist(entity);
	}
	
	public ArtistsEntity updateArtist(ArtistsEntity entity) {
		return (ArtistsEntity)service.updateArtist(entity);
	}
	
	public void deleteUser(ArtistsEntity entity) {
		 service.deleteArtist(entity);
	}
	
	public ArtistsEntity findArtistById(ArtistsEntity entity) {
		return (ArtistsEntity)service.findArtistById(entity);
	}
	
	public ArtistsEntity findArtistByName(String name) {
		return (ArtistsEntity)service.findArtistByName(name);
	}
	
	public boolean verifyName(String name) {
		return service.verifyName(name);
	}
	
	
	public List<ArtistsEntity> findAllArtists() {
		return service.findAllArtists();
	}

	public boolean registerArtist(String name, String portfolioName, String description, UsersEntity user) {
 		return service.registerArtist(name, portfolioName, description, user);
	}
	
}
