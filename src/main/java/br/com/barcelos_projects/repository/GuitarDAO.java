package br.com.barcelos_projects.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.barcelos_projects.model.Guitar;

@Stateful
public class GuitarDAO {
    
    @PersistenceContext(unitName = "guitarStorePersistenceUnit")
    private EntityManager entityManager;

    public void add(Guitar guitar) {
        try {
            entityManager.persist(guitar);
        } catch (Exception e) {
            System.out.println("GuitarDAO.add: " + e.getMessage());
            throw e;
        }
    }
    public Guitar findById(Guitar object) {
        try {
            Guitar guitar = entityManager.find(Guitar.class, object.getId());

            return guitar;
        } catch (NullPointerException n) {
            return null;
        } catch (Exception e) {
            System.out.println("GlobalDAO.findById: " + e.getMessage());
            throw e;
        }
    }
    public void delete(Guitar object){
        try {
            Guitar guitar = entityManager.find(Guitar.class, object.getId());         
            entityManager.remove(guitar);
        } catch (Exception e) {
            System.out.println("GlobalDAO.delete: " + e.getMessage());
            throw e;
        }
    }
    public void update(Guitar object) {
        try {
            entityManager.merge(object);
        } catch (Exception e) {
            System.out.println("GuitarDAO.update: " + e.getMessage());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Guitar> listGuitars() {
        try {        
            Query query = this.entityManager.createQuery("FROM Guitar guitar");
            List<Guitar> guitars = (List<Guitar>) query.getResultList();
            
            return guitars;
        } catch(NullPointerException e) {
            return null;
        } catch (Exception e) {
            System.out.println("GuitarDAO.listAll: " + e.getMessage());
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Guitar> listRandomGuitars (){
        try {
            Query query = this.entityManager.createNativeQuery("SELECT *FROM guitar ORDER BY RAND() LIMIT 12");
            List<Guitar> guitars = (List<Guitar>) query.getResultList();

            return guitars;
        } catch(NullPointerException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public List<Guitar> getClonedProducts() {
        List<Guitar> results = new ArrayList<>();
        List<Guitar> originals = listGuitars();
        for (Guitar original : originals) {
            results.add(original.clone());
        }
        return results;
    }
}