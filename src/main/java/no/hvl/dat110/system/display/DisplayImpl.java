package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.rpc.RPCServer;

public class DisplayImpl extends RPCRemoteImpl {

	public DisplayImpl(byte rpcid, RPCServer rpcserver) {
		super(rpcid, rpcserver);
	}

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}

	public byte[] invoke(byte[] param) {

		byte[] returnval = null;

		String string = RPCUtils.unmarshallString(param);
		
		write(string);

		returnval = RPCUtils.marshallString(string);


		return returnval;
	}
}