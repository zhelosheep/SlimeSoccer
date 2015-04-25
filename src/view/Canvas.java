package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.Game;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements Runnable {
	Thread gameLoop;
	
	public Canvas() {
		// set up JPanel stuff
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(Frame.controller);
        setDoubleBuffered(true);
        
        gameLoop = new Thread(this);
        gameLoop.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Game.imgBackgroundSoccerfield, 0,0, null);
		g.drawLine(0, Game.groundLevel, 600, Game.groundLevel);
		g.drawRect(0, Game.groundLevel - 100, 50, 100);
		g.drawRect(550, Game.groundLevel - 100, 50, 100);
		Game.slime1.paint(g);
		Game.slime2.paint(g);
		Game.ball.paint(g);
	}
	
	public void run() {
        // variables used to determine how long to wait until loop starts again
        long beginTime, timeTaken, timeLeft;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            // do something
            Game.update();
            
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