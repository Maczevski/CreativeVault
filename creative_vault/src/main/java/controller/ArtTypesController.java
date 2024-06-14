package controller;

import java.util.List;

import model.entities.ArtTypesEntity;
import model.services.ArtTypesService;

public class ArtTypesController {

	ArtTypesService artTypesService = new ArtTypesService();
	
    public ArtTypesEntity createArtType(ArtTypesEntity artType) {
        return (ArtTypesEntity) artTypesService.createArtType(artType);
    }

    public ArtTypesEntity updateArtType(ArtTypesEntity artType) {
        return (ArtTypesEntity) artTypesService.updateArtType(artType);
    }

    public void deleteArtType(ArtTypesEntity artType) {
        artTypesService.deleteArtType(artType);
    }

    public ArtTypesEntity findArtTypeById(Long id) {
        return (ArtTypesEntity) artTypesService.findArtTypeById(id);
    }

    public ArtTypesEntity findArtTypeByType(String type) {
        return artTypesService.findArtTypeByType(type);
    }

    public List<ArtTypesEntity> findAllArtTypes() {
        return artTypesService.findAllArtTypes();
    }
}
