package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// Create a new byte array with size equal to the payload plus one for the rpcid
		rpcmsg = new byte[payload.length + 1];
		
		// Set the first byte in the rpcmsg to the rpcid
		rpcmsg[0] = rpcid;
		
		// Copy the payload into the rpcmsg starting from the second byte
		for (int i = 0; i < payload.length; i++) {
			rpcmsg[i+1] = payload[i];
		}
		
		// Return the resulting rpcmsg
		return rpcmsg;
	}
	
	// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;
		
		// Create a new byte array with size equal to the rpcmsg minus one (to exclude the rpcid)
		payload = new byte[rpcmsg.length - 1];
		
		// Copy the payload of the rpcmsg into the payload array
		for (int i = 1; i < rpcmsg.length; i++) {
			payload[i-1] = rpcmsg[i];
		}
		
		// Return the resulting payload
		return payload;
		
	}

	// Convert a String to a byte array representation
	public static byte[] marshallString(String str) {
		
		byte[] encoded = null;
		
		// Encode the string into a byte array using the default encoding (usually UTF-8)
		encoded = str.getBytes();
		
		// Return the resulting encoded string
		return encoded;
	}

	// Convert a byte array representation to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null; 
		
		// Decode the byte array representation into a string using the default encoding (usually UTF-8)
		decoded = new String(data);
		
		// Return the resulting decoded string
		return decoded;
	}
	
	// Convert the void return type to a byte array representation
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		return encoded;
		
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		// This method does not require any processing as 'void' type in Java does not have a representation in byte array.
		// It is simply used to indicate the absence of a return value. 
		
		// No need to perform any operations or conversions, just return.
		
	}


	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = new byte[4];
		encoded[0] = (byte) (x >> 24);
		encoded[1] = (byte) (x >> 16);
		encoded[2] = (byte) (x >> 8);
		encoded[3] = (byte) x;
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		decoded |= (data[0] & 0xff) << 24;
		decoded |= (data[1] & 0xff) << 16;
		decoded |= (data[2] & 0xff) << 8;
		decoded |= data[3] & 0xff;
		
		return decoded;
		
	}
}
