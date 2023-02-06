package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// connection to the messaging server
	private MessageConnection connection;

	public RPCClient(String server, int port) {
		// initialize the messaging client with the given server and port
		msgclient = new MessagingClient(server, port);
	}

	public void connect() throws UnknownHostException, IOException {
		// establish the connection to the messaging server
		connection = msgclient.connect();
	}

	public void disconnect() {
		// close the connection to the messaging server
		connection.close();
	}

	/**
	 * Make a remote call on the method on the RPC server by sending an RPC request
	 * message and receive an RPC reply message
	 * 
	 * @param rpcid  the identifier on the server side of the method to be called
	 * @param param  the marshalled parameter of the method to be called
	 * @return the return value of the RPC call, in bytes
	 */
	public byte[] call(byte rpcid, byte[] param)  {

		byte[] returnval = null;

		/*
		 * The rpcid and param must be encapsulated according to the RPC message format
		 * 
		 * The return value from the RPC call must be decapsulated according to the RPC
		 * message format
		 */
		try {
			// send an RPC request message to the messaging server
			connection.send(new Message(RPCUtils.encapsulate(rpcid, param)));
			// receive an RPC reply message from the messaging server
			returnval = RPCUtils.decapsulate(connection.receive().getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return returnval;
	}

}
