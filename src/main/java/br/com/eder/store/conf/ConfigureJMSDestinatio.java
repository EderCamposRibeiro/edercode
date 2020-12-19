package br.com.eder.store.conf;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions({
	@JMSDestinationDefinition(
		name = "java:/jms/topics/ShopingCartTopics", 
		interfaceName = "javax.jms.Topic",
		destinationName = "java:/jms/topics/ShopingCartTopics"
		)
})
@Singleton
public class ConfigureJMSDestinatio {

}
