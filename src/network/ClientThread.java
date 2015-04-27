package network;

import java.io.IOException;

import javax.swing.JFrame;

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
		while (true) {
			try {
				String str;
				if (isGuest) { // this one uses mmg
					str = mmg.sReader.readLine();
					if (str == null) break;
					// str.charAt(0) is an identifier we add which tells us what to do with the string
					if (str.charAt(0) == 'C') { // chat
						mmg.chatArea.setText(mmg.chatArea.getText() + "\n" + str.substring(1));
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
						ptr.player1_manaCurrent = Integer.parseInt(splited[13]);
						ptr.player2_manaCurrent = Integer.parseInt(splited[14]);
						ptr.player1scored = Boolean.parseBoolean(splited[15]);
						ptr.player2scored = Boolean.parseBoolean(splited[16]);
						ptr.gameOver = Boolean.parseBoolean(splited[17]);
						ptr.playerThatWon = Integer.parseInt(splited[18]);
						ptr.player1_score = Integer.parseInt(splited[19]);
						ptr.player2_score = Integer.parseInt(splited[20]);
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
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.setVisible(true);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.setVisible(false);
					} else if (str.charAt(0) == 'G') {
						String delims = "[$]";
						String [] tokens = str.substring(1).split(delims);
						for (int i = 0; i < tokens.length; i++)
						{
							System.out.println("In ClientThread: Read a G label string");
							System.out.println(tokens[i]);
							//tokens[0] = p1SlimeType
							//tokens[1] = p2SlimeType
							//tokens[2] = p1Username
							//tokens[3] = p2Username
							//tokens[4] = special mode
							//tokens[5] = backgroundCombo
							//tokens[6] = regenRate
							//tokens[7] = totalMana
						}
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.setVariables(tokens[5], tokens[0], tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[7]), Integer.valueOf(tokens[7]), Integer.valueOf(tokens[6]), tokens[4]);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.gameScreen.setVisible(true);
						mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.setVisible(false);
					} else if (str.charAt(0) == 'I') {
						System.out.println("String before concat is " + str + "!");
						str = str.substring(2);
						System.out.println("I detected?");
						System.out.println(str);
						
						int index = convertNametoIndex(str);
						
						if (mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 == false)
						{
							System.out.println("Entering if PP is 1");
							System.out.println(index);
							//if slime is normal 9
							if (index < 10)
							{
								System.out.println("Slime is normal; slime is player2");
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeButtons[index].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeNames[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeAbilities[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeType = str;
							}
							
							//if slime is special
							if (index >= 10)			
							{
								System.out.println("Slime is special; slime is player2");
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimes[index-10].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeNames[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeDescriptions[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p1SlimeType = str;
							}
						}
						
						else if (mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.isPlayer1 == true)
						{
							System.out.println("Entering if PP is not 1");
							System.out.println(index);
							
							//if slime is normal 9
							if (index < 10)
							{
								System.out.println("Slime is normal; slime is player1");
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeButtons[index].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeNames[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.slimeAbilities[index]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeType = str;
							}
							
							//if slime is special
							if (index >= 10)			
							{
								System.out.println("Slime is special; slime is player1");
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeImageLabel.setIcon(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimes[index-10].getIcon());
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeNameLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeNames[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeAbilityLabel.setText(mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.specialSlimeDescriptions[index-10]);
								mmu.mainMenuUserPlayPlayer.mainMenuUserWaiting.mainMenuUserPlaySlime.p2SlimeType = str;
							}
						}
					} else if (str.charAt(0) == 'M') {
						String[] splited = str.split("\\s+");
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
						ptr.player1_manaCurrent = Integer.parseInt(splited[13]);
						ptr.player2_manaCurrent = Integer.parseInt(splited[14]);
						ptr.player1scored = Boolean.parseBoolean(splited[15]);
						ptr.player2scored = Boolean.parseBoolean(splited[16]);
						ptr.gameOver = Boolean.parseBoolean(splited[17]);
						ptr.playerThatWon = Integer.parseInt(splited[18]);
						ptr.player1_score = Integer.parseInt(splited[19]);
						ptr.player2_score = Integer.parseInt(splited[20]);
					}
				}
			} catch (IOException ioe) {
				System.out.println("IOException in ClientThread.run(): " + ioe.getMessage());
			}
		}
	}
	
	public int convertNametoIndex(String name)
	{
		System.out.println("The string to convert is " + name + "!");
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
