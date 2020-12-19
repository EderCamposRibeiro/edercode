package br.com.eder.store.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;

import br.com.eder.store.dao.PurchaseDao;
import br.com.eder.store.models.Purchase;

@Path("/payment")
public class PaymentService {
	
	@Context
	ServletContext context;
	
	@Inject
	private PurchaseDao purchaseDao;
	
	@Inject
	private PaymentGateway paymentGateway;
	
	@Inject
	private JMSContext jmsContext;
	
	@Resource(name = "java:/jms/topics/ShopingCartTopics")
	private Destination destination;

	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		Purchase purchase = purchaseDao.findByUuid(uuid);
		String contextPath = context.getContextPath();
		
		JMSProducer producer = jmsContext.createProducer();
		
		executor.submit(() -> {
				try {
					String responseStr = paymentGateway.pay(purchase.getTotal());
					
					producer.send(destination, purchase.getUuid());

					URI responseUri = UriBuilder
							.fromPath("http://localhost:8080" + contextPath + "/index.xhtml")
						.queryParam("msg", "Congratulations, you did it! Successful Purchase!!").build();
					Response response = Response.seeOther(responseUri).build();
					
					ar.resume(response);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (UriBuilderException e) {
					e.printStackTrace();
				} catch (Exception e2) {
					ar.resume(new WebApplicationException(e2));
					e2.printStackTrace();
				}
				
			
		});
	}

}
