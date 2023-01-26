package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {

		byte[] segment = null;
		byte[] data;

		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer
		data = message.getData();
		segment = new byte[SEGMENTSIZE];
		segment[0] = (byte) data.length;
		for (int i = 1; i <= data.length; i++) {
			segment[i] = data[i - 1];
		}

		return segment;

	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		int length = segment[0];
		byte[] data = Arrays.copyOfRange(segment, 1, length + 1);
		message = new Message(data);

		return message;

	}
}
