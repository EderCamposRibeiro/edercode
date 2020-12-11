package br.com.store.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.eder.store.models.Book;

public class BookDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Book book) {
		manager.persist(book);
	}

	public List<Book> list() {
		String jpql = "select distinct(b) from Book b "
				+ " join fetch b.authors ";
		
		return manager.createQuery(jpql, Book.class).getResultList();
	}

}
