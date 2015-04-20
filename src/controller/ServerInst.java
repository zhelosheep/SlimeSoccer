package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInst {
	Socket s;
	ServerSocket ss;
	
	ServerInst() {
		try {
			ss = new ServerSocket(3306);
			
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		}
	}
}
