package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
