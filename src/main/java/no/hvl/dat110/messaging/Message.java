package no.hvl.dat110.messaging;

public class Message {

	// The payload of the message, limited to 127 bytes.
	private byte[] data;

	/**
	 * Constructs a Message with the provided data.
	 * If the data is null or exceeds 127 bytes in length, it is not stored.
	 * @param data the data to be stored as the payload of the Message
	 */
	public Message(byte[] data) {
		
		if(data != null && data.length < 128) {
			this.data = data;
		}
	}

	/**
	 * Returns the payload of the Message.
	 * @return the payload of the Message
	 */
	public byte[] getData() {
		return this.data; 
	}
}
