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
		System.out.println("Entering on the Last Releases!");
		return dao.getLastReleases();
	}

	public List<Book> allOtherBooks() {
		System.out.println("Entering on the All Other Books!");
		return dao.getAllOtherBooks();
	}

}
