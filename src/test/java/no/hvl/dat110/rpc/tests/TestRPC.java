package no.hvl.dat110.rpc.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class TestRPC {

	private static int PORT = 8080;
	private static String SERVER = "localhost";

	@Test
	public void testStartStop() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCClientStopStub stub = new RPCClientStopStub(client);

				stub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testVoidCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestVoidVoidImpl voidvoidimpl = new TestVoidVoidImpl((byte)1,server);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCClientStopStub stopstub = new RPCClientStopStub(client);
				TestVoidVoidStub voidvoidstub = new TestVoidVoidStub(client);

				// void test case
				voidvoidstub.m();

				assertTrue(true); // just check that we complete call
				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testStringCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestStringStringImpl stringstringimpl = new TestStringStringImpl((byte)2,server);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCClientStopStub stopstub = new RPCClientStopStub(client);
				TestStringStringStub stringstringstub = new TestStringStringStub(client);

				// string test case
				String teststr = "string";
				String resstr = stringstringstub.m(teststr);

				assertEquals(teststr + teststr, resstr);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testIntCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestIntIntImpl intintimpl = new TestIntIntImpl((byte)3,server);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCClientStopStub stopstub = new RPCClientStopStub(client);
				TestIntIntStub intintstub = new TestIntIntStub(client);

				// int test case
				int x = 42;
				int resx = intintstub.m(x);

				assertEquals(x, resx);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testBoolCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestBooleanBooleanImpl boolboolimpl = new TestBooleanBooleanImpl((byte)4,server);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCClientStopStub stopstub = new RPCClientStopStub(client);
				TestBooleanBooleanStub boolboolstub = new TestBooleanBooleanStub(client);

				// boolean test case

				boolean testb = true;
				boolean resb = boolboolstub.m(testb);

				assertEquals(!testb, resb);

				testb = false;
				resb = boolboolstub.m(testb);
				assertEquals(!testb, resb);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testAllCalls() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestVoidVoidImpl voidvoidimpl = new TestVoidVoidImpl((byte)1,server);
				TestStringStringImpl stringstringimpl = new TestStringStringImpl((byte)2,server);
				TestIntIntImpl intintimpl = new TestIntIntImpl((byte)3,server);
				TestBooleanBooleanImpl boolboolimpl = new TestBooleanBooleanImpl((byte)4,server);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCClientStopStub stopstub = new RPCClientStopStub(client);
				TestVoidVoidStub voidvoidstub = new TestVoidVoidStub(client);
				TestStringStringStub stringstringstub = new TestStringStringStub(client);
				TestIntIntStub intintstub = new TestIntIntStub(client);
				TestBooleanBooleanStub boolboolstub = new TestBooleanBooleanStub(client);

				// void test case
				voidvoidstub.m();

				// string test case
				String teststr = "string";
				String resstr = stringstringstub.m(teststr);

				assertEquals(teststr + teststr, resstr);

				// int test case
				int x = 42;
				int resx = intintstub.m(x);

				assertEquals(x, resx);
				// boolean test case

				boolean testb = true;
				boolean resb = boolboolstub.m(testb);

				assertEquals(!testb, resb);

				testb = false;
				resb = boolboolstub.m(testb);
				assertEquals(!testb, resb);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}
}
