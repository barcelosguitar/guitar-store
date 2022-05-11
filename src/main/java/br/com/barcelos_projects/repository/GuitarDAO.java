package br.com.barcelos_projects.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.barcelos_projects.model.Guitar;

@Stateless
public class GuitarDAO {
    
    @PersistenceContext(unitName="guitarStorePersistenceUnit")
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

            Query query = this.entityManager.createQuery("FROM Guitar entity WHERE entity.id =: id");
            Guitar guitar = (Guitar) query.getSingleResult();

			return guitar;
		} catch (NullPointerException n) {
			return null;
		} catch (Exception e) {
			System.out.println("GlobalDAO.findById: " + e.getMessage());
			throw e;
		}
	}

	public void delete(Guitar object) {
		try {
            Query query = this.entityManager.createQuery("FROM Guitar entity WHERE entity.id=:id");
			query.setParameter("id", object.getId());

            Guitar guitar = (Guitar) query.getSingleResult();

			entityManager.remove(guitar);

		} catch (Exception e) {
			System.out.println("GlobalDAO.delete: " + e.getMessage());
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	public void update(Guitar object) {
		try {

			entityManager.merge(object);

		} catch (Exception e) {
			System.out.println("GuitarDAO.update: " + e.getMessage());
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	@SuppressWarnings("uncheked")
	public List<Guitar> listAll() {
		try {
		    Query query = this.entityManager.createQuery("FROM Guitar entity");
		    List<Guitar> guitars = query.getResultList();
		
		    return guitars;
		} catch (NullPointerException n) {
			return null;
		} catch (Exception e) {
			System.out.println("GlobalDAO.listAll: " + e.getMessage());
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}