package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.Variables;

public class Canvas extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	Thread gameLoop;
	int numTimesLeftToPrintScore, numTimesToPrintScore = 30;
	Variables variables;

	public Canvas() {
		// set up JPanel stuff
		setFocusable(true);
		requestFocusInWindow();
        setDoubleBuffered(true);
        
        numTimesLeftToPrintScore = -1;
        
        gameLoop = new Thread(this);
        variables = new Variables();
	}
	
	public void begin() {
		gameLoop.start();
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
		g.fillRoundRect(drawAtLeft, 50, (int)((variables.player2_manaCurrent/variables.player2_manaMax)*80), 10, 5, 5); // update
		g.setFont(new Font("Helvetica", Font.PLAIN, 40));
		if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
		g.drawString(variables.player2_score.toString(), drawAtLeft - 50, 45); // update
		
		// if a player scores, let them know
		if (variables.player1scored) { // update
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
			g.drawString("Player 1 scored!", 200, 300 - 100);
			if (numTimesLeftToPrintScore == -1) { // first loop in which Game.player1scored is true
				numTimesLeftToPrintScore = numTimesToPrintScore;
			} else if (numTimesLeftToPrintScore == 0) { // last loop in which Game.player1scored will be true and last time drawString will be called
				variables.player1scored = false;
				numTimesLeftToPrintScore--;
			} else if (numTimesLeftToPrintScore <= numTimesToPrintScore && numTimesLeftToPrintScore > 0) {
				numTimesLeftToPrintScore--;
			}
		}
		if (variables.player2scored) { // update
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			if (variables.background.equals("Outer Space")) { g.setColor(Color.WHITE); }
			g.drawString("Player 2 scored!", 200, 300 - 100);
			if (numTimesLeftToPrintScore == -1) { // first loop in which Game.player1scored is true
				numTimesLeftToPrintScore = numTimesToPrintScore;
			} else if (numTimesLeftToPrintScore == 0) { // last loop in which Game.player1scored will be true and last time drawString will be called
				variables.player2scored = false;
				numTimesLeftToPrintScore--;
			} else if (numTimesLeftToPrintScore <= numTimesToPrintScore && numTimesLeftToPrintScore > 0) {
				numTimesLeftToPrintScore--;
			}
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
				g.drawImage(variables.imgGameoverPlayer1, 0, 0, null);
			} else if (variables.playerThatWon == 2) {
				g.drawImage(variables.imgGameoverPlayer2, 0, 0, null);
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Score: " + variables.player1_score + "-" + variables.player2_score, 250, 250); // update
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
	
}