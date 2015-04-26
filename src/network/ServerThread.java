package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import model.Game;

public class ServerThread extends Thread{
	ServerSocket ss;
	Vector<ServerHelperThread> shtVector;
	Map<Game, Set<ServerHelperThread>> ongoingGames;
	Queue<ServerHelperThread> randomPlayers;
	Set<ServerHelperThread> waitingPlayers;
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
			try {
				ServerHelperThread sht = new ServerHelperThread(shtVector.size(), this);
				sht.s = null;
				sht.s = ss.accept();
				sht.br = new BufferedReader(new InputStreamReader(sht.s.getInputStream()));
				sht.pw = new PrintWriter(sht.s.getOutputStream());
				shtVector.addElement(sht);
				sht.start();
			} catch (IOException ioe) {
				System.out.println("IOException in AcceptThread: " + ioe.getMessage());
			}
		}
		b = false;
	}
	
	public static void main(String[] args) {
		(new ServerThread(6789)).start();
	}
}