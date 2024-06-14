package model.services;

import java.util.List;

import model.entities.ArtistsEntity;
import model.entities.PortfoliosEntity;
import model.entities.UsersEntity;
import model.repositories.ArtistsRepository;

public class ArtistsService {

	private ArtistsRepository repository;
	private UsersService usersService;
	private PortfoliosService portfoliosService;

	public ArtistsService() {
		this.repository = new ArtistsRepository();
		this.usersService = new UsersService();
		this.portfoliosService = new PortfoliosService();
	}

	public ArtistsEntity createArtist(ArtistsEntity entity) {
		return (ArtistsEntity) repository.create(entity);
	}

	public ArtistsEntity updateArtist(ArtistsEntity entity) {
		return (ArtistsEntity) repository.update(entity);
	}

	public void deleteArtist(ArtistsEntity entity) {
		repository.delete(entity);
	}

	public ArtistsEntity findArtistById(ArtistsEntity entity) {
		return (ArtistsEntity) repository.findById(entity.getId());
	}

	public ArtistsEntity findArtistByName(String name) {
		return (ArtistsEntity) repository.findByName(name);
	}

	public List<ArtistsEntity> findAllArtists() {
		return repository.findAll();
	}

	public boolean verifyName(String name) {
		if (findArtistByName(name) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean registerArtist(String name, String portfolioName, String description, UsersEntity user) {
		UsersEntity userInDB = usersService.setArtistRole(user);

		if (userInDB != null) {
			ArtistsEntity artist = new ArtistsEntity();
			artist.setArtistName(name);
			artist.setUser(userInDB);

			ArtistsEntity newArtist = createArtist(artist);

			if (newArtist != null) {
				PortfoliosEntity portfolio = new PortfoliosEntity();
				portfolio.setPortfolioName(portfolioName);
				portfolio.setDescription(description);
				portfolio.setArtist(newArtist);
				portfoliosService.createPortfolio(portfolio);
				newArtist.setPortfolio(portfolio);
				updateArtist(newArtist);
				return true;
			} else {
				usersService.removeArtistRole(userInDB);
				return false;
			}
		}

		return false;
	}

}
