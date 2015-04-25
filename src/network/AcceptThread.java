package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class AcceptThread extends Thread {
	private ServerThread st;
	
	AcceptThread(ServerThread st) {
		this.st = st;
	}
	
	public void run() {
		try {
			ServerHelperThread sht = new ServerHelperThread(st.shtVector.size(), st);
			sht.s = null;
			sht.s = st.ss.accept();
			sht.br = new BufferedReader(new InputStreamReader(sht.s.getInputStream()));
			sht.pw = new PrintWriter(sht.s.getOutputStream());
			st.shtVector.addElement(sht);
			sht.start();
		} catch (IOException ioe) {
			System.out.println("IOException in AcceptThread: " + ioe.getMessage());
		}
	}
}
