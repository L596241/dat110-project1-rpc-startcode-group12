package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	// Constructor that initializes the input and output streams and the socket
		public MessageConnection(Socket socket) {
			try {
				// set the socket for the underlying TCP connection
				this.socket = socket;
				
				// initialize the DataOutputStream with the output stream from the socket
				outStream = new DataOutputStream(socket.getOutputStream());
				
				// initialize the DataInputStream with the input stream from the socket
				inStream = new DataInputStream (socket.getInputStream());
			} catch (IOException ex) {
				System.out.println("Connection: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		// method to send a message
		public void send(Message message) {
			byte[] data;
			
			// encapsulate the data contained in the Message and write to the output stream
			data = MessageUtils.encapsulate(message);
			
			try {
				outStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// method to receive a message
		public Message receive() {
			Message message = null;
			byte[] data;
			
			// read a segment from the input stream and decapsulate data into a Message
			try {
				data = inStream.readNBytes(128);
				message = MessageUtils.decapsulate(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return message;
		}
		
		// method to close the connection
		public void close() {
			try {
				// close the DataOutputStream
				outStream.close();
				
				// close the DataInputStream
				inStream.close();
				
				// close the socket
				socket.close();
			} catch (IOException ex) {
				System.out.println("Connection: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}