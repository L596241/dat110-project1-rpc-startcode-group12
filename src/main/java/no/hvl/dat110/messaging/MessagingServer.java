package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {

		try {

			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {
			// in case of error when creating the server socket, a message is printed to the console
			System.out.println("Error creating Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// accept an incoming connection from a client
	public MessageConnection accept() throws IOException {
		
		// socket for the accepted connection
		Socket accepted = welcomeSocket.accept();
		
		// create a messaging connection object from the accepted socket
		MessageConnection connection = new MessageConnection(accepted);
		
		return connection;

	}

	public void stop() {

		if (welcomeSocket != null) {

			try {
				// close the server socket to stop the server
				welcomeSocket.close();
			} catch (IOException ex) {
				// in case of error when closing the socket, a message is printed to the console
				System.out.println("Error stopping Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
