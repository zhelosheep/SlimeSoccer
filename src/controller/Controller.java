package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.GameScreen;
import view.MainMenuUserWaiting;

public class Controller implements KeyListener {
    // Keyboard states (true = pressed, false = not pressed)
    private static boolean[] keyboardState = new boolean[525];
    private GameScreen gs;
    
    public Controller(GameScreen gs) {
    	this.gs = gs;
    }
    
    public static boolean keyboardKeyState(int key) {
        return keyboardState[key];
    }

    public static void changeKeyboardKeyState(int key, boolean state) {
        keyboardState[key] = state;
    }

	public void keyPressed(KeyEvent event) {
		System.out.println("here");
		char c = event.getKeyChar();
		System.out.println(c);
		
		if (event.getKeyCode() == KeyEvent.VK_UP)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "up");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (event.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "down");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "left");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "right");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (event.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "space");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}

		
		
        keyboardState[event.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
        
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "upno");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "downno");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "leftno");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "rightno");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (gs.isPvCGame) {
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.println("K" + "spaceno");
				((MainMenuUserWaiting) gs.prevScreen.prevScreen).prevScreen.prevScreen.sWriter.flush();				
			}
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}
