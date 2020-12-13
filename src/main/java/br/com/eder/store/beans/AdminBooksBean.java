package br.com.eder.store.beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.eder.store.dao.AuthorDao;
import br.com.eder.store.dao.BookDao;
import br.com.eder.store.infra.FileSaver;
import br.com.eder.store.models.Author;
import br.com.eder.store.models.Book;

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
	
	@Inject
	private FacesContext context;
	
	private Part bookCover;
	
	public AdminBooksBean() {
		context = FacesContext.getCurrentInstance();
	}

	@Transactional
	public String save() throws IOException {
		dao.save(book);
		FileSaver fileSaver = new FileSaver(); // Our new class

		book.setCoverPath(fileSaver.write(bookCover,"books")); //We already called the write method e already returned the path to the book
		
		context.getExternalContext()
			.getFlash().setKeepMessages(true); // Here we are activating the FlashScope
		context
			.addMessage(null, new FacesMessage("Book successfully registered!"));
		
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

	public Part getBookCover() {
		return bookCover;
	}

	public void setBookCover(Part bookCover) {
		this.bookCover = bookCover;
	}
}
