package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInst {
	int port;
	
	ServerInst() {
		port = 3306;
		System.out.println("Server started!");
		
		new Thread(new Runnable() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(port);
			
					while (true) {
						Socket s = ss.accept();
					}
				
				} catch (IOException ioe) {
					System.out.println("IOException in ServerInst constructor: " + ioe.getMessage());
				}
			}
		}).start();
	}
}
