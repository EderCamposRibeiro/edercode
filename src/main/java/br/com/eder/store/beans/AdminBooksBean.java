package br.com.eder.store.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.eder.store.models.Author;
import br.com.eder.store.models.Book;
import br.com.store.loja.dao.AuthorDao;
import br.com.store.loja.dao.BookDao;

//CDI
@Named
@RequestScoped()
public class AdminBooksBean {
	
	private Book book = new Book();
	
	// Context and Dependency Injection
	@Inject
	private BookDao dao;
	
	@Inject
	private AuthorDao authorDao;
	
	private List<Integer> authorsId = new ArrayList<>(); // new ArrayList in order to avoid NullPointeException

	@Transactional
	public String save() {
		for (Integer authorId : authorsId) {
			book.getAuthors().add(new Author(authorId));
		}
		dao.save(book);
		System.out.println("Registered book: " + book);
		
		// In order to clean the screen after the register
		//this.book = new Book(); 
		//this.authorsId = new ArrayList<>();
		return "/books/list?faces-redirect=true";
	}
	
	public List<Author> getAuthors() {
		return authorDao.list();
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public List<Integer> getAuthorsId() {
		return authorsId;
	}
	
	public void setAuthorsId(List<Integer> authorsId) {
		this.authorsId = authorsId;
	}

}
