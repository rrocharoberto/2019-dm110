package br.inatel.dm110.client.hello;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.inatel.dm110.api.MessageTO;

public class HelloClient {

	private static final String REST_URI_GET = "http://localhost:8080/dm110-web/api/hello/message";
	private static final String REST_URI_POST = "http://localhost:8080/dm110-web/api/hello/storeMessage";

	private static Client client = ClientBuilder.newClient();

	public static void main(String[] args) {
		System.out.println("Message service:");
		
		MessageTO msg = new MessageTO();
		msg.setFirstName("Roberto");
		msg.setLastName("Rocha");
		System.out.println("Result from POST: " + createMessage(msg));

		System.out.println("Result from GET 1: " + getMessage(1));
		System.out.println("Result from GET 2: " + getMessage(2));
	}

	public static MessageTO getMessage(Integer id) {
		return client
				.target(REST_URI_GET)
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get(MessageTO.class);
	}
	
	public static Response createMessage(MessageTO emp) {
	    return client
	      .target(REST_URI_POST)
	      .request(MediaType.APPLICATION_JSON)
	      .post(Entity.entity(emp, MediaType.APPLICATION_JSON));
	}

}
