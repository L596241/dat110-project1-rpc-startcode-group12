package no.hvl.dat110.system.display;

import java.io.IOException;

import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;


public class DisplayDevice {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Display server starting ...");
		
		// implement the operation of the display RPC server
		// see how this is done for the sensor RPC server in SensorDevice
		RPCServer displayServer = new RPCServer(Common.DISPLAYPORT);
		new DisplayImpl((byte)Common.WRITE_RPCID,displayServer);
		
		displayServer.run();
		displayServer.stop();
		
		System.out.println("Display server stopping ...");
		
	}
}