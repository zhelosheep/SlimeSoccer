package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;


public class ServerThread extends Thread{
	ServerSocket ss;
	Vector<ServerHelperThread> shtVector;
	Hashtable<GameThread, HashSet<ServerHelperThread>> ongoingGames;
	LinkedList<ServerHelperThread> randomPlayers;
	HashSet<ServerHelperThread> waitingPlayers;
	boolean b = true;
	
	public static Integer idCounter = 1; // for generating unique game ids

	ServerThread(int port) {
		ongoingGames = new Hashtable<GameThread, HashSet<ServerHelperThread>>();
		randomPlayers = new LinkedList<ServerHelperThread>();
		waitingPlayers = new HashSet<ServerHelperThread>();
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