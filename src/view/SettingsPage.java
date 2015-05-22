package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class SettingsPage extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton cancelButton, saveButton, changePassword;
	private JFrame prevScreen = null;
	private JPasswordField jpf, currentPassword;
	private JButton[] avatarButtons;
	
	int avatar = -1;

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
		saveButton = new JButton("Change Avatar");
		changePassword = new JButton("Change Password");
		jpf = new JPasswordField(20);
		currentPassword = new JPasswordField(20);
		
		avatarButtons = new JButton[LoginPage.avatarImages.length];
		for (int i = 0; i < LoginPage.avatarImages.length; i++) avatarButtons[i] = new JButton(LoginPage.avatarImages[i]);
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
		JPanel avatarPanel = new JPanel();
		avatarPanel.setLayout(new GridLayout(2, 5));
		for (int i = 0; i < LoginPage.avatarImages.length; i++) avatarPanel.add(avatarButtons[i]);
		JPanel jp4 = new JPanel();
		jp4.add(saveButton);
		jp4.add(cancelButton);
		jp4.add(changePassword);
		JPanel jp5 = new JPanel();
		jp5.add(new JLabel("New Password: "));
		jp5.add(jpf);
		JPanel jp6 = new JPanel();
		jp6.add(new JLabel("Current Password: "));
		jp6.add(currentPassword);
		centerPanel.add(jp1);
		centerPanel.add(avatarPanel);
		centerPanel.add(jp6);
		centerPanel.add(jp5);
		centerPanel.add(jp4);
		centerPanel.add(Box.createGlue());
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				prevScreen.setVisible(true);
				setVisible(false);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(SettingsPage.this, "You will be logged out", "Warning", JOptionPane.INFORMATION_MESSAGE);
				if (avatar != -1) {
					LoginPage.sqli.changeAvatar(MainMenuUser.username, avatar);
					MainMenuUser.avatar = LoginPage.avatarImages[LoginPage.sqli.getImage(MainMenuUser.username)];
					new LoginPage().setVisible(true);
					LoginPage.sqli.toggleLog(MainMenuUser.username);
					dispose();
				}
				else JOptionPane.showMessageDialog(SettingsPage.this, "Select picture to change avatar", "Change Avatar", JOptionPane.ERROR_MESSAGE);
				avatar = -1;
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.setVisible(true);
				setVisible(false);
			}
		});
		
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LoginPage.sqli.validateLogin(MainMenuUser.username, String.copyValueOf(currentPassword.getPassword()))) {
					LoginPage.sqli.changePassword(MainMenuUser.username, String.copyValueOf(jpf.getPassword()));
				} else {
					JOptionPane.showMessageDialog(SettingsPage.this, "Enter current password to change password", "Incorrect Password", JOptionPane.ERROR_MESSAGE);
				}
				currentPassword.setText("");
				jpf.setText("");
			}
		});
		
		for (int i = 0; i < avatarButtons.length; i++) {
			avatarButtons[i].addActionListener(new ActionListener() {
				int i;
				
				public ActionListener init(int i) {
					this.i = i;
					return this;
				}
				
				public void actionPerformed(ActionEvent e) {
					avatar = i;
				}
			}.init(i));
		}
	}
	
	void setPrevScreen(JFrame prevScreen) {
		this.prevScreen = prevScreen;
	}
}
