package model.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.entities.UsersEntity;

public class UsersRepository implements BasicCrud {

	EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();

	List<UsersEntity> usersBase = new ArrayList<>();

	@Override
	public Object create(Object object) {
		UsersEntity userInData = (UsersEntity) object;

		try {
			em.getTransaction().begin();
			em.persist(userInData);
			em.getTransaction().commit();
			
			return findById(userInData.getId());
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public Object update(Object object) {
		UsersEntity user = (UsersEntity) object;

		try {
			em.getTransaction().begin();
            UsersEntity mergedUser = em.merge(user);
            em.getTransaction().commit();
            
            return mergedUser;
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public void delete(Object object) {
		UsersEntity user = (UsersEntity) object;
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			System.err.println("ERROR");
			em.getTransaction().rollback();
		}
	}

	@Override
	public Object findById(Long id) {

		try {
			UsersEntity userInDB = em.find(UsersEntity.class, id);
			return userInDB;
		} catch (Exception e) {
			System.err.println("ERROR");
			return null;
		}
	}

	public UsersEntity findByEmail(String email) {
		try {
			return em.createQuery("SELECT u FROM UsersEntity u WHERE u.email = :email", UsersEntity.class)
					.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<UsersEntity> findAll() {
		return em.createQuery("SELECT u FROM UsersEntity", UsersEntity.class).getResultList();
	}

}
