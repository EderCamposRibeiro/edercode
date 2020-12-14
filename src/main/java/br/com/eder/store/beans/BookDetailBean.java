package br.com.eder.store.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.eder.store.dao.BookDao;
import br.com.eder.store.models.Book;

@Model
public class BookDetailBean {
	
	@Inject
	private BookDao dao;
	
	private Book book;
	
	private Integer id;
	
	public void chargeDetail() {
		//this.setBook(dao.findById(getId()));
		this.book = dao.findById(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
