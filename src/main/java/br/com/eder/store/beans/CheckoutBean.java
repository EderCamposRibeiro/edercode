package br.com.eder.store.beans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.eder.store.models.ClientUser;
import br.com.eder.store.models.Purchase;
import br.com.eder.store.models.ShoppingCart;

@Model
public class CheckoutBean {
	
	private ClientUser clientUser = new ClientUser();
	
	@Inject
	private ShoppingCart shoppingCart;
	
	@Inject
	private FacesContext facesContext;
	
	@Transactional
	public void checkout() {
		Purchase purchase = new Purchase();
		purchase.setClientUser(clientUser);
		shoppingCart.checkout(purchase);
		
		String contextName = facesContext.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse) 
				facesContext.getExternalContext().getResponse();
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", contextName 
				+ "/" + "services/payment?uuid="+ purchase.getUuid());
	}

	public ClientUser getClientUser() {
		return clientUser;
	}

	public void setClientUser(ClientUser clientUser) {
		this.clientUser = clientUser;
	}

}
