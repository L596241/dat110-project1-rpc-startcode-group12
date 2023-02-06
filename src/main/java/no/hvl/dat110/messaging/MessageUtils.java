package no.hvl.dat110.messaging;

import java.util.Arrays;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	/**
	 * Encapsulates the data contained in the Message and returns a byte array of segment
	 * @param message - Message object containing data
	 * @return byte array of segment
	 */
	public static byte[] encapsulate(Message message) {
		
		byte[] segment = new byte[128];
		byte[] data = message.getData();
		int length = data.length;
		
		// set the length of the payload data in the first byte of the segment
		segment[0] = (byte) length;
		
		// copy the payload data into the remaining bytes of the segment
		for(int i = 0; i < length; i++) {
			segment[i+1] = data[i]; 
		}
		
		return segment;
	}

	/**
	 * Decapsulates the byte array of segment into a Message object
	 * @param segment - byte array of segment
	 * @return Message object containing the decapsulated payload data
	 */
	public static Message decapsulate(byte[] segment) {

		Message message = null;
		int length = segment[0];
		
		// extract the payload data from the segment
		byte[] decapsulated = new byte[length];
		for(int i = 0; i < length; i++) {
			decapsulated[i] = segment[i+1];
		}
		
		// create a Message object with the extracted payload data
		message = new Message(decapsulated);
		
		return message;
	}
	
}
