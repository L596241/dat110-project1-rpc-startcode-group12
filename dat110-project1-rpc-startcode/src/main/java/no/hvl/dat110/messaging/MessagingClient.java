package no.hvl.dat110.messaging;


import java.io.IOException;
import java.net.Socket;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

		// client-side socket for underlying TCP connection to messaging server
		Socket clientSocket;

		MessageConnection connection = null;
		
		// TODO - START
		// connect to messaging server using a TCP socket
		// create and return a corresponding messaging connection
		// Den deler som skaber forbindelser til serversiden.
		// Må siggert lage en socket og ni må bruge messageconnection classen
		// Bruge accept metode. (Vi skal bruge Socket programmering til å lage meldingsnivå)
		try {
			clientSocket = new Socket(server, port);
			connection = new MessageConnection(clientSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// TODO - END
		return connection;
	}
}
