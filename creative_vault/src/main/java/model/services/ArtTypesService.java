package model.services;

import java.util.List;

import model.entities.ArtTypesEntity;
import model.repositories.ArtTypesRepository;

public class ArtTypesService {
	 private ArtTypesRepository artTypesRepository = new ArtTypesRepository();

	    public ArtTypesEntity createArtType(ArtTypesEntity artType) {
	        return (ArtTypesEntity) artTypesRepository.create(artType);
	    }

	    public ArtTypesEntity updateArtType(ArtTypesEntity artType) {
	        return (ArtTypesEntity) artTypesRepository.update(artType);
	    }

	    public void deleteArtType(ArtTypesEntity artType) {
	        if (artType != null) {
	            artTypesRepository.delete(artType);
	        }
	    }

	    public ArtTypesEntity findArtTypeById(Long id) {
	        return (ArtTypesEntity) artTypesRepository.findById(id);
	    }

	    public ArtTypesEntity findArtTypeByType(String type) {
	        return artTypesRepository.findByType(type);
	    }

	    public List<ArtTypesEntity> findAllArtTypes() {
	        return artTypesRepository.findAll();
	    }
}
