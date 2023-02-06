package no.hvl.dat110.rpc;

import java.io.IOException;

public class RPCClientStopStub extends RPCLocalStub {

	public RPCClientStopStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	// client-side implementation of the built-in server stop RPC method
	public void stop() throws IOException {
		
		// marshal void request
		byte[] request = RPCUtils.marshallVoid();
		
		// make an RPC call to the server to stop it by sending RPIDSTOP and request
		byte[] response = rpcclient.call(RPCCommon.RPIDSTOP, request);
		
		// unmarshal the response to check if the server stopped successfully
		RPCUtils.unmarshallVoid(response);
	
	}
}
