package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.entities.ArtRatingsEntity;
import model.entities.ArtWorksEntity;

public class ArtRatingsRepository implements BasicCrud {

EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();
	

    @Override
    public Object create(Object object) {
        ArtRatingsEntity ratingInData = (ArtRatingsEntity) object;

        try {
            em.getTransaction().begin();
            em.persist(ratingInData);
            em.getTransaction().commit();
            
            return findById(ratingInData.getId());
        } catch (Exception e) {
            System.err.println("Erro ao persistir dados: " + e.getMessage());
            e.printStackTrace();

            if (em.getTransaction().isActive()) {
                System.err.println("Transação ativa. Executando rollback.");
                em.getTransaction().rollback();
            } else {
                System.err.println("Transação não está ativa. Nenhuma ação de rollback necessária.");
            }

            return null;
        }
    }

    @Override
    public Object update(Object object) {
        ArtRatingsEntity rating = (ArtRatingsEntity) object;

        try {
            em.getTransaction().begin();
            ArtRatingsEntity updateRating = em.merge(rating);
            em.getTransaction().commit();
            
            return updateRating;
            
        } catch (Exception e) {
            System.err.println("ERROR");
            em.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public void delete(Object object) {
        ArtRatingsEntity rating = (ArtRatingsEntity) object;
        try {
            em.getTransaction().begin();
            em.remove(rating);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("ERROR");
            em.getTransaction().rollback();

        }
    }

    @Override
    public Object findById(Long id) {

        try {
            ArtRatingsEntity ratingInDB = em.find(ArtRatingsEntity.class, id);
            return ratingInDB;
        } catch (Exception e) {
            System.err.println("ERROR");
            return null;
        }
    }

    public List<ArtRatingsEntity> findRatingsByArt(ArtWorksEntity artWork) {
        try {
        	List<ArtRatingsEntity> ratings = em.createQuery(
                    "SELECT ar FROM ArtRatingsEntity ar WHERE ar.artWork = :artWork", ArtRatingsEntity.class)
                    .setParameter("artWork", artWork)
                    .getResultList();
        	return ratings;
        } catch (Exception e) {
            System.err.println("ERROR");
            return null;
        }
    }
}
