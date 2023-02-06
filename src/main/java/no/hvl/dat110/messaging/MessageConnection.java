package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageConnection {

	private DataOutputStream outStream; // DataOutputStream to write bytes to the underlying TCP connection
	private DataInputStream inStream; // DataInputStream to read bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	// constructor to initialize the streams and socket
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			// initialize the output stream to write bytes to the socket
			outStream = new DataOutputStream(socket.getOutputStream());

			// initialize the input stream to read bytes from the socket
			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			// print the error message if any exception occurs
			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// method to send a message over the connection
	public void send(Message message) throws IOException {

		byte[] data;
		
		// encapsulate the data contained in the Message and write it to the output stream
		data = MessageUtils.encapsulate(message);
		outStream.write(data);
	}

	// method to receive a message from the connection
	public Message receive() throws IOException {
		Message message = null;
		byte[] data = new byte[128];
		
		// read a segment from the input stream and decapsulate it into a Message
		for(int i = 0; i<data.length;i++) {
			data[i] = inStream.readByte();
		}
		message = MessageUtils.decapsulate(data);
		
		return message;
	}

	// method to close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			// print the error message if any exception occurs
			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
