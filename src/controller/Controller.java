package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    // Keyboard states (true = pressed, false = not pressed)
    private static boolean[] keyboardState = new boolean[525];
//    private static boolean[] keyboardStateWASD = new boolean [4]; // keyboard states of 'w','a','s','d' keys
//    private static boolean[] keyboardStateUpLeftDownRight = new boolean [4]; // keyboard states of up,left,down,right arrow keys
	
    public static boolean keyboardKeyState(int key) {
        return keyboardState[key];
    }

	public void keyPressed(KeyEvent event) {
        keyboardState[event.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}
