package model.repositories;

import java.util.List;

import javax.persistence.*;
import model.entities.ArtistsEntity;

public class ArtistsRepository implements BasicCrud {

	EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();

	@Override
	public Object create(Object object) {
		ArtistsEntity artistInData = (ArtistsEntity) object;
		try {
			em.getTransaction().begin();
            em.persist(artistInData);
            em.getTransaction().commit();
            
			return (ArtistsEntity)findById(artistInData.getId());
		} catch (PersistenceException e) {
	        // Lidar com erros de persistência
	        System.err.println("PersistenceException: " + e.getMessage());
	        return null;
	    } catch (Exception e) {
	        // Lidar com outros erros
	        System.err.println("UnexpectedException: " + e.getMessage());
	        return null;
	    }
	}

	@Override
	public Object update(Object object) {
		ArtistsEntity artistInData = (ArtistsEntity) object;
		try {
			em.getTransaction().begin();
			em.merge(artistInData);
			em.getTransaction().commit();
			
			return findById(artistInData.getId());
		} catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}
	}

	@Override
	public void delete(Object object) {
		ArtistsEntity artistInData = (ArtistsEntity) object;
		try {
			em.getTransaction().begin();
			em.remove(artistInData);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
		}		
	}

	@Override
	public Object findById(Long id) {
		try {
			ArtistsEntity artistInDB = em.find(ArtistsEntity.class, id);
			return artistInDB;
		} catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}		
	}
	
	public ArtistsEntity findByName(String name) {
		try {
			return em.createQuery("SELECT a FROM ArtistsEntity a WHERE a.artistName = :name", ArtistsEntity.class)
					.setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<ArtistsEntity> findAll() {
		return em.createQuery("SELECT a FROM ArtistsEntity a", ArtistsEntity.class).getResultList();
	}

}
