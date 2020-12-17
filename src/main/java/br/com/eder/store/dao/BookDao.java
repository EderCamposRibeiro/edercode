package br.com.eder.store.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;

import br.com.eder.store.models.Book;

@Stateful
public class BookDao {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED) //
	private EntityManager manager;
	
	public void save(Book book) {
		manager.persist(book);
	}
	
	public void limpaCache() {
		Cache cache = manager.getEntityManagerFactory().getCache();
		cache.evict(Book.class, 1l);
		cache.evictAll();
		
		SessionFactory factory = manager.getEntityManagerFactory().unwrap(SessionFactory.class);
		factory.getCache().evictAllRegions();
		factory.getCache().evictQueryRegion("home");
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
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.setHint(QueryHints.HINT_CACHE_REGION, "home")
				.getResultList();	}

	public List<Book> getAllOtherBooks() {
		String jpql = "select b from Book b "
				+ " order by b.publishDate desc";
		
		return manager.createQuery(jpql, Book.class)
				.setFirstResult(5)
				.setHint(QueryHints.HINT_CACHEABLE, true)
				.setHint(QueryHints.HINT_CACHE_REGION, "home")
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
