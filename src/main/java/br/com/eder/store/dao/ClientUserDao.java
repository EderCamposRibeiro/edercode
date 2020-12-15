package br.com.eder.store.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.eder.store.models.ClientUser;

public class ClientUserDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void save(ClientUser clientUser) {
		manager.persist(clientUser);
	}
	
	

}
