package model.repositories;

import java.util.List;

import javax.persistence.*;

import model.entities.PortfoliosEntity;

public class PortfoliosRepository implements BasicCrud {

	EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();

    @Override
    public Object create(Object object) {
        PortfoliosEntity portfolio = (PortfoliosEntity) object;
        try {
            em.getTransaction().begin();
            em.persist(portfolio);
            em.getTransaction().commit();
            
            return findById(portfolio.getId());
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Object update(Object object) {
        PortfoliosEntity portfolio = (PortfoliosEntity) object;
        try {
            em.getTransaction().begin();
            PortfoliosEntity updatedPortfolio = em.merge(portfolio);
            em.getTransaction().commit();
            
            return findById(updatedPortfolio.getId());
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            em.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public void delete(Object object) {
        PortfoliosEntity portfolio = (PortfoliosEntity) object;
        try {
            em.getTransaction().begin();
            em.remove(portfolio);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public Object findById(Long id) {
        try {
            return em.find(PortfoliosEntity.class, id);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public PortfoliosEntity findByName(String name) {
        try {
            return em.createQuery("SELECT p FROM PortfoliosEntity p WHERE p.portfolioName = :name", PortfoliosEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public List<PortfoliosEntity> findAll() {
        return em.createQuery("SELECT p FROM PortfoliosEntity p", PortfoliosEntity.class).getResultList();
    }

}
