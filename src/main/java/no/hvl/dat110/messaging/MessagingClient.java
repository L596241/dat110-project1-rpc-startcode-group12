package no.hvl.dat110.messaging;


import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingClient {
	
	private String server;
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// connect to messaging server
	public MessageConnection connect () {
			
		Socket clientSocket;
		MessageConnection connection = null;
		
		// TODO - START
		// create TCP socket for client and connection
		
		if (true)
			throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
		return connection;
	}
}
