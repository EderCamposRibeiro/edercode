package br.com.eder.store.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.eder.store.models.Book;

@Stateful
public class BookDao {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED) //
	private EntityManager manager;
	
	public void save(Book book) {
		manager.persist(book);
	}

	public List<Book> list() {
		String jpql = "select distinct(b) from Book b "
				+ " join fetch b.authors ";
		
		return manager.createQuery(jpql, Book.class).getResultList();
	}

	public List<Book> getLastReleases() {
		String jpql = "select b from Book b "
				+ " order by b.publishDate desc";
		
		return manager.createQuery(jpql, Book.class)
				.setMaxResults(5)
				.getResultList();	}

	public List<Book> getAllOtherBooks() {
		String jpql = "select b from Book b "
				+ " order by b.publishDate desc";
		
		return manager.createQuery(jpql, Book.class)
				.setFirstResult(5)
				.getResultList();	
	}

	public Book findById(Integer id) {
		
//		return manager.find(Book.class, id);
		
		String jpql = "select b from Book b join fetch b.authors"
				+ " where b.id = :id ";
		return manager.createQuery(jpql, Book.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
