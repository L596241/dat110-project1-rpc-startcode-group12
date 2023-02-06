package no.hvl.dat110.system.controller;

import java.io.IOException;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller  {
	
	private static int N = 5; // number of times to read from sensor and write to display
	
	public static void main (String[] args) throws IOException, InterruptedException {
		
		DisplayStub display; // local display stub object
		SensorStub sensor; // local sensor stub object
		
		RPCClient displayClient, sensorClient; // RPC clients for the system
		
		System.out.println("Controller starting ...");
				
		// Create RPC clients for the system
		displayClient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT);
		sensorClient = new RPCClient(Common.SENSORHOST, Common.SENSORPORT);
		
		// Set up stop methods in the RPC middleware
		RPCClientStopStub stopDisplay = new RPCClientStopStub(displayClient);
		RPCClientStopStub stopSensor = new RPCClientStopStub(sensorClient);
		
		// Connect to sensor and display RPC servers
		displayClient.connect();
		sensorClient.connect();
		
		// Create local display and sensor stub objects
		display = new DisplayStub(displayClient);
		sensor = new SensorStub(sensorClient);
		
		// Read value from sensor using RPC and write to display using RPC
		for(int i = 0; i < N; i++) {
			
			Thread.sleep(1000); // wait for 1 second
			display.write("" + sensor.read()); // convert int to string and write to display
		}
		
		// Stop the display and sensor RPC middleware
		stopDisplay.stop();
		stopSensor.stop();
	
		// Disconnect from the display and sensor RPC servers
		displayClient.disconnect();
		sensorClient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
