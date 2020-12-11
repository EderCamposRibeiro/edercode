package br.com.eder.store.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.eder.store.models.Book;
import br.com.store.loja.dao.BookDao;

@Model  // We can use @Model instead use @Named and @RequestScoped (@Model has both annotations and more)
public class AdminListBooksBean {
	
	@Inject
	private BookDao dao;
	
	private List<Book> books = new ArrayList<>();

	public List<Book> getBooks() {
		this.books = dao.list();
		return books;
	}

}
