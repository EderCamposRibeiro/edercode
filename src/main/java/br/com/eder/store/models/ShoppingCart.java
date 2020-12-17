package br.com.eder.store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.eder.store.dao.PurchaseDao;

@SessionScoped
@Named
public class ShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Set<CartItem> items = new HashSet<>();
	
	@Inject
	private PurchaseDao purchaseDao;
	
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

	public void checkout(Purchase purchase) {
		purchase.setItems(toJson());
		purchase.setTotal(getTotal());
		purchaseDao.save(purchase);
	}

	private String toJson() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (CartItem item : items) {
			builder.add(Json.createObjectBuilder()
				   .add("title", item.getBook().getTitle())
				   .add("price", item.getBook().getPrice())
				   .add("amount", item.getAmount())
				   .add("total", getTotal(item)));
		}
		String json = builder.build().toString();
		
		System.out.println(json);
		
		return json;
	}
}

















