package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
	public MessageConnection connect ()  {

		// client-side socket for underlying TCP connection to messaging server
		Socket clientSocket;
		MessageConnection connection  = null;
		try {
			// Connect to messaging server using a TCP socket
			clientSocket = new Socket(server,port);
			
			// Create and return a corresponding messaging connection
			connection = new MessageConnection(clientSocket);
		} catch (UnknownHostException e) {
			System.out.println("Error: Host not found. " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: I/O Exception. " + e.getMessage());
			e.printStackTrace();
		}
		
		return connection;
	}
}
