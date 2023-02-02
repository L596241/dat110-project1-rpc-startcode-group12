package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.MessagingClient;

	/**
	 * The RPCClient class implements the client side for the Remote Procedure Call
	 * (RPC) communication. It uses the MessagingClient and MessageConnection
	 * classes to connect to the server and send and receive messages. The call
	 * method is used to make a remote call on the server-side method by sending an
	 * RPC request message and receiving an RPC reply message.
	 */
public class RPCClient {

    // messaging client used for RPC communication
    private MessagingClient msgClient;
    
    // messaging connection used for RPC communication
    private MessageConnection connection;

    /**
     * Constructs a new RPCClient with the specified server and port
     * @param server the hostname or IP address of the server
     * @param port the port number on the server to connect to
     */
    public RPCClient(String server, int port) {
        msgClient = new MessagingClient(server, port);
    }

    /**
     * Connects to the server using the messaging client.
     * @throws UnknownHostException if the server hostname is not found
     * @throws IOException if an I/O error occurs
     */
    public void connect() throws UnknownHostException, IOException {
        connection = msgClient.connect();
    }

    /**
     * Disconnects from the server by closing the messaging connection.
     */
    public void disconnect() {
        connection.close();
    }

    /**
     * Makes a remote call on the specified method on the server by sending an RPC request
     * message and receiving an RPC reply message.
     * @param rpcid the identifier of the method to be called on the server
     * @param param the marshalled parameter of the method to be called
     * @return the return value from the RPC call
     */
    public byte[] call(byte rpcid, byte[] param) {

        byte[] returnVal = null;
        connection.send(new Message(RPCUtils.encapsulate(rpcid, param)));
		returnVal = RPCUtils.decapsulate(connection.receive().getData());

        return returnVal;
    }
}
