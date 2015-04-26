package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerHelperThread extends Thread {
	Socket s;
	PrintWriter pw;
	BufferedReader br;
	private int i;
	private ServerThread st;
	String username;
	boolean readyToPlay = false;
	ServerHelperThread opponentThread = null;
	
	ServerHelperThread(int i, ServerThread st) {
		this.i = i;
		this.st = st;
	}
	
	public void run() {
		try {
			username = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IOException in ServerHelperThread.run(): " + ioe.getMessage());
		}
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
					C - chat
					
					E - player1 xcoord
					F - player1 ycoord
					G - player2 xcoord
					H - player2 ycoord
					
					I - ball xcoord
					J - ball ycoord
					
					M - get random game (spectate)
					N - find specific game (spectate)
					O - play
					P - add to random (play)
					Q - add to waiting (play)
					R - remove from random and waiting
					S - spectate!

					Z - remove all
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
					} 

					else if (str.charAt(0) == 'M')
					{

					}

					else if (str.charAt(0) == 'N')
					{

					}

					else if (str.charAt(0) == 'O')
					{
						System.out.println("in O");
						System.out.println(str);
						
						synchronized (st) {
							if (opponentThread.readyToPlay) {
								opponentThread.readyToPlay = false;
								HashSet<ServerHelperThread> set = new HashSet<ServerHelperThread>();
								set.add(opponentThread);
								set.add(this);
								// this shouldn't be hardcoded, decode the string here!!
								
								str = str.substring(2);
								String delims = "[$]";
								String [] tokens = str.split(delims);
								for (int i = 0; i < tokens.length; i++)
								{
									System.out.println(tokens[i]);
									//tokens[0] = special mode
									//tokens[1] = background
									//tokens[2] = regenRate
									//tokens[3] = totalMana
								}
								st.ongoingGames.put(new GameThread(tokens[1], "SlimeBowAndArrow", "SlimeGeyser", "shawnren", "josemama", Integer.valueOf(tokens[3]), Integer.valueOf(tokens[3]), Integer.valueOf(tokens[2]), tokens[0]), set);
								System.out.println("making game");
							} else {
								this.readyToPlay = true;
							}
						}
					}

					else if (str.charAt(0) == 'P')
					{
						synchronized (st.randomPlayers) {
							if (st.randomPlayers.isEmpty()) st.randomPlayers.add(this);
							else {
								st.randomPlayers.peek().pw.println("Y1" + this.username);
								st.randomPlayers.peek().pw.flush();
								st.randomPlayers.peek().opponentThread = this;
								this.pw.println("Y2" + st.randomPlayers.peek().username);
								this.pw.flush();
								this.opponentThread = st.randomPlayers.peek();
								st.randomPlayers.remove();
							}
						}
					}

					else if (str.charAt(0) == 'Q')
					{
						synchronized (st.waitingPlayers) {
							boolean found = false;
							for (ServerHelperThread temp : st.waitingPlayers) {
								if (temp.username.equals(str.substring(1))) {
									found = true;
									temp.pw.println("Y1" + this.username);
									temp.pw.flush();
									temp.opponentThread = this;
									this.pw.println("Y2" + temp.username);
									this.pw.flush();
									this.opponentThread = temp;
									st.waitingPlayers.remove(temp);
								}
							}
							if (!found) {
								st.waitingPlayers.add(this);
							}
						}
					}

					else if (str.charAt(0) == 'R')
					{
						synchronized (st.randomPlayers) {
							if (st.randomPlayers.contains(this)) st.randomPlayers.remove(this);
						}
						synchronized (st.waitingPlayers) {
							if (st.waitingPlayers.contains(this)) st.waitingPlayers.remove(this);
						}
					}
					
					else if (str.charAt(0) == 'Z')
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
				} else break;
			} catch (IOException ioe) {
				System.out.println("IOException in ServerHelperThread.run(): " + ioe.getMessage());
				break;
			} 
		}
		try {
			if (s != null) s.close();
		} catch (IOException ioe) {
			System.out.println("IOException in ServerHelperThread.run(): " + ioe.getMessage());
		}
	}
}
