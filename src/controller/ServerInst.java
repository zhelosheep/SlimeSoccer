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
			System.out.println("Waiting for connection...");
			s = ss.accept();
			
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} finally {
			try {
				s.close();
				ss.close();
			} catch (IOException e) {
				System.out.println("IOException: " + e.getMessage());
			}
		}
	}
}
