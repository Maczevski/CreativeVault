package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.entities.ArtTypesEntity;

public class ArtTypesRepository implements BasicCrud{

	EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();


	@Override
	public Object create(Object object) {
		ArtTypesEntity typeInData = (ArtTypesEntity) object;

		try {
			em.getTransaction().begin();
			em.persist(typeInData);
			em.getTransaction().commit();
			
			return findById(typeInData.getId());
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public Object update(Object object) {
		ArtTypesEntity art = (ArtTypesEntity) object;

		try {
			em.getTransaction().begin();
			ArtTypesEntity updatedType =  em.merge(art);
			em.getTransaction().commit();
			
			return updatedType;
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public void delete(Object object) {
		ArtTypesEntity art = (ArtTypesEntity) object;
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
			ArtTypesEntity typeInDB = em.find(ArtTypesEntity.class, id);
			return typeInDB;
		} catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}
	}
	
	public ArtTypesEntity findByType(String type) {
		try {
			return em.createQuery("SELECT t FROM ArtTypesEntity t WHERE t.type = :type", ArtTypesEntity.class)
					.setParameter("type", type).getSingleResult();
		
	    } catch (Exception e) {
	        System.err.println("ERROR: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public List<ArtTypesEntity> findAll(){
		return em.createQuery("SELECT t FROM ArtTypesEntity t", ArtTypesEntity.class).getResultList();
	}
}
