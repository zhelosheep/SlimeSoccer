package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class MainMenuGuest extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton searchButton, sendButton, backButton, randomButton;
	public JTextArea chatArea;
	private JTextField chatField, gameIDField;
	Socket s;
	public BufferedReader sReader;
	public PrintWriter sWriter;
	private static JLabel spectateLabel;
	public SpectateScreen spectateScreen;
	
	public MainMenuGuest() {
		setSize(800, 600);
		setLocation(300,100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		try {
			spectateLabel = new JLabel(new ImageIcon(ImageIO.read((getClass().getClassLoader().getResource("Spectate.png")))));
		} catch (IOException e) {
			System.out.println("IOException in MainMenuGuest.instantiateVariables (read image: " + e.getMessage());
		}
		searchButton = new JButton("Search");
		randomButton = new JButton("?? Random ??");
		sendButton = new JButton("Send");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
		backButton = new JButton("Back");
		gameIDField = new JTextField(6);
		spectateScreen = new SpectateScreen(true, this);
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
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JPanel jp1 = new JPanel();
		JLabel helloLabel = new JLabel("<html><div style=\"text-align: center;\">Hello, Guest!");
		helloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		jp1.add(helloLabel);
		JPanel jp2 = new JPanel();
		jp2.add(spectateLabel);
		jp2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		JPanel jp3 = new JPanel();
		JLabel searchLabel = new JLabel("Search by ID: ");
		jp3.add(searchLabel);
		jp3.add(gameIDField);
		jp3.add(searchButton);
		JPanel orPanel = new JPanel();
		JLabel orLabel = new JLabel("- OR -");
		orPanel.add(orLabel);
		JPanel jp4 = new JPanel();
		jp4.add(randomButton);
		jp4.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));;
		leftPanel.add(jp1);
		leftPanel.add(jp2);
		leftPanel.add(Box.createGlue());
		leftPanel.add(jp3);
		leftPanel.add(orPanel);
		leftPanel.add(jp4);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JLabel chatLabel = new JLabel("Chat");
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		((DefaultCaret)chatArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane jsp = new JScrollPane(chatArea);
		jsp.setPreferredSize(new Dimension(40, 420));
		JPanel jp5 = new JPanel();
		jp5.add(chatField);
		jp5.add(sendButton);
		JPanel jp6 = new JPanel();
		jp6.add(chatLabel);
		rightPanel.add(jp6);
		rightPanel.add(jsp);
		rightPanel.add(jp5);
		rightPanel.setPreferredSize(new Dimension(200, 400));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
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
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new LoginPage()).setVisible(true);
				dispose();
			}
		});
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sWriter.println("CGuest: " + chatField.getText());
				sWriter.flush();
				//System.out.println(chatArea.getText());
				chatArea.setText(chatArea.getText() + "\nGuest: " + chatField.getText());
				chatField.setText("");
			}
		});
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spectateScreen.setVisible(true);
				setVisible(false);
			}
		});
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spectateScreen.setVisible(true);
				setVisible(false);
			}
		});		
		
		chatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sWriter.println("CGuest: " + chatField.getText());
				sWriter.flush();
				chatArea.setText(chatArea.getText() + "\nGuest: " + chatField.getText());
				chatField.setText("");
			}
		});
	}
	
	void quit() {
		try {
			sWriter.println("Z");
			sWriter.flush();
			if (s != null) {
				s.shutdownInput();
				s.shutdownOutput();
				s.close();
				s = null;
			}
		} catch (IOException ioe) {
			System.out.println("IOException in MainMenuGuest.quit(): " + ioe.getMessage());
		}
		System.exit(0);
	}
}
