package no.hvl.dat110.rpc;

import java.io.IOException;
import java.util.HashMap;

import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

/**
 * Class for the RPC server which listens for requests and dispatches
 * corresponding methods registered with the server.
 * 
 */
public class RPCServer {

	private MessagingServer msgserver; // messaging server
	private MessageConnection connection; // message connection
	private HashMap<Byte, RPCRemoteImpl> services; // hashmap to store registered services

	/**
	 * Constructor for the RPC server
	 * 
	 * @param port - port number on which server will listen
	 */
	public RPCServer(int port) {

		// Initialize messaging server with the given port number
		msgserver = new MessagingServer(port);

		// Initialize hashmap to store registered services
		services = new HashMap<>();
	}

	/**
	 * Method to start the RPC server
	 * 
	 * @throws IOException - If there is an error in reading or writing to message
	 *                     connection
	 */
	public void run() throws IOException {

		// Built-in stop method for the server
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP, this);

		// Accept connection from client
		connection = msgserver.accept();

		System.out.println("RPC Server accepted a connection");

		boolean stop = false;

		while (!stop) {

			// receive message containing request
			Message request = connection.receive();

			byte rpcid = 0;
			Message reply;

			byte[] requestData = request.getData();

			// extract the RPC identifier for the method to be invoked
			rpcid = requestData[0];

			// decapsulate the request data
			requestData = RPCUtils.decapsulate(requestData);

			// look up the method to be invoked from the hashmap of services
			RPCRemoteImpl impl = services.get(rpcid);

			// invoke the method and get the response
			byte[] responseData = impl.invoke(requestData);

			// encapsulate the response data
			responseData = RPCUtils.encapsulate(rpcid, responseData);

			// create a message with the response data
			reply = new Message(responseData);

			// send the reply message to the client
			connection.send(reply);

			// stop the server if it was stop methods that was called
			if (rpcid == RPCCommon.RPIDSTOP) {

				if (rpcid == RPCCommon.RPIDSTOP) {
					stop = true;
				}
			}
		}
	}

	// Registers an RPC method implementation on the server side with the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
	services.put(rpcid, impl);
	}
	
	public void stop() {

		// close the message connection if it exists
		if (connection != null) {
			connection.close();
		} else {
			System.out.println("RPCServer.stop - connection was null");
		}

		// stop the messaging server if it exists
		if (msgserver != null) {
			msgserver.stop();
		} else {
			System.out.println("RPCServer.stop - msgserver was null");
		}

	}
}