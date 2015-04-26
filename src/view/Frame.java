package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import network.GameThread;
import controller.Controller;

@SuppressWarnings("serial")
public class Frame extends JFrame {
//	public static Game model;
	public static GameThread gt;
	public static Controller controller;
	public static Canvas view;

	public Frame() {
		// default JFrame stuff
		super("Slime Soccer");
//		setSize(800,600);
		setSize(600,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set up model
//		model = new Game("outerspace", "SlimeBowAndArrow", "SlimeBowAndArrow", "shawnren", "josemama", 100, 100, 1, "");
		gt = new GameThread("Outer Space", "Slime3D", "SlimeSweater", "shawnren", "josemama", 100, 100, 1, "");
		// set up controller
		controller = new Controller();
		// set up view
		view = new Canvas();
		add(view, BorderLayout.CENTER);
	}
	public static void main (String [] args) {
		Frame guiFrame = new Frame();
		view.variables = gt.game.variables;
		guiFrame.setVisible(true);
		gt.start();
		view.start();
	}
}
