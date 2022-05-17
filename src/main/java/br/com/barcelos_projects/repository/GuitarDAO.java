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
    public Guitar findByIdLong(Long id) {
        try {
            Guitar guitar = entityManager.find(Guitar.class, id);

            return guitar;
        } catch (NullPointerException n) {
            return null;
        } catch (Exception e) {
            System.out.println("GlobalDAO.findById: " + e.getMessage());
            throw e;
        }
    }
    public String getGuitarImg(Guitar object){
        try {
            
            return "s";
        } catch (Exception e) {
            //TODO: handle exception
        }
        return null;
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
        } catch (Exception e) {
            System.out.println("GuitarDAO.listAll: " + e.getMessage());
            throw e;
        }
    }
}