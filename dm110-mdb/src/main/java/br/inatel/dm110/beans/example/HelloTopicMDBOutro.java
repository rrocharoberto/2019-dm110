package br.inatel.dm110.beans.example;

import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", 
								  propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", 
								  propertyValue = "java:/jms/topic/dm110topic") })

public class HelloTopicMDBOutro implements MessageListener {
	
	private static Logger log = Logger.getLogger(HelloTopicMDBOutro.class.getName());

	@Override
	public void onMessage(Message message) {
		//processamento da mensagem
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String text = txtMessage.getText();
				log.info("##### Mensagem recebida do tópico: " + text);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}