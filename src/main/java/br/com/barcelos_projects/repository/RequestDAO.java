package br.com.barcelos_projects.repository;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.barcelos_projects.model.Request;

@Stateful
public class RequestDAO {
    
    @PersistenceContext(unitName = "guitarStorePersistenceUnit")
    private EntityManager entityManager;

    public void add(Request request) {
        try {
            entityManager.persist(request);
        } catch (Exception e) {
            throw e;
        }
    }
    public Request findById(Request request) {
        try {
            Request findRequest = entityManager.find(Request.class, request.getId());
            return findRequest;
        } catch (NullPointerException n) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
    public void delete(Request request){
        try {
            Request findRequest = entityManager.find(Request.class, request.getId());
            entityManager.remove(findRequest);
        } catch (Exception e) {
            throw e;
        }
    }
    public void update(Request request) {
        try {
            entityManager.merge(request);
        } catch (Exception e) {
            throw e;
        }
    }
    @SuppressWarnings("unchecked")
    public List<Request> listAll() {
        try {        
            Query query = this.entityManager.createQuery("FROM Request request");
            List<Request> requests = (List<Request>) query.getResultList();
            
            return requests;
        } catch(NullPointerException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}