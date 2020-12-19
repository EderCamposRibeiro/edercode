package br.com.eder.store.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.eder.store.dao.PurchaseDao;
import br.com.eder.store.infra.MailSender;
import br.com.eder.store.models.Purchase;

@MessageDriven(activationConfig = {
	@ActivationConfigProperty(
			propertyName = "destinationLookup",
			propertyValue = "java:/jms/topics/ShopingCartTopics"),
	@ActivationConfigProperty(
			propertyName = "destinationType",
			propertyValue = "javax.jms.Topic")
})
public class SendPurchaseEmail implements MessageListener{
	
	@Inject
	private PurchaseDao purchaseDao;
	
	@Inject	
	private MailSender mailSender;
	
	public void onMessage(Message message) {

		try {
			TextMessage textMessage = (TextMessage) message;
			
			Purchase purchase = purchaseDao.findByUuid(textMessage.getText());
	
			String messageBody = "Your purchase is done! Yor purchase number is " + purchase.getUuid();
		
			mailSender.send("purchase@edercode.com.br", 
				purchase.getClientUser().getEmail(),
				"New purchase o Eder Code shop!",
				messageBody );
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}


}
