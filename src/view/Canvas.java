package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Variables;

public class Canvas extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	Thread gameLoop;
	int p1numTimesLeftToPrintScore, p2numTimesLeftToPrintScore, numTimesToPrintScore = 30;
	public Variables variables;
	String username;
	
	public Canvas(String username) {
		this.username = username;
		// set up JPanel stuff
		setFocusable(true);
		requestFocusInWindow();
        setDoubleBuffered(true);
        
        p1numTimesLeftToPrintScore = -1;
        p2numTimesLeftToPrintScore = -1;
        
        gameLoop = new Thread(this);
        variables = new Variables();
	}
	
	public void begin() {
		if (!gameLoop.isAlive()) gameLoop.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw background
		g.drawImage(variables.imgBackground, 0,0, null);
		if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
		g.drawLine(variables.leftBoundary, variables.groundLevel, variables.rightBoundary, variables.groundLevel);
		
		// draw player1 stats
		g.setFont(new Font("Helvetica", Font.PLAIN, 14));
		if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
		g.drawString(variables.player1_slimeType, 20, 20);
		g.drawString(variables.player1_username, 20, 40);
		g.drawRoundRect(20, 50, 80, 10, 5, 5);
//		System.out.println("p1: " + variables.player1_manaCurrent + "/" + variables.player1_manaMax + " -> " + (int)((variables.player1_manaCurrent/variables.player1_manaMax)*80));
		g.fillRoundRect(20, 50, (int)((variables.player1_manaCurrent/variables.player1_manaMax)*80), 10, 5, 5); // update
		g.setFont(new Font("Helvetica", Font.PLAIN, 40));
		if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
		g.drawString(variables.player1_score.toString(), 160, 45); // update
		
		// draw player2 stats
		int drawAtLeft = 460;
		g.setFont(new Font("Helvetica", Font.PLAIN, 14));
		if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
		g.drawString(variables.player2_slimeType, drawAtLeft, 20);
		g.drawString(variables.player2_username, drawAtLeft, 40);
		g.drawRoundRect(drawAtLeft, 50, 80, 10, 5, 5);
//		System.out.println("p2: " + variables.player2_manaCurrent + "/" + variables.player2_manaMax + " -> " + (int)((variables.player2_manaCurrent/variables.player2_manaMax)*80));
		g.fillRoundRect(drawAtLeft, 50, (int)((variables.player2_manaCurrent/variables.player2_manaMax)*80), 10, 5, 5); // update
		g.setFont(new Font("Helvetica", Font.PLAIN, 40));
		if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
		g.drawString(variables.player2_score.toString(), drawAtLeft - 50, 45); // update
		
		// if a player scores, let them know
/*		if (variables.player1scored) { // update
			p1numTimesLeftToPrintScore = numTimesToPrintScore;
		}*/
		if (p1numTimesLeftToPrintScore > 0) {
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
			g.drawString("Player 1 scored!", 200, 300 - 100);
//			if (numTimesLeftToPrintScore == -1) { // first loop in which Game.player1scored is true
//				numTimesLeftToPrintScore = numTimesToPrintScore;
//			} else if (numTimesLeftToPrintScore == 0) { // last loop in which Game.player1scored will be true and last time drawString will be called
//				variables.player1scored = false;
				p1numTimesLeftToPrintScore--;
//			} else if (numTimesLeftToPrintScore <= numTimesToPrintScore && numTimesLeftToPrintScore > 0) {
//				numTimesLeftToPrintScore--;
//			}
		}
/*		if (variables.player2scored) { // update
			p2numTimesLeftToPrintScore = numTimesToPrintScore;
		}*/
		if (p2numTimesLeftToPrintScore > 0) {
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
			g.drawString("Player 2 scored!", 200, 300 - 100);
//			if (p2numTimesLeftToPrintScore == -1) { // first loop in which Game.player1scored is true
//				p2numTimesLeftToPrintScore = numTimesToPrintScore;
//			} else if (numTimesLeftToPrintScore == 0) { // last loop in which Game.player1scored will be true and last time drawString will be called
//				variables.player2scored = false;
				p2numTimesLeftToPrintScore--;
//			} else if (numTimesLeftToPrintScore <= numTimesToPrintScore && numTimesLeftToPrintScore > 0) {
//				numTimesLeftToPrintScore--;
//			}
		}
		
		// draw game objects
//		System.out.println("in paint component");
		if (variables.slime1 != null) variables.slime1.paint(g); // update
//		else System.out.println("slime1 is null");
		if (variables.slime2 != null) variables.slime2.paint(g); // update
//		System.out.println("slime2 is null");
		if (variables.ball != null) variables.ball.paint(g); // update
//		System.out.println("ball is null");
		if (variables.goal1 != null) variables.goal1.paint(g); // update
//		System.out.println("goal1 is null");
		if (variables.goal2 != null) variables.goal2.paint(g); // update
//		System.out.println("goal2 is null");
		
		// if game over, let user know
		if (variables.gameOver) {  // update
			if (variables.playerThatWon == 1) { // update
				if (variables.player1_username.equals(username)) {
					g.drawImage(variables.imgGameoverPlayer1, 0, 0, null);
				} else if (variables.player2_username.equals(username)) {
					g.drawImage(variables.imgGameoverLose, 0, 0, null);
				} else {
					g.drawImage(variables.imgGuestEndSpectate, 0, 0, null);
				}
			} else if (variables.playerThatWon == 2) {
				if (variables.player2_username.equals(username)) {
					g.drawImage(variables.imgGameoverPlayer2, 0, 0, null);
				} else if (variables.player1_username.equals(username)){
					g.drawImage(variables.imgGameoverLose, 0, 0, null);
				} else {
					g.drawImage(variables.imgGuestEndSpectate, 0, 0, null);
				}
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Score: " + variables.player1_score + "-" + variables.player2_score, 250, 250); // update
			
			if (!username.toLowerCase().equals("guest") && !username.toLowerCase().equals("computer")) checkAchievements();
		}
	}
	
	public void run() {
        // variables used to determine how long to wait until loop starts again
        long beginTime, timeTaken, timeLeft;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            // repaint screen
            this.repaint();
            
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
	
	private void checkAchievements() {
		// N00b – Play first game
		if (LoginPage.sqli.getGames(username) == 1 && !LoginPage.sqli.checkAchievement(username, LoginPage.noob_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.noob_a);
			JOptionPane.showMessageDialog(this, "You received the N00B achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		}
		
		// No Life Award – Play 1000 games
		if (LoginPage.sqli.getGames(username) == 1000 && !LoginPage.sqli.checkAchievement(username, LoginPage.nolife_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.nolife_a);
			JOptionPane.showMessageDialog(this, "You received the NO LIFE achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		}
	
		// Victorious – Win 10 games
		if (LoginPage.sqli.getWins(username) == 10 && !LoginPage.sqli.checkAchievement(username, LoginPage.vict_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.vict_a);
			JOptionPane.showMessageDialog(this, "You received the VICTORIOUS achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		}
		
		// Loser – Lose 5 games in a row
		if (LoginPage.sqli.getGamesLostInARow(username) == 5 && !LoginPage.sqli.checkAchievement(username, LoginPage.loser_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.loser_a);
			JOptionPane.showMessageDialog(this, "You received the LOSER achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		}
		
		// Cristiano Ronaldo – Have a 2:1 win lose ratio or greater
		if (LoginPage.sqli.getRatio(username) >= 2 && !LoginPage.sqli.checkAchievement(username, LoginPage.chris_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.chris_a);
			JOptionPane.showMessageDialog(this, "You received the CRISTIANO RONALDO achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		}

		// Unathletic Athlete – Have a 1:10 win/loss ratio or less
		if (LoginPage.sqli.getRatio(username) <= 1/10 && LoginPage.sqli.getGames(username) >= 10 && !LoginPage.sqli.checkAchievement(username, LoginPage.unath_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.unath_a);
			JOptionPane.showMessageDialog(this, "You received the UNATHLETIC ATHLETE achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		}
		
		// Packing on the Pounds – Don’t move your slime at all during a game
		if (variables.player1_username.equals(username) && !variables.slimeHasMoved_1 && !LoginPage.sqli.checkAchievement(username, LoginPage.pack_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.pack_a);
			JOptionPane.showMessageDialog(this, "You received the PACKING ON THE POUNDS achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		} else if (variables.player2_username.equals(username) && !variables.slimeHasMoved_2 && !LoginPage.sqli.checkAchievement(username, LoginPage.pack_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.pack_a);
			JOptionPane.showMessageDialog(this, "You received the PACKING ON THE POUNDS achievement!", "Achievement Earned!", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
}