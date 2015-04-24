package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuUserWaiting extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	private MainMenuUserPlayPlayer prevScreen;

	public MainMenuUserWaiting(MainMenuUserPlayPlayer prevScreen) {
		setSize(800, 600);
		setLocation(300,100);
		this.prevScreen = prevScreen;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		cancelButton = new JButton("Cancel");
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(Box.createGlue());
		add(northPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JPanel jp1 = new JPanel();
		JLabel waitLabel = new JLabel("<html><div style=\"text-align: center;\">Waiting for other player to connect...<br>(Player must be searching you at the same time to connect)");
		jp1.add(waitLabel);
		JPanel jp2 = new JPanel();
		jp2.add(cancelButton);
		jp1.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		jp2.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		centerPanel.add(jp1);
		centerPanel.add(jp2);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevScreen.setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		new MainMenuUserWaiting(new MainMenuUserPlayPlayer(new MainMenuUser("faketechguy")));
	}
}
