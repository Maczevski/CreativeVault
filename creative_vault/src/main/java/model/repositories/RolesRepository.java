package model.repositories;

import java.util.List;

import javax.persistence.*;

import model.entities.RolesEntity;

public class RolesRepository implements BasicCrud {

    EntityManager em = Persistence.createEntityManagerFactory("creativevault").createEntityManager();

    @Override
    public Object create(Object object) {
        RolesEntity role = (RolesEntity) object;
        try {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
            
            return findById(role.getId());
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Object update(Object object) {
        RolesEntity role = (RolesEntity) object;
        try {
            em.getTransaction().begin();
            RolesEntity updatedRole = em.merge(role);
            em.getTransaction().commit();
            
            return findById(updatedRole.getId());
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void delete(Object object) {
        RolesEntity role = (RolesEntity) object;
        try {
            em.getTransaction().begin();
            role = em.find(RolesEntity.class, role.getId());
            if (role != null) {
                em.remove(role);
            }
            em.getTransaction().commit();
            
        } catch (Exception e) {
            System.err.println("ERROR");
            }
    }

    @Override
    public Object findById(Long id) {
        try {
            return em.find(RolesEntity.class, id);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public RolesEntity findByName(String name) {
        try {
            return em.createQuery("SELECT r FROM RolesEntity r WHERE r.role = :name", RolesEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public List<RolesEntity> findAll() {
        try {
            return em.createQuery("SELECT r FROM RolesEntity r", RolesEntity.class).getResultList();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }
}
