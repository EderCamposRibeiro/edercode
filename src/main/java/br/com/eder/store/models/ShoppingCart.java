package br.com.eder.store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Set<CartItem> items = new HashSet<>();
	
	public void add(CartItem item) {
		items.add(item);
	}

	public List<CartItem> getItems() {
		return new ArrayList<CartItem>(items);
	}
	
	public BigDecimal getTotal(CartItem item) {
		return item.getBook().getPrice().multiply(
				new BigDecimal(item.getAmount()));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem cartItem : items) {
			total = total.add(cartItem.getBook().getPrice()
					.multiply(new BigDecimal(cartItem.getAmount())));
		}
		return total;
	}

	public void remove(CartItem item) {
		this.items.remove(item);
	}
	
	public Integer getTotalAmount() {
		return items.stream().mapToInt(item -> item.getAmount()).sum();
	}
}
