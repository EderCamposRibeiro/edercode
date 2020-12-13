package br.com.eder.store.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.eder.store.dao.BookDao;
import br.com.eder.store.models.Book;

@Model
public class HomeBean {
	
	@Inject
	private BookDao dao;
	
	public List<Book> lastReleases() {
		return dao.getLastReleases();
	}

	public List<Book> allOtherBooks() {
		return dao.getAllOtherBooks();
	}

}
