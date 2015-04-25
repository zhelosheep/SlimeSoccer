package view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import controller.Controller;
import model.Game;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	public static Game model;
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
		model = new Game("laptop", "SlimeMagnet", "SlimeSuper", "shawnren", "josemama", 100, 100, 10, null);
		// set up controller
		controller = new Controller();
		// set up view
		view = new Canvas();
		add(view, BorderLayout.CENTER);
	}
	public static void main (String [] args) {
		Frame guiFrame = new Frame();
		guiFrame.setVisible(true);
	}
}
