package br.com.eder.store.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.eder.store.dao.BookDao;
import br.com.eder.store.models.Book;
import br.com.eder.store.models.CartItem;
import br.com.eder.store.models.ShoppingCart;

@Model
public class ShoppingCartBean {
	
	@Inject
	private BookDao bookDao;
	
	@Inject
	private ShoppingCart cart;

	public String add(Integer id) {
		Book book = bookDao.findById(id);
		CartItem item = new CartItem(book);
		cart.add(item);
		
		return "cart?faces-redirect=true";
	}
	
	public void remove(CartItem item) {
		cart.remove(item);
	}
	
	public List<CartItem> getItems(){
		return cart.getItems();
	}
}
