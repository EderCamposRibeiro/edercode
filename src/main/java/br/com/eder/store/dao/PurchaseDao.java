package br.com.eder.store.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.eder.store.models.Purchase;

public class PurchaseDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Purchase purchase) {
		manager.persist(purchase);
		
	}

	public Purchase findByUuid(String uuid) {
		return manager.createQuery("select p from Purchase p where p.uuid = :uuid", Purchase.class)
			.setParameter("uuid", uuid)
			.getSingleResult();
	}

}
