package no.hvl.dat110.rpc;

import java.io.IOException;
import java.util.HashMap;

import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.MessageUtils;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private MessageConnection connection;
	private HashMap<Byte,RPCRemoteImpl> services;
	
	public RPCServer(int port) {
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<>();
	}
	
	public void run() throws IOException {
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP, this);
		System.out.println("RPC Server started. Services registered: " + services.size());
		
		connection = msgserver.accept(); 
		System.out.println("RPC Server accepted connection.");
		
		boolean stop = false;
		
		while (!stop) {
		   byte rpcid = 0;
		   Message requestMessage, replyMessage;
		   
		   requestMessage = connection.receive();
		   byte[] requestData = requestMessage.getData();
		   rpcid = requestData[0];
		   requestData = RPCUtils.decapsulate(requestData);
		   
		   RPCRemoteImpl rpcImpl = services.get(rpcid);
		   byte[] responseData = rpcImpl.invoke(requestData);
		   replyMessage = new Message(RPCUtils.encapsulate(rpcid, responseData));
		   connection.send(replyMessage);
		  
		   if (rpcid == RPCCommon.RPIDSTOP) {
			   stop = true;
		   }
		}
	}
	
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {
		if (connection != null) {
			connection.close();
		} else {
			System.out.println("RPCServer.stop - connection was null");
		}
		
		if (msgserver != null) {
			msgserver.stop();
		} else {
			System.out.println("RPCServer.stop - msgserver was null");
		}
	}
}
