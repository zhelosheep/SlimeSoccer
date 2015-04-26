package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Game;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements Runnable {
	Thread gameLoop;
	int numTimesLeftToPrintScore, numTimesToPrintScore = 30;
	private Game game;
	
	public Canvas(Game game) {
		// set up JPanel stuff
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(Frame.controller);
        setDoubleBuffered(true);
        
        this.game = game;
        numTimesLeftToPrintScore = -1;
        
        gameLoop = new Thread(this);
        gameLoop.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw background
		g.drawImage(game.imgBackground, 0,0, null);
		if (game.background.equals("outerspace")) { g.setColor(Color.WHITE); }
		g.drawLine(game.leftBoundary, game.groundLevel, game.rightBoundary, game.groundLevel);
		
		// draw player1 stats
		g.setFont(new Font("Helvetica", Font.PLAIN, 14));
		if (game.background.equals("outerspace")) { g.setColor(Color.WHITE); }
		g.drawString(game.player1_slimeType, 20, 20);
		g.drawString(game.player1_username, 20, 40);
		g.drawRoundRect(20, 50, 80, 10, 5, 5);
		g.fillRoundRect(20, 50, (int)((game.player1_manaCurrent/game.player1_manaMax)*80), 10, 5, 5);
		g.setFont(new Font("Helvetica", Font.PLAIN, 40));
		if (game.background.equals("outerspace")) { g.setColor(Color.WHITE); }
		g.drawString(game.player1_score.toString(), 130, 45);
		
		// draw player2 stats
		g.setFont(new Font("Helvetica", Font.PLAIN, 14));
		if (game.background.equals("outerspace")) { g.setColor(Color.WHITE); }
		g.drawString(game.player2_slimeType, 500, 20);
		g.drawString(game.player2_username, 500, 40);
		g.drawRoundRect(500, 50, 80, 10, 5, 5);
		g.fillRoundRect(500, 50, (int)((game.player2_manaCurrent/game.player2_manaMax)*80), 10, 5, 5);
		g.setFont(new Font("Helvetica", Font.PLAIN, 40));
		if (game.background.equals("outerspace")) { g.setColor(Color.WHITE); }
		g.drawString(game.player2_score.toString(), 450, 45);
		
		// if a player scores, let them know
		if (game.player1scored) {
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			if (game.background.equals("outerspace")) { g.setColor(Color.WHITE); }
			g.drawString("Player 1 scored!", 200, 300 - 100);
			if (numTimesLeftToPrintScore == -1) { // first loop in which Game.player1scored is true
				numTimesLeftToPrintScore = numTimesToPrintScore;
			} else if (numTimesLeftToPrintScore == 0) { // last loop in which Game.player1scored will be true and last time drawString will be called
				game.player1scored = false;
				numTimesLeftToPrintScore--;
			} else if (numTimesLeftToPrintScore <= numTimesToPrintScore && numTimesLeftToPrintScore > 0) {
				numTimesLeftToPrintScore--;
			}
		}
		if (game.player2scored) {
			g.setFont(new Font("Helvetica", Font.PLAIN, 30));
			if (game.background.equals("outerspace")) { g.setColor(Color.WHITE); }
			g.drawString("Player 2 scored!", 200, 300 - 100);
			if (numTimesLeftToPrintScore == -1) { // first loop in which Game.player1scored is true
				numTimesLeftToPrintScore = numTimesToPrintScore;
			} else if (numTimesLeftToPrintScore == 0) { // last loop in which Game.player1scored will be true and last time drawString will be called
				game.player2scored = false;
				numTimesLeftToPrintScore--;
			} else if (numTimesLeftToPrintScore <= numTimesToPrintScore && numTimesLeftToPrintScore > 0) {
				numTimesLeftToPrintScore--;
			}
		}
		
		// draw game objects
		game.slime1.paint(g);
		game.slime2.paint(g);
		game.ball.paint(g);
		game.goal1.paint(g);
		game.goal2.paint(g);
		
	}
	
	public void run() {
        // variables used to determine how long to wait until loop starts again
        long beginTime, timeTaken, timeLeft;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            // do something
            game.update();
            
            // repaint screen
            repaint();
            
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