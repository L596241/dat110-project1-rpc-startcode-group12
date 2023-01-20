package no.hvl.dat110.rpc;

public class RPCServerStopImpl extends RPCRemoteImpl {

	public RPCServerStopImpl(byte rpcid, RPCServer rpcserver) {
		super(rpcid,rpcserver);
	}
	
	// RPC server-side implementation of the built-in stop RPC method
	public byte[] invoke(byte[] request) {
		
		RPCUtils.unmarshallVoid(request);
		
		byte[] reply = RPCUtils.marshallVoid(); 
		
		stop(); 
		
		return reply;
	}
	
	public void stop() {
		
		System.out.println("RPC server executing stop");
		
	}
}
