package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class SensorStub extends RPCLocalStub {

	public SensorStub(RPCClient rpcclient) {
		super(rpcclient);
	}

	public int read() {

		// marshall a void parameter for the read call
		byte[] request = RPCUtils.marshallVoid();
		
		// make the remote procedure call for read
		byte[] response = rpcclient.call((byte)Common.READ_RPCID, request);
		
		// unmarshall the integer response from the read call
		int temp = RPCUtils.unmarshallInteger(response);
		
		return temp;
	}
}
