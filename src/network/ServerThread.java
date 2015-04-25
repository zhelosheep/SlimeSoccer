package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

public class ServerThread extends Thread{
	ServerSocket ss;
	Vector<ServerHelperThread> shtVector;
	boolean b = true;
	
	ServerThread(int port) {
		try {
			ss = new ServerSocket(port);
		} catch (IOException ioe) {
			System.out.println("IOException in ServerThread();" + ioe.getMessage());
		}
		shtVector = new Vector<ServerHelperThread>();
	}
	
	public void run() {
		while (true) {
			if (ss == null) break;
			(new AcceptThread(this)).start();
		}
		b = false;
	}
	
	public static void main(String[] args) {
		(new ServerThread(6789)).start();
	}
}