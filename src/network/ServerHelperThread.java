package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public class ServerHelperThread extends Thread {
	Socket s;
	PrintWriter pw;
	BufferedReader br;
	private int i;
	private ServerThread st;
	String username, otherUsername;
	
	ServerHelperThread(int i, ServerThread st) {
		this.i = i;
		this.st = st;
	}
	
	public void run() {
		while (st.b) {
			try {
				String str = br.readLine();
				if (str != null) {
					// again, str.charAt(0) is an identifier that tells us what to do with the string
					if (str.charAt(0) == 'C') { // chat
						for (int j = 0; j < st.shtVector.size(); j++) {
							if (j != i) { // push the string to all other threads
								st.shtVector.elementAt(j).pw.println(str);
								st.shtVector.elementAt(j).pw.flush();
							}
						}
					}
					
					/*Corresponding data transfers
					E - player1 xcoord
					F - player1 ycoord
					G - player2 xcoord
					H - player2 ycoord
					
					I - ball xcoord
					J - ball ycoord
					
					P - play!
					Q - also play!
					R - remove from Vector
					S - spectate!
					*/
					
					// TO DO: Parser in the clientthread!
					else if (str.charAt(0) == 'E')	//player1 xcoord
					{
						//push the string to a player1 handler which will parse into integer
					}
					
					else if (str.charAt(0) == 'F') //player1 ycoord
					{
						//push the string to a player1 handler which will parse into integer
					}
					
					else if (str.charAt(0) == 'G') //player2 xcoord
					{
						//push the string to a player2 handler which will parse into integer
					}
					
					else if (str.charAt(0) == 'H') //player2 ycoord
					{
						//push the string to a player2 handler which will parse into integer
					}
					
					else if (str.charAt(0) == 'I') //ball xcoord
					{
						//push the string to a ball handler which will parse into integer
					}
					
					else if (str.charAt(0) == 'J') //ball ycoord
					{
						//push the string to a ball handler which will parse into integer
					} else if (str.charAt(0) == 'R')
					{
						st.shtVector.removeElementAt(i);
						synchronized (st.ongoingGames) {
							for (Set<ServerHelperThread> set : st.ongoingGames.values()) {
								if (set.contains(this)) set.remove(this);
							}
						}
						synchronized (st.randomPlayers) {
							if (st.randomPlayers.contains(this)) st.randomPlayers.remove(this);
						}
						synchronized (st.waitingPlayers) {
							if (st.waitingPlayers.contains(this)) st.waitingPlayers.remove(this);
						}
						break;
					}
				}
			} catch (IOException ioe) {
				System.out.println("IOException in ServerHelperThread.run(): " + ioe.getMessage());
			} 
		}
		try {
			if (s != null) s.close();
		} catch (IOException ioe) {
			System.out.println("IOException in ServerHelperThread.run(): " + ioe.getMessage());
		}
	}
}
