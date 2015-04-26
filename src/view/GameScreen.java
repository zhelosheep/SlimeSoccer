package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameScreen extends JFrame{
	public JTextArea chatArea;
	private JTextField chatField;
	private JButton sendButton;
	
	public GameScreen()
	{
		setSize(800, 600);
		setLocation(300, 100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}

	private void instantiateVariables()
	{
		sendButton = new JButton("Send");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
	}
	
	private void addComponents()
	{
		
	}
	
	private void addListeners()
	{
		
	}
}
