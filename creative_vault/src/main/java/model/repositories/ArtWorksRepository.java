package model.repositories;

import java.util.List;

import javax.persistence.*;

import model.entities.ArtWorksEntity;
import model.entities.ArtistsEntity;

public class ArtWorksRepository implements BasicCrud {

	EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();

	@Override
	public Object create(Object object) {
		ArtWorksEntity artInData = (ArtWorksEntity) object;

		try {
			em.getTransaction().begin();
			em.persist(artInData);
			em.getTransaction().commit();
			return findById(artInData.getId());
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public Object update(Object object) {
		ArtWorksEntity art = (ArtWorksEntity) object;

		try {
			em.getTransaction().begin();
			ArtWorksEntity updatedArt = em.merge(art);
			em.getTransaction().commit();
			
			return updatedArt;
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public void delete(Object object) {
		ArtWorksEntity art = (ArtWorksEntity) object;
		try {
			em.getTransaction().begin();
			em.remove(art);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
		}
	}

	@Override
	public Object findById(Long id) {

		try {
			ArtWorksEntity artInDB = em.find(ArtWorksEntity.class, id);
			return artInDB;
		} catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}
	}
	
	public ArtWorksEntity findByTitle(String title) {
		try {
			return em.createQuery("SELECT aw FROM ArtWorksEntity aw WHERE aw.title = :title", ArtWorksEntity.class)
					.setParameter("title", title).getSingleResult();
		}  catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}
	}
	
	public List<ArtWorksEntity> findAll(){
		return em.createQuery("SELECT aw FROM ArtWorksEntity aw", ArtWorksEntity.class).getResultList();
	}
	
	public List<ArtWorksEntity> findArtsByArtist(ArtistsEntity artist) {
        try {
        	 return em.createQuery(
                     "SELECT aw FROM ArtWorksEntity aw WHERE aw.portfolio.artist = :artist", ArtWorksEntity.class)
                     .setParameter("artist", artist)
                     .getResultList();
        } catch (Exception e) {
            System.err.println("ERROR");
            return null;
        }
    }

}
