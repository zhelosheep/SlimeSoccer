package network;

import java.io.IOException;

import javax.swing.JFrame;

import view.MainMenuGuest;
import view.MainMenuUser;

public class ClientThread extends Thread {
	private MainMenuGuest mmg = null;
	private MainMenuUser mmu = null;
	private boolean isGuest = false;

	public ClientThread(JFrame mm, boolean isGuest) {
		this.isGuest = isGuest;
		if (isGuest) mmg = (MainMenuGuest) mm;
		else mmu = (MainMenuUser) mm;
	}
	
	public void run() {
		while (true) {
			try {
				String str;
				if (isGuest) {
					str = mmg.sReader.readLine();
					if (str.charAt(0) == 'C') { // chat
						mmg.chatArea.setText(mmg.chatArea.getText() + "\n" + str.substring(1));
					}
				} else {
					str = mmu.sReader.readLine();
					if (str.charAt(0) == 'C') { // chat
						mmu.chatArea.setText(mmu.chatArea.getText() + "\n" + str.substring(1));
					}
				}
			} catch (IOException ioe) {
				System.out.println("IOException in ClientThread.run(): " + ioe.getMessage());
			}
		}
	}
}
