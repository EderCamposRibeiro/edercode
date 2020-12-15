package br.com.eder.store.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.eder.store.models.ClientUser;
import br.com.eder.store.models.ShoppingCart;

@Model
public class CheckoutBean {
	
	private ClientUser clientUser = new ClientUser();
	
	@Inject
	private ShoppingCart shoppingCart;
	
	public void checkout() {
		shoppingCart.checkout(clientUser);
	}

	public ClientUser getClientUser() {
		return clientUser;
	}

	public void setClientUser(ClientUser clientUser) {
		this.clientUser = clientUser;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


}
