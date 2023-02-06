package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import no.hvl.dat110.TODO;

public class RPCUtils {

	/**
	 * Encapsulate the rpcid and payload in a byte array according to the RPC
	 * message syntax / format
	 * 
	 * @param rpcid   - the id of the remote procedure call
	 * @param payload - the payload to be sent
	 * @return the encapsulated byte array
	 */
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		byte[] rpcmsg = new byte[payload.length + 1];
		rpcmsg[0] = rpcid;
		for (int i = 0; i < rpcmsg.length - 1; i++) {
			rpcmsg[i + 1] = payload[i];
		}
		return rpcmsg;
	}

	/**
	 * Decapsulate the rpcid and payload in a byte array according to the RPC
	 * message syntax
	 * 
	 * @param rpcmsg - the byte array to be decapsulated
	 * @return the decapsulated payload
	 */
	public static byte[] decapsulate(byte[] rpcmsg) {
		byte[] payload = new byte[rpcmsg.length - 1];
		for (int i = 0; i < rpcmsg.length - 1; i++) {
			payload[i] = rpcmsg[i + 1];
		}
		return payload;
	}

	/**
	 * Converts a string to a byte array
	 * 
	 * @param str - the string to be converted
	 * @return the byte array representation of the string
	 */
	public static byte[] marshallString(String str) {
		return str.getBytes();
	}

	/**
	 * Converts a byte array to a string
	 * 
	 * @param data - the byte array to be converted
	 * @return the string representation of the byte array
	 */
	public static String unmarshallString(byte[] data) {
		return new String(data);
	}

	/**
	 * Returns an empty byte array as a representation of void
	 * 
	 * @return an empty byte array
	 */
	public static byte[] marshallVoid() {
		return new byte[0];
	}

	/**
	 * Unmarshalls a void representation in the form of a byte array
	 * 
	 * @param data - the byte array representation of void
	 */
	public static void unmarshallVoid(byte[] data) {
		// No implementation needed
	}

	/**
	 * Converts a boolean to its byte array representation
	 * 
	 * @param b - the boolean to be converted
	 * @return the byte array representation of the boolean
	 */
	public static byte[] marshallBoolean(boolean b) {
		byte[] encoded = new byte[1];
		encoded[0] = b ? (byte) 1 : (byte) 0;
		return encoded;
	}

	// Convert a byte array representation of a boolean to its boolean
	// representation
	public static boolean unmarshallBoolean(byte[] data) {
		return data[0] != 0;
	}

	// Convert an integer to its byte array representation
	public static byte[] marshallInteger(int x) {
		return ByteBuffer.allocate(4).putInt(x).array();
	}

	// Convert a byte array representation of an integer to its integer
	// representation
	public static int unmarshallInteger(byte[] data) {
		return ByteBuffer.wrap(data).getInt();
	}
}
