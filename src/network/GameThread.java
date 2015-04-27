package network;

import model.Game;

public class GameThread extends Thread {
	public Game game;
	private ServerThread st;
	boolean isPvCGame;
	
	public GameThread(String background, String player1_slimeType, String player2_slimeType, String player1_username, String player2_username, int player1_manaMax, int player2_manaMax, int manaRegenerationRate, String specialMode, ServerThread st, boolean isPvCGame) {
	
		game = new Game(background, player1_slimeType, player2_slimeType, player1_username, player2_username, player1_manaMax, player2_manaMax, manaRegenerationRate, specialMode);
		this.st = st;
	}
	
	public void run() {
        long beginTime, timeTaken, timeLeft;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            // do something
            if (isPvCGame) {
                game.update();
            } else {
            	
            }
            
            // repaint screen
            // tell the canvases to paint themselves
            for (ServerHelperThread thread : st.ongoingGames.get(this)) {
            	thread.pw.println("M");
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
}
