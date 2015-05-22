package network;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Variables;
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
        long beginTime, timeTaken, timeLeft;
        
		while (true) {
            beginTime = System.nanoTime();
			try {
				String str;
				if (isGuest) { // this one uses mmg
					str = mmg.sReader.readLine();
					if (str == null) break;
					// str.charAt(0) is an identifier we add which tells us what to do with the string
					if (str.charAt(0) == 'C') { // chat
						mmg.chatArea.setText(mmg.chatArea.getText() + "\n" + str.substring(1));
					} else if (str.charAt(0) == 'Q') {
						String delims = "[$]";
						String [] tokens = str.substring(1).split(delims);
						mmg.spectateScreen.setVariables(tokens[5], tokens[0], tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[7]), Integer.valueOf(tokens[7]), Integer.valueOf(tokens[6]), tokens[4]);
						mmg.spectateScreen.setVisible(true);
						mmg.setVisible(false);
						mmg.spectateScreen.setTitle("Guest - SpectateScreen");
					} else if (str.charAt(0) == 'S') {
						JOptionPane.showMessageDialog(mmg, "Game ID does not exist", "ERROR404", JOptionPane.ERROR_MESSAGE);
					} else if (str.charAt(0) == 'M') {
						String[] splited = str.split("\\s+");
						Variables ptr = mmg.spectateScreen.primary.variables;
						ptr.ball.x = Integer.parseInt(splited[1]);
						ptr.ball.y = Integer.parseInt(splited[2]);
						ptr.ball.width = Integer.parseInt(splited[3]);
						ptr.ball.height = Integer.parseInt(splited[4]);
						ptr.slime1.x = Integer.parseInt(splited[5]);
						ptr.slime1.y = Integer.parseInt(splited[6]);
						ptr.slime1.width = Integer.parseInt(splited[7]);
						ptr.slime1.height = Integer.parseInt(splited[8]);
						ptr.slime2.x = Integer.parseInt(splited[9]);
						ptr.slime2.y = Integer.parseInt(splited[10]);
						ptr.slime2.width = Integer.parseInt(splited[11]);
						ptr.slime2.height = Integer.parseInt(splited[12]);
						ptr.player1_manaCurrent = Double.parseDouble(splited[13]);
						ptr.player2_manaCurrent = Double.parseDouble(splited[14]);
						ptr.player1scored = Boolean.parseBoolean(splited[15]);
						ptr.player2scored = Boolean.parseBoolean(splited[16]);
						ptr.gameOver = Boolean.parseBoolean(splited[17]);
						ptr.playerThatWon = Integer.parseInt(splited[18]);
						ptr.player1_score = Integer.parseInt(splited[19]);
						ptr.player2_score = Integer.parseInt(splited[20]);
						ptr.slimeHasMoved_1 = Boolean.parseBoolean(splited[21]);
						ptr.slimeHasMoved_2 = Boolean.parseBoolean(splited[22]);
					}
				} else { // this one uses mmu
					str = mmu.sReader.readLine();
					if (str == null) break;
					if (str.charAt(0) == 'C') { // chat
						mmu.chatArea.setText(mmu.chatArea.getText() + "\n" + str.substring(1));
					} else if (str.charAt(0) == 'D') { 
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.chatArea.append("\n" + str.substring(1));
						mmu.mainMenuUserSpectate.spectateScreen.chatArea.append("\n" + str.substring(1));
					} else if (str.charAt(0) == 'Y') {
						if (str.charAt(1) == '1') {
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 = true;
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1Username = mmu.getUsername();
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1UsernameLabel.setText(mmu.getUsername());
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2Username = str.substring(2);
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2UsernameLabel.setText("<html><div style=\"text-align: center;\">" + str.substring(2));
						}
						else {
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 = false;
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2Username = mmu.getUsername();
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2UsernameLabel.setText(mmu.getUsername());
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1Username = str.substring(2);
							mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1UsernameLabel.setText("<html><div style=\"text-align: center;\">" + str.substring(2));
						}
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.setEnabled(true);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.setVisible(true);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.setVisible(false);
					} else if (str.charAt(0) == 'G') {
						String delims = "[$]";
						String [] tokens = str.substring(1).split(delims);
							//tokens[0] = p1SlimeType
							//tokens[1] = p2SlimeType
							//tokens[2] = p1Username
							//tokens[3] = p2Username
							//tokens[4] = special mode
							//tokens[5] = backgroundCombo
							//tokens[6] = regenRate
							//tokens[7] = totalMana
						    //tokens[8] = gameID
//						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.primary = new Canvas(mmu.username);
//						System.out.println("new Canvas");
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.setVariables(tokens[5], tokens[0], tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[7]), Integer.valueOf(tokens[7]), Integer.valueOf(tokens[6]), tokens[4]);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.gameIDLabel.setText("Game ID: " + Long.parseLong(tokens[8]));
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.setVisible(true);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.setVisible(false);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.setTitle(mmu.getUsername() + " - GameScreen");
						
					} else if (str.charAt(0) == 'I') {
						str = str.substring(2);
						
						int index = convertNametoIndex(str);
						
						if (mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 == false)
						{
							//if slime is normal 9
							if (index < 10)
							{
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeButtons[index].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeNames[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeAbilities[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeType = str;
							}
							
							//if slime is special
							if (index >= 10)			
							{
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimes[index-10].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeNames[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeDescriptions[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeType = str;
							}
						}
						
						else if (mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 == true)
						{
							//if slime is normal 9
							if (index < 10)
							{
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeButtons[index].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeNames[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeAbilities[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeType = str;
							}
							
							//if slime is special
							if (index >= 10)			
							{
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimes[index-10].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeNames[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeDescriptions[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeType = str;
							}
						}
					} else if (str.charAt(0) == 'M') {		//random game is found and plays
						String [] splited = str.split("\\s+");
//						String delims = "[$]";
//						String [] tokens = str.substring(1).split(delims);
							//tokens[0] = p1SlimeType
							//tokens[1] = p2SlimeType
							//tokens[2] = p1Username
							//tokens[3] = p2Username
							//tokens[4] = special mode
							//tokens[5] = backgroundCombo
							//tokens[6] = regenRate
							//tokens[7] = totalMana
						    //tokens[8] = gameID
						//!! If we find a game, then we get the rest of the varibles.
						//mmu.mainMenuUserSpectate.spectateScreen.setVariables(tokens[5], tokens[0], tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[7]), Integer.valueOf(tokens[7]), Integer.valueOf(tokens[6]), tokens[4]);
						
						if (mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.isVisible()) {
							Variables ptr = mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.primary.variables;
							ptr.ball.x = Integer.parseInt(splited[1]);
							ptr.ball.y = Integer.parseInt(splited[2]);
							ptr.ball.width = Integer.parseInt(splited[3]);
							ptr.ball.height = Integer.parseInt(splited[4]);
							ptr.slime1.x = Integer.parseInt(splited[5]);
							ptr.slime1.y = Integer.parseInt(splited[6]);
							ptr.slime1.width = Integer.parseInt(splited[7]);
							ptr.slime1.height = Integer.parseInt(splited[8]);
							ptr.slime2.x = Integer.parseInt(splited[9]);
							ptr.slime2.y = Integer.parseInt(splited[10]);
							ptr.slime2.width = Integer.parseInt(splited[11]);
							ptr.slime2.height = Integer.parseInt(splited[12]);
							ptr.player1_manaCurrent = Double.parseDouble(splited[13]);
							ptr.player2_manaCurrent = Double.parseDouble(splited[14]);
							ptr.player1scored = Boolean.parseBoolean(splited[15]);
							ptr.player2scored = Boolean.parseBoolean(splited[16]);
							ptr.gameOver = Boolean.parseBoolean(splited[17]);
							ptr.playerThatWon = Integer.parseInt(splited[18]);
							ptr.player1_score = Integer.parseInt(splited[19]);
							ptr.player2_score = Integer.parseInt(splited[20]);
							ptr.slimeHasMoved_1 = Boolean.parseBoolean(splited[21]);
							ptr.slimeHasMoved_2 = Boolean.parseBoolean(splited[22]);
						} else if (mmu.mainMenuUserSpectate.spectateScreen.isVisible()) {
							Variables ptr = mmu.mainMenuUserSpectate.spectateScreen.primary.variables;
							ptr.ball.x = Integer.parseInt(splited[1]);
							ptr.ball.y = Integer.parseInt(splited[2]);
							ptr.ball.width = Integer.parseInt(splited[3]);
							ptr.ball.height = Integer.parseInt(splited[4]);
							ptr.slime1.x = Integer.parseInt(splited[5]);
							ptr.slime1.y = Integer.parseInt(splited[6]);
							ptr.slime1.width = Integer.parseInt(splited[7]);
							ptr.slime1.height = Integer.parseInt(splited[8]);
							ptr.slime2.x = Integer.parseInt(splited[9]);
							ptr.slime2.y = Integer.parseInt(splited[10]);
							ptr.slime2.width = Integer.parseInt(splited[11]);
							ptr.slime2.height = Integer.parseInt(splited[12]);
							ptr.player1_manaCurrent = Double.parseDouble(splited[13]);
							ptr.player2_manaCurrent = Double.parseDouble(splited[14]);
							ptr.player1scored = Boolean.parseBoolean(splited[15]);
							ptr.player2scored = Boolean.parseBoolean(splited[16]);
							ptr.gameOver = Boolean.parseBoolean(splited[17]);
							ptr.playerThatWon = Integer.parseInt(splited[18]);
							ptr.player1_score = Integer.parseInt(splited[19]);
							ptr.player2_score = Integer.parseInt(splited[20]);
							ptr.slimeHasMoved_1 = Boolean.parseBoolean(splited[21]);
							ptr.slimeHasMoved_2 = Boolean.parseBoolean(splited[22]);
						}
//			            System.out.println("slime1: " + ptr.slime1.x + " " + ptr.slime1.y + " slime2: " + ptr.slime2.x + " " + ptr.slime2.y);
					} else if (str.charAt(0) == 'P') {
						String delims = "[$]";
						String [] tokens = str.substring(1).split(delims);
						mmu.mainMenuUserSpectate.spectateScreen.setVariables(tokens[5], tokens[0], tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[7]), Integer.valueOf(tokens[7]), Integer.valueOf(tokens[6]), tokens[4]);
						mmu.mainMenuUserSpectate.spectateScreen.setVisible(true);
						mmu.mainMenuUserSpectate.setVisible(false);
						mmu.mainMenuUserSpectate.spectateScreen.setTitle(mmu.getUsername() + " - SpectateScreen");
					} else if (str.charAt(0) == 'R') {
						JOptionPane.showMessageDialog(mmu, "Game ID does not exist", "ERROR404", JOptionPane.ERROR_MESSAGE);												
					}
				}
			} catch (IOException ioe) {
				System.out.println("IOException in ClientThread.run(): " + ioe.getMessage());
			}
			
            // determine how long to wait until loop starts again
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = ( (1000000000L/25) - timeTaken) / 1000000L; // in milliseconds
            // if the time is less than 10 milliseconds, then sleep this thread for 10 milliseconds so another thread can do work
            if (timeLeft < 10) 
                timeLeft = 10; // set a minimum
            try {
                 Thread.sleep(timeLeft);
            } catch (InterruptedException ex) {}
		}
	}
	
	public int convertNametoIndex(String name)
	{
		if (name.equals("SlimeBomb"))
		{
			return 0;
		}
		
		else if (name.equals("SlimeBowAndArrow"))
		{
			return 1;
		}
		
		else if (name.equals("SlimeClone"))
		{
			return 2;
		}
		
		else if (name.equals("SlimeCosmic"))
		{
			return 3;
		}
		
		else if (name.equals("SlimeFireball"))
		{
			return 4;
		}
		
		else if (name.equals("SlimeFisher"))
		{
			return 5;
		}
		
		else if (name.equals("SlimeGeyser"))
		{
			return 6;
		}
		
		else if (name.equals("SlimeMagnet"))
		{
			return 7;
		}
		
		else if (name.equals("SlimeSuperSize"))
		{
			return 8;
		}
		
		else if (name.equals("SlimeSuper"))
		{
			return 9;
		}
		
		else if (name.equals("Slime3D"))
		{
			return 10;
		}
		
		else if (name.equals("SlimeButterfly"))
		{
			return 11;
		}
		
		else if (name.equals("SlimeRonaldo"))
		{
			return 12;
		}
		
		else if (name.equals("SlimeSweater"))
		{
			return 13;
		}
		
		else if (name.equals("SlimeCrown"))
		{
			return 14;
		}
		
		else if (name.equals("SlimeDunce"))
		{
			return 15;
		}
		
		else if (name.equals("LSlime"))
		{
			return 16;
		}
		
		else if (name.equals("SlimePotato"))
		{
			return 17;
		}
		
		return 20;
	}
}
