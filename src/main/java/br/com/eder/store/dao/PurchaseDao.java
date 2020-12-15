package br.com.eder.store.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.eder.store.models.Purchase;

public class PurchaseDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void save(Purchase purchase) {
		manager.persist(purchase);
		
	}

}
