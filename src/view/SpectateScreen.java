package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import model.Slime;

public class SpectateScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private Slime slime1, slime2;
	private JFrame prevScreen;
	private boolean isGuest;
	private JButton backButton, switchButton, randomButton, sendButton;
	private JTextField gameIDField, chatField;
	private JTextArea chatArea;
	
	SpectateScreen(boolean isGuest, JFrame prevScreen) {
		setSize(800, 600);
		setLocation(300,100);
		this.isGuest = isGuest;
		this.prevScreen = prevScreen;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	private void instantiateVariables() {
		backButton = new JButton("Back");
		switchButton = new JButton("Switch");
		randomButton = new JButton("Random");
		sendButton = new JButton("Send");
		gameIDField = new JTextField(10);
		chatField = new JTextField(10);
		chatArea = new JTextArea();
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(Box.createGlue());
		northPanel.add(backButton);
		add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(300, 200));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 15, 0));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JPanel jp1 = new JPanel();
		JLabel spectateLabel = new JLabel("Spectate Mode");
		jp1.add(spectateLabel);
		JPanel jp2 = new JPanel();
		JLabel switchSpecificLabel = new JLabel("Switch to specific game");
		jp2.add(switchSpecificLabel);
		JPanel jp3 = new JPanel();
		JLabel gameIDLabel = new JLabel("Game ID: ");
		jp3.add(gameIDLabel);
		jp3.add(gameIDField);
		jp3.add(switchButton);
		JPanel jp4 = new JPanel();
		JLabel switchRandomLabel = new JLabel("Switch to a random game");
		jp4.add(switchRandomLabel);
		JPanel jp5 = new JPanel();
		jp5.add(randomButton);
		JLabel chatLabel = new JLabel("Chat");
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		((DefaultCaret)chatArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane jsp = new JScrollPane(chatArea);
		jsp.setPreferredSize(new Dimension(40, 420));
		JPanel jp6 = new JPanel();
		jp6.add(chatField);
		jp6.add(sendButton);
		JPanel jp7 = new JPanel();
		jp7.add(chatLabel);
		rightPanel.add(jp1);
		rightPanel.add(jp2);
		rightPanel.add(jp3);
		rightPanel.add(jp4);
		rightPanel.add(jp5);
		rightPanel.add(jp7);
		rightPanel.add(jsp);
		rightPanel.add(jp6);
		rightPanel.setPreferredSize(new Dimension(180, 420));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		centerPanel.add(Box.createGlue());
		centerPanel.add(leftPanel);
		centerPanel.add(Box.createGlue());
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
	}
	
	void quit() {
		if (isGuest) {
			((MainMenuGuest) prevScreen).quit();
		} else {
			((MainMenuUserSpectate) prevScreen).quit();
		}
	}	
}
