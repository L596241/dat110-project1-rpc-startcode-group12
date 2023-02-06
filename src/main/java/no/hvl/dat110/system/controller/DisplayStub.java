package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayStub {
	
	private RPCClient rpcclient;
	
	public DisplayStub(RPCClient rpcclient) {
		this.rpcclient = rpcclient;
	}
	
	/**
	 * Calls the write method in the display
	 * @param message - the string message to be displayed
	 */
	public void write (String message) {
		
		// Marshalls the string message into a byte array
		byte[] request = RPCUtils.marshallString(message);
		
		// Sends the request to the server
		byte[] response = rpcclient.call((byte)Common.WRITE_RPCID, request);
		
		// Unmarshalls the response from the server
		RPCUtils.unmarshallString(response);
	}
}
