package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;

public class Controller implements KeyListener {
    // Keyboard states (true = pressed, false = not pressed)
    private static boolean[] keyboardState = new boolean[525];
    PrintWriter sWriter;
   
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
		
//		if (event.getKeyCode() == KeyEvent.VK_UP)
//		{
//			sWriter.println("K" + "up");
//			sWriter.flush();
//		}
//		
//		else if (event.getKeyCode() == KeyEvent.VK_DOWN)
//		{
//			sWriter.println("K" + "down");
//			sWriter.flush();
//		}
//		
//		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
//		{
//			sWriter.println("K" + "left");
//			sWriter.flush();
//		}
//		
//		else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
//		{
//			sWriter.println("K" + "right");
//			sWriter.flush();
//		}
//		
//		else if (event.getKeyCode() == KeyEvent.VK_SPACE)
//		{
//			sWriter.println("K" + "space");
//			sWriter.flush();
//		}
		
        keyboardState[event.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
        keyboardState[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}
