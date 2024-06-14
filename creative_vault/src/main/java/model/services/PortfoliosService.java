package model.services;

import java.util.List;

import model.entities.ArtWorksEntity;
import model.entities.PortfoliosEntity;
import model.entities.UsersEntity;
import model.repositories.PortfoliosRepository;

public class PortfoliosService {

	private PortfoliosRepository portfoliosRepository;
	private UsersService usersService = new UsersService();

	public PortfoliosService() {
		this.portfoliosRepository = new PortfoliosRepository();
	}

	public PortfoliosEntity createPortfolio(PortfoliosEntity portfolio) {
		return (PortfoliosEntity) portfoliosRepository.create(portfolio);
	}

	public PortfoliosEntity updatePortfolio(PortfoliosEntity portfolio) {
		return (PortfoliosEntity) portfoliosRepository.update(portfolio);
	}

	public void deletePortfolio(PortfoliosEntity portfolio) {
		portfoliosRepository.delete(portfolio);
	}

	public PortfoliosEntity findById(Long id) {
		return (PortfoliosEntity) portfoliosRepository.findById(id);
	}

	public PortfoliosEntity findByName(String name) {
		return portfoliosRepository.findByName(name);
	}

	public List<PortfoliosEntity> findAll() {
		return portfoliosRepository.findAll();
	}

	public PortfoliosEntity updatePortfolioName(UsersEntity user, String newName) {

		UsersEntity userInDB = usersService.findUserById(user);

		if (userInDB != null) {
			PortfoliosEntity portfolio = userInDB.getArtist().getPortfolio();
			portfolio.setPortfolioName(newName);
			return updatePortfolio(portfolio);
		}

		return null;
	}

	public PortfoliosEntity updatePortfolioDescription(UsersEntity user, String newDescription) {

		UsersEntity userInDB = usersService.findUserById(user);

		if (userInDB != null) {
			PortfoliosEntity portfolio = userInDB.getArtist().getPortfolio();
			portfolio.setDescription(newDescription);
			return updatePortfolio(portfolio);
		}

		return null;
	}
}
