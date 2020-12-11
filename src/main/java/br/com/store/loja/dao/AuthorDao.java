package br.com.store.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.eder.store.models.Author;

public class AuthorDao {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Author> list(){
		return manager.createQuery(
				"select a from Author a", Author.class)
				.getResultList();
	}

}
