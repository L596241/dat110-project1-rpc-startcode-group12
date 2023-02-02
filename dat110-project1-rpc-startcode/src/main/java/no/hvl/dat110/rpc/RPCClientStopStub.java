package no.hvl.dat110.rpc;

import java.io.IOException;

public class RPCClientStopStub extends RPCLocalStub {

	public RPCClientStopStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	// client-side implementation of the built-in server stop RPC method
	public void stop () throws IOException {
		
		byte[] request = RPCUtils.marshallVoid();
		
		byte[] response = rpcclient.call(RPCCommon.RPIDSTOP,request);
		
		RPCUtils.unmarshallVoid(response);
	
	}
}