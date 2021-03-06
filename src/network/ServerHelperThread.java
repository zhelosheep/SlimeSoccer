package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
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
					else if (str.charAt(0) == 'D' )
					{
						synchronized (st.ongoingGames) {
							for (Set<ServerHelperThread> set : st.ongoingGames.values()) {
								if (set.contains(this)) {
									for (int j = 0; j < set.size(); j++) {
										if (j != i) {
											st.shtVector.elementAt(j).pw.println(str);
											st.shtVector.elementAt(j).pw.flush();
										}
									}
								}
							}
						}
					}
					
					/*Corresponding data transfers
					C - chat
					D - lobbychat
					
					E - 
					F - 
					G - 
					H - 
					
					I - transmit slime selection
					J -
					
					K - player1 key
					L - player2 key
					
					M - get random game (spectate)
					N - find specific game (spectate)
					O - play
					P - add to random (play)
					Q - add to waiting (play)
					R - remove from random and waiting
					S - leave
					
					Z - remove all
					*/
										
					else if (str.charAt(0) == 'I') 
					{
						this.opponentThread.pw.println(str);
						this.opponentThread.pw.flush();
					}
					
					else if (str.charAt(0) == 'J') 
					{
						//push the string to a ball handler which will parse into integer
					} 
					
					else if (str.charAt(0) == 'K') //player 1 key detection
					{
						str = str.substring(1);	//lop off the K
						synchronized (st.ongoingGames) {
							for (GameThread gt : st.ongoingGames.keySet()) {
								if (st.ongoingGames.get(gt).contains(this)) {
									if (str.equals("up"))
									{
										gt.game.variables.p1_keyboardState[0] = true;
									}

									else if (str.equals("down"))
									{
										gt.game.variables.p1_keyboardState[1] = true;
									}

									else if (str.equals("left"))
									{
										gt.game.variables.p1_keyboardState[2] = true;
									}

									else if (str.equals("right"))
									{
										gt.game.variables.p1_keyboardState[3] = true;
									}

									else if (str.equals("space"))
									{
										gt.game.variables.p1_keyboardState[4] = true;
									}

									//when key is released
									if (str.equals("upno"))
									{
										gt.game.variables.p1_keyboardState[0] = false;
									}

									else if (str.equals("downno"))
									{
										gt.game.variables.p1_keyboardState[1] = false;
									}

									else if (str.equals("leftno"))
									{
										gt.game.variables.p1_keyboardState[2] = false;
									}

									else if (str.equals("rightno"))
									{
										gt.game.variables.p1_keyboardState[3] = false;
									}

									else if (str.equals("spaceno"))
									{
										gt.game.variables.p1_keyboardState[4] = false;
									}
								}
							}
						}
					}
					
					else if (str.charAt(0) == 'L') //player 2 key detection
					{
						str = str.substring(1); //lop off the L
						synchronized (st.ongoingGames) {
							for (GameThread gt : st.ongoingGames.keySet()) {
								if (st.ongoingGames.get(gt).contains(this)) {
									if (str.equals("up"))
									{
										gt.game.variables.p2_keyboardState[0] = true;
									}

									else if (str.equals("down"))
									{
										gt.game.variables.p2_keyboardState[1] = true;
									}

									else if (str.equals("left"))
									{
										gt.game.variables.p2_keyboardState[2] = true;
									}

									else if (str.equals("right"))
									{
										gt.game.variables.p2_keyboardState[3] = true;
									}

									else if (str.equals("space"))
									{
										gt.game.variables.p2_keyboardState[4] = true;
									}

									//when key is released
									if (str.equals("upno"))
									{
										gt.game.variables.p2_keyboardState[0] = false;
									}

									else if (str.equals("downno"))
									{
										gt.game.variables.p2_keyboardState[1] = false;
									}

									else if (str.equals("leftno"))
									{
										gt.game.variables.p2_keyboardState[2] = false;
									}

									else if (str.equals("rightno"))
									{
										gt.game.variables.p2_keyboardState[3] = false;
									}

									else if (str.equals("spaceno"))
									{
										gt.game.variables.p2_keyboardState[4] = false;
									}
								}
							}
						}
					}

					else if (str.charAt(0) == 'M')
					{
						synchronized (st.ongoingGames) {
							Random rand = new Random();
							int num = rand.nextInt(st.ongoingGames.size());
							int counter = 0;
							st.ongoingGames.values();
							for (GameThread thread : st.ongoingGames.keySet()) {
								if (counter == num) {
									st.ongoingGames.get(thread).add(this);
									if (str.charAt(1) == 'U') {
										this.pw.println("P" + thread.game.variables.stringify()); // send variables
										this.pw.flush();										
									} else if (str.charAt(1) == 'G') {
										this.pw.println("Q" + thread.game.variables.stringify()); // send variables
										this.pw.flush();
									}
									break;
								}
								else counter++;
							}
						}
					}

					else if (str.charAt(0) == 'N')
					{
						if (str.charAt(1) == 'U') {
							Long search = Long.parseLong(str.substring(2));
							synchronized (st.ongoingGames) {
								boolean found = false;
								for (GameThread thread : st.ongoingGames.keySet()) {
									if (thread.gameID == search) {
										st.ongoingGames.get(thread).add(this);
										this.pw.println("P" + thread.game.variables.stringify()); // found gameID, send variables
										this.pw.flush();
										found = true;
										break;
									}
								}
								if (!found) {
									this.pw.println("R"); // invalid gameID, send error message?
									this.pw.flush();									
								}
							}							
						} else if (str.charAt(1) == 'G') {
							Long search = Long.parseLong(str.substring(2));
							synchronized (st.ongoingGames) {
								boolean found = false;
								for (GameThread thread : st.ongoingGames.keySet()) {
									if (thread.gameID == search) {
										st.ongoingGames.get(thread).add(this);
										this.pw.println("Q" + thread.game.variables.stringify()); // found gameID, send variables
										this.pw.flush();
										found = true;
										break;
									}
								}
								if (!found) {
									this.pw.println("S"); // invalid gameID, send error message?
									this.pw.flush();									
								}
							}														
						}
					}

					else if (str.charAt(0) == 'O')
					{
						synchronized (st) {
							if (opponentThread.readyToPlay) {
								opponentThread.readyToPlay = false;
								HashSet<ServerHelperThread> set = new HashSet<ServerHelperThread>();
								set.add(opponentThread);
								set.add(this);
								str = str.substring(2);
								String delims = "[$]";
								String [] tokens = str.split(delims);
								GameThread gt = new GameThread(tokens[5], tokens[0], tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[7]), Integer.valueOf(tokens[7]), Integer.valueOf(tokens[6]), tokens[4], st, false);
								gt.gameID = st.gameIDcounter;
								st.ongoingGames.put(gt, set);
								// made the game on the server, now set variables for the two clients
								this.pw.println("G" + str + "$" + st.gameIDcounter);
								this.pw.flush();
								opponentThread.pw.println("G" + str + "$" + st.gameIDcounter);
								opponentThread.pw.flush();
								st.gameIDcounter++;
								gt.start();
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
					
					else if (str.charAt(0) == 'S')
					{
						synchronized (st.ongoingGames) {
							for (Set<ServerHelperThread> set : st.ongoingGames.values()) {
								if (set.contains(this)) {
									set.remove(this);
								}
							}
						}
					}
					
					else if (str.charAt(0) == 'Z')
					{
						synchronized (st.shtVector) {
							if (st.shtVector.contains(this)) st.shtVector.remove(this);

						}
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
