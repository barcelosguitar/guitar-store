package br.com.barcelos_projects.repository;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.barcelos_projects.model.Costumer;

@Stateful
public class CostumerDAO {

    @PersistenceContext(unitName = "guitarStorePersistenceUnit")
    private EntityManager entityManager;
    
    public void add(Costumer costumer) {
        try {
            entityManager.persist(costumer);
        } catch (Exception e) {
            throw e;
        }
    }
    public Costumer findById(Costumer costumer) {
        try {
            Costumer findCostumer = entityManager.find(Costumer.class, costumer.getId());

            return findCostumer;
        } catch (NullPointerException n) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
    public void delete(Costumer costumer){
        try {
            Costumer findCostumer = entityManager.find(Costumer.class, costumer.getId());
            entityManager.remove(findCostumer);
        } catch (Exception e) {
            throw e;
        }
    }
    public void update(Costumer costumer) {
        try {
            entityManager.merge(costumer);
        } catch (Exception e) {
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Costumer> listAll() {
        try {        
            Query query = this.entityManager.createQuery("FROM Costumer entity");
            List<Costumer> costumers = (List<Costumer>) query.getResultList();
            
            return costumers;
        } catch(NullPointerException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }   
}