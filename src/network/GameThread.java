package network;

import model.Game;

public class GameThread extends Thread {
	public Game game;
	private ServerThread st;
	boolean isPvCGame;
	Long gameID = (long) 0;
	
	public GameThread(String background, String player1_slimeType, String player2_slimeType, String player1_username, String player2_username, int player1_manaMax, int player2_manaMax, int manaRegenerationRate, String specialMode, ServerThread st, boolean isPvCGame) {
		game = new Game(background, player1_slimeType, player2_slimeType, player1_username, player2_username, player1_manaMax, player2_manaMax, manaRegenerationRate, specialMode, isPvCGame);
		this.st = st;
		this.isPvCGame = isPvCGame;
	}
	
	public void run() {
        long beginTime, timeTaken, timeLeft;
        
        while(true)
        {
            beginTime = System.nanoTime();
            game.update();
             
            if (!isPvCGame) {
            	String output = "M " + game.variables.ball.x + " " + game.variables.ball.y + " " + game.variables.ball.width + " " + game.variables.ball.height + " " +
            			game.variables.slime1.x + " " + game.variables.slime1.y + " " + game.variables.slime1.width + " " + game.variables.slime1.height + " " +
            			game.variables.slime2.x + " " + game.variables.slime2.y + " " + game.variables.slime2.width + " " + game.variables.slime2.height + " " +
            			game.variables.player1_manaCurrent + " " + game.variables.player2_manaCurrent + " " + game.variables.player1scored + " " + game.variables.player2scored + " " + 
            			game.variables.gameOver + " " + game.variables.playerThatWon + " " + game.variables.player1_score + " " + game.variables.player2_score + " " + game.variables.slimeHasMoved_1 + " " + game.variables.slimeHasMoved_2;


            	// repaint screen
            	// tell the canvases to paint themselves
            	for (ServerHelperThread thread : st.ongoingGames.get(this)) {
            		thread.pw.println(output);
            		thread.pw.flush();
            	}

            	game.variables.player1scored = false;
            	game.variables.player2scored = false;
            }
            
            if (game.variables.gameOver) break;
            
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
        if (!isPvCGame) {
            st.ongoingGames.remove(this);        	
        }
	}
}
