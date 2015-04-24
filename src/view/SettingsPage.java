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
import javax.swing.JSlider;

public class SettingsPage extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton cancelButton, saveButton;
	private JSlider sfxSlider, musicSlider; 
	private JFrame prevScreen = null;

	public SettingsPage() {
		setSize(800, 600);
		setLocation(300,100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		cancelButton = new JButton("Cancel");
		saveButton = new JButton("Save");
		sfxSlider = new JSlider(JSlider.HORIZONTAL);
		musicSlider = new JSlider(JSlider.HORIZONTAL);
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
		JLabel settingsLabel = new JLabel("<html><div style=\"text-align: center;\">Settings");
		jp1.add(settingsLabel);
		JPanel jp2 = new JPanel();
		JLabel sfxLabel = new JLabel("Sound Effects: ");
		jp2.add(sfxLabel);
		jp2.add(sfxSlider);
		JPanel jp3 = new JPanel();
		JLabel musicLabel = new JLabel("Music: ");
		jp3.add(musicLabel);
		jp3.add(musicSlider);
		JPanel jp4 = new JPanel();
		jp4.add(saveButton);
		jp4.add(cancelButton);
		centerPanel.add(jp1);
		centerPanel.add(jp2);
		centerPanel.add(jp3);
		centerPanel.add(jp4);
		centerPanel.add(Box.createGlue());
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.setVisible(true);
				setVisible(false);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.setVisible(true);
				setVisible(false);
			}
		});
	}
	
	void setPrevScreen(JFrame prevScreen) {
		this.prevScreen = prevScreen;
	}
	
	public static void main(String[] args) {
		new SettingsPage();
	}
}
