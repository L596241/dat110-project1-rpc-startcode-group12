package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This class implements a messaging server that listens for incoming
 * connections from clients on a specified port.
 */
public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	/**
	 * Creates an instance of MessagingServer and listens on a specified port
	 * 
	 * @param port the port number to listen on
	 */
	public MessagingServer(int port) {

		try {
			// create a server socket on the specified port
			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {

			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// accept an incoming connection from a client
	public MessageConnection accept() {

		MessageConnection connection = null;

		// accept TCP connection on welcome socket and create messaging connection to be
		// returned
		try {
			connection = new MessageConnection(welcomeSocket.accept());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return connection;

	}

	/**
	 * Closes the server socket and stops the messaging server
	 */

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
