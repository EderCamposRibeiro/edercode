package br.com.eder.store.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.eder.store.models.Payment;

public class PaymentGateway implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public String pay(BigDecimal total) {
		Client client = ClientBuilder.newClient();
		Payment payment = new Payment(total);
		String target ="http://book-payment.herokuapp.com/payment";
		
		return client.target(target).request()
				.post(Entity.json(payment), String.class);
	}

}
