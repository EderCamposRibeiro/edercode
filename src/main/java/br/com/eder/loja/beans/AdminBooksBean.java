package br.com.eder.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.eder.loja.dao.BookDao;
import br.com.eder.loja.models.Book;

//CDI
@Named
@RequestScoped()
public class AdminBooksBean {
	
	private Book book = new Book();
	
	// Context and Dependency Injection
	@Inject
	private BookDao dao;
	
	@Transactional
	public void save() {
		dao.save(book);
		System.out.println("Registered book: " + book);
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
