package com.devfer.demomvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class AbstractDao<T, PK extends Serializable> {

	//Tipos genéricos
	//T é o tipo de entidade que a classe irar utilizar para fazer as percistencias
	//PK é o tipo da chave primária

	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = 
			(Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	//Injeção, conexão com o DB
	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void save(T entity) { 

		entityManager.persist(entity);
	}
	
	public void update(T entity) {
		
		entityManager.merge(entity);
	}
	
	public void delete(PK id) {
		
		entityManager.remove(entityManager.getReference(entityClass, id));
	}
	
	public T findById(PK id) {
		
		return entityManager.find(entityClass, id);
	}
	
	public List<T> findAll() {
		
		return entityManager
				.createQuery("from " + entityClass.getSimpleName(), entityClass)
				.getResultList();
	}	

	//Para executar Querys específicas
	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		for (int i = 0; i < params.length; i++) {
		    query.setParameter(i+1, params[i]);
        }
    	return query.getResultList();
	}
}
