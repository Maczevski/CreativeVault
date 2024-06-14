package controller;

import java.util.List;
import model.entities.PortfoliosEntity;
import model.entities.UsersEntity;
import model.services.PortfoliosService;

public class PortfoliosController {

    private PortfoliosService portfoliosService;

    public PortfoliosController() {
        this.portfoliosService = new PortfoliosService();
    }

    public PortfoliosEntity createPortfolio(PortfoliosEntity portfolio) {
        return portfoliosService.createPortfolio(portfolio);
    }

    public PortfoliosEntity updatePortfolio(PortfoliosEntity portfolio) {
        return portfoliosService.updatePortfolio(portfolio);
    }

    public void deletePortfolio(PortfoliosEntity portfolio) {
        portfoliosService.deletePortfolio(portfolio);
    }

    public PortfoliosEntity findById(Long id) {
        return portfoliosService.findById(id);
    }

    public PortfoliosEntity findByName(String name) {
        return portfoliosService.findByName(name);
    }

    public List<PortfoliosEntity> findAll() {
        return portfoliosService.findAll();
    }
    
    public PortfoliosEntity updatePortfolioName(UsersEntity user, String newName) {
    	return portfoliosService.updatePortfolioName(user, newName);
    }
    
    public PortfoliosEntity updatePortfolioDescription(UsersEntity user, String newName) {
    	return portfoliosService.updatePortfolioDescription(user, newName);
    }
}
