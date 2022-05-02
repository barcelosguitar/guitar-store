package br.com.barcelos_projects.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class GlobalDAOImplementation <Entity> implements GlobalDAO<Entity>{
	
	private Class<Entity> object;
	
	@SuppressWarnings("unchecked")
	public GlobalDAOImplementation() {
		System.out.println("Class: " + getClass().getGenericSuperclass().toString());
		this.object = (Class <Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	@Override
	public void add(Entity entity) {
		EntityTransaction transation = null;
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
			entityManager = entityManagerFactory.createEntityManager();
			transation = entityManager.getTransaction();

			transation.begin();
			entityManager.persist(entity);
			transation.commit();

		} catch (Exception e) {
			if (transation != null) {
				transation.rollback();
			}
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
				entityManagerFactory.close();
			}
		}
	}

	@Override
	public Entity findById(Long id) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
			entityManager = entityManagerFactory.createEntityManager();

			Entity entity = entityManager.find(object, id);

			return entity;
		} catch (NullPointerException n) {
			return null;
		} catch (Exception e) {
			System.out.println("GlobalRepository.findId: " + e.getMessage());
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
				entityManagerFactory.close();
			}
		}
	}

	@Override
	public void delete(Long id) {
		EntityTransaction transaction = null;
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			Entity entity = entityManager.find(object, id);

			transaction.begin();
			entity = entityManager.merge(entity);
			entityManager.remove(entity);
			transaction.commit();

		} catch (Exception e) {
			System.out.println("GlobalRepository.delete: " + e.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
				entityManagerFactory.close();
			}
		}
	}

	@Override
	public void update(Entity entity) {
		EntityTransaction transation = null;
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
			entityManager = entityManagerFactory.createEntityManager();
			transation = entityManager.getTransaction();

			transation.begin();
			entityManager.merge(entity);
			transation.commit();

		} catch (Exception e) {
			if (transation != null) {
				transation.rollback();
			}
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
				entityManagerFactory.close();
			}
		}
	}

	@Override
	public List<Entity> listAll() {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
			entityManager = entityManagerFactory.createEntityManager();

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Entity> cq = cb.createQuery(object);
			Root<Entity> rootEntry = cq.from(object);
			CriteriaQuery<Entity> all = cq.select(rootEntry);

			TypedQuery<Entity> allQuery = entityManager.createQuery(all);
			return allQuery.getResultList();
		} catch (NullPointerException n) {
			return null;
		} catch (Exception e) {
			System.out.println("GlobalRepository.listAll: " + e.getMessage());
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
				entityManagerFactory.close();
			}
		}
	}
}