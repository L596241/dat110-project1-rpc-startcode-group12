package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

		public void send(Message message) {		//Takes in Message object as a parameter

			byte[] data = message.getData();	//Calls the getData() method of the object to retrieve the byte array of the message payload

			// encapsulate the data contained in the Message and write to the output stream
			try {
				// header = first byte, payload = subsequent 127 bytes
				
				outStream.writeByte(data.length);	//uses the writeByte() method of the outStream object to write the length of the payload data as the first byte
				outStream.write(data);				//uses the write() method of the outStream object to write the payload data to the output stream.	

				// fill in remaining bytes with padding and sends the message to the output stream
				for (int i = data.length; i < 128; i++) {
					outStream.writeByte(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public Message receive() {

			Message message = null;
			byte[] data = new byte[128];		//Create an array of bytes with a length of 128

			// read a segment from the input stream and decapsulate data into a Message
			try {
				inStream.read(data);			//Uses the read() method of the inStream object to read a segment of data into the "data" array.
				int len = data[0];				//Extracts the length of the message payload by reading the first byte of the data array (data[0])
				byte[] payload = new byte[len];	//Creates a new array called "payload" with the length extracted in the previous step.
				for (int i = 0; i < len; i++) {	//Iterates through the "data" array, starting at the second element (data[1]), and copies the bytes into the "payload" array.
					payload[i] = data[i + 1];	//creates a new Message object using the payload array as a parameter.
				}
				message = new Message(payload);	//assigns the newly created Message object to the "message" variable.
			} catch (IOException e) {
				e.printStackTrace();
			}
			return message;						//Returns the "message" variable
		}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}