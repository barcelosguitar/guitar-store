package br.com.barcelos_projects.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.barcelos_projects.model.Guitar;
import javax.persistence.PersistenceContext;

@Stateless
public class GuitarDAO {
    
    @PersistenceContext(unitName = "guitarStorePersistenceUnit")
    //private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    //private Transaction utx;

    public void add(Guitar guitar) {
        try {
            //entityManager = entityManagerFactory.createEntityManager();
            
            //utx.begin();
            entityManager.persist(guitar);
            //utx.commit();

        } catch (Exception e) {
            System.out.println("GuitarDAO.add: " + e.getMessage());
            //utx.rollback();
            throw e;
        } /*finally {
            if (entityManager != null) {
                entityManagerFactory.close();
                entityManager.close();
            }
        }*/
    }

    public Guitar findById(Guitar object) {
        try {
            //entityManager = entityManagerFactory.createEntityManager();
            
            //Query query = this.entityManager.createQuery("FROM Guitar entity WHERE entity.id =: id");
            //utx.begin();
            Guitar guitar = entityManager.find(Guitar.class, object.getId());
            //utx.commit();

            return guitar;
        } catch (NullPointerException n) {
            return null;
        } catch (Exception e) {
            System.out.println("GlobalDAO.findById: " + e.getMessage());
            //utx.rollback();
            throw e;
        } /*finally {
            if (entityManager != null) {
                entityManagerFactory.close();
                entityManager.close();
            }
        }*/
        //return null;
    }

    public void delete(Guitar object){
        try {
            /*Query query = this.entityManager.createQuery("FROM Guitar entity WHERE entity.id=:id");
            query.setParameter("id", object.getId());

            Guitar guitar = (Guitar) query.getSingleResult();*/
            //entityManager = entityManagerFactory.createEntityManager();
            
            Guitar guitar = entityManager.find(Guitar.class, object.getId());
            
            //utx.begin();
            entityManager.remove(guitar);
            //utx.commit();

        } catch (Exception e) {
            System.out.println("GlobalDAO.delete: " + e.getMessage());
            throw e;
            //utx.rollback();
        } /*finally {            if (entityManager != null) {
                entityManager.close();
            }
        }*/
    }

    public void update(Guitar object) {
        try {
            //entityManager = entityManagerFactory.createEntityManager();
            
            //utx.begin();
            entityManager.merge(object);
            //utx.commit();

        } catch (Exception e) {
            System.out.println("GuitarDAO.update: " + e.getMessage());
            //utx.rollback();
            //throw e;
        } /*finally {
            if (entityManager != null) {
                entityManagerFactory.close();
                entityManager.close();
            }
        }*/
    }

    @SuppressWarnings("unchecked")
    public List<Guitar> listAll() {
        try {       
            //utx.begin();
            
            Query query = this.entityManager.createQuery("FROM Guitar entity");
            List<Guitar> guitars = (List<Guitar>) query.getResultList();
            //utx.commit();

            return guitars;
        } catch (NullPointerException n) {
            return null;
        } catch (Exception e) {
            System.out.println("GlobalDAO.listAll: " + e.getMessage());
            //utx.rollback();
            throw e;
        } /*finally {
            if (entityManager != null) {
                entityManagerFactory.close();
                entityManager.close();
            }
        }*/
        //return null;
    }
}