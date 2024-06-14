package model.repositories;

import java.util.List;

import javax.persistence.*;
import model.entities.ArtistRatingsEntity;
import model.entities.ArtistsEntity;

public class ArtistRatingsRepository implements BasicCrud {

	EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();

	@Override
	public Object create(Object object) {
		ArtistRatingsEntity ratingInData = (ArtistRatingsEntity) object;

		try {
			em.getTransaction().begin();
			em.persist(ratingInData);
			em.getTransaction().commit();

			return findById(ratingInData.getId());
		} catch (Exception e) {
			System.err.println("ERROR");
            em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public Object update(Object object) {
		ArtistRatingsEntity rating = (ArtistRatingsEntity) object;

		try {
			em.getTransaction().begin();
			em.merge(rating);
			em.getTransaction().commit();

			return findById(rating.getId());
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public void delete(Object object) {
		ArtistRatingsEntity rating = (ArtistRatingsEntity) object;
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
			ArtistRatingsEntity ratingInDB = em.find(ArtistRatingsEntity.class, id);
			return ratingInDB;
		} catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}
	}

	public List<ArtistRatingsEntity> findRatingsByArtist(ArtistsEntity artist) {
		try {
			List<ArtistRatingsEntity> ratings = em
					.createQuery("SELECT ar FROM ArtistRatingsEntity ar WHERE ar.artist = :artist",
							ArtistRatingsEntity.class)
					.setParameter("artist", artist).getResultList();

			return ratings;
		} catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}
	}


}
