package no.hvl.dat110.messaging;


public class Message {

    // the up to 127 bytes of data (payload) that a message can hold
    private byte[] data;

    // constructor for a Message with the data provided
    public Message(byte[] data) {

        // check that the data is not null and not longer than 127 bytes
        if (data == null || data.length > 127) {
            throw new IllegalArgumentException("Data cannot be null and must be less than 128 bytes.");
        }

        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }
}
