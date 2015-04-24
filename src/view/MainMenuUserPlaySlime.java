package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MainMenuUserPlaySlime extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton continueButton, backButton;
	private String p1Username, p2Username;
	private JButton[] slimeButtons;
	private JLabel p1SlimeNameLabel, p2SlimeNameLabel, p1SlimeImageLabel, p2SlimeImageLabel, p1SlimeAbilityLabel, p2SlimeAbilityLabel;
	private JComboBox<String> specialModeCombo, backgroundCombo;
	private JSlider regenRateSlider, totalManaSlider; 
	private boolean isPlayer1 = true;

	public MainMenuUserPlaySlime(boolean isPlayer1) {
		setSize(800, 600);
		setLocation(300,100);
		this.isPlayer1 = isPlayer1;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
		setVisible(true);		
	}
	
	private void instantiateVariables() {
		p1Username = "<html><div style=\"text-align: center;\">techguychen";
		p2Username = "<html><div style=\"text-align: center;\">derpyderp";
		continueButton = new JButton("Continue");
		backButton = new JButton("Back");
		p1SlimeNameLabel = new JLabel("<html><div style=\"text-align: center;\">Bomber Slime");
		p2SlimeNameLabel = new JLabel("<html><div style=\"text-align: center;\">Bow And Arrow Slime");
		p1SlimeAbilityLabel = new JLabel("<html><div style=\"text-align: center;\">Explode");
		p2SlimeAbilityLabel = new JLabel("<html><div style=\"text-align: center;\">Shoot Arrows");
		slimeButtons = new JButton[10];
		slimeButtons[0] = new JButton(new ImageIcon("resources/slimes/BombSlime.png"));
		slimeButtons[1] = new JButton(new ImageIcon("resources/slimes/BowAndArrowSlime.png"));
		slimeButtons[2] = new JButton(new ImageIcon("resources/slimes/CloneSlime.png"));
		slimeButtons[3] = new JButton(new ImageIcon("resources/slimes/CosmicSlime.png"));
		slimeButtons[4] = new JButton(new ImageIcon("resources/slimes/FireballSlime.png"));
		slimeButtons[5] = new JButton(new ImageIcon("resources/slimes/FisherSlime.png"));
		slimeButtons[6] = new JButton(new ImageIcon("resources/slimes/GeyserSlime.png"));
		slimeButtons[7] = new JButton(new ImageIcon("resources/slimes/MagnetSlime.png"));
		slimeButtons[8] = new JButton(new ImageIcon("resources/slimes/SuperSizeSlime.png"));
		slimeButtons[9] = new JButton(new ImageIcon("resources/slimes/SuperSlime.png"));
		p1SlimeImageLabel = new JLabel(slimeButtons[0].getIcon());
		p2SlimeImageLabel = new JLabel(slimeButtons[1].getIcon());
		String[] specialModeOptions = { "Anti-Gravity", "Giant Ball", "Multiple Balls", "Mini Slime" };
		String[] backgroundOptions = { "Desk", "Soccer Field", "Outer Space", "Laptop Screen" };
		specialModeCombo = new JComboBox<String>(specialModeOptions);
		backgroundCombo = new JComboBox<String>(backgroundOptions);
		regenRateSlider = new JSlider(JSlider.HORIZONTAL);
		totalManaSlider = new JSlider(JSlider.HORIZONTAL);
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
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.X_AXIS));
		JPanel jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
		JPanel c1 = new JPanel();
		JPanel c2 = new JPanel();
		JPanel c3 = new JPanel();
		JPanel c4 = new JPanel();
		JLabel p1UsernameLabel = new JLabel("<html><div style=\"text-align: center;\">" + p1Username);
		c1.add(p1UsernameLabel);
		c2.add(p1SlimeNameLabel);
		c3.add(p1SlimeImageLabel);
		c4.add(p1SlimeAbilityLabel);
		jp2.add(c1);
		jp2.add(c2);
		jp2.add(c3);
		jp2.add(c4);
		JPanel jp3 = new JPanel();
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
		JPanel c5 = new JPanel();
		JPanel c6 = new JPanel();
		JPanel c7 = new JPanel();
		JPanel c8 = new JPanel();
		JLabel p2UsernameLabel = new JLabel("<html><div style=\"text-align: center;\">" + p2Username);
		c5.add(p2UsernameLabel);
		c6.add(p2SlimeNameLabel);
		c7.add(p2SlimeImageLabel);
		c8.add(p2SlimeAbilityLabel);
		jp3.add(c5);
		jp3.add(c6);
		jp3.add(c7);
		jp3.add(c8);
		jp1.add(jp2);
		jp1.add(jp3);
		JPanel jp4 = new JPanel();
		jp4.setLayout(new GridLayout(2, 5));
		for (int i = 0; i < 10; i++) jp4.add(slimeButtons[i]);
		jp4.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
		centerPanel.add(jp1);
		centerPanel.add(jp4);		
		JLabel specialLabel = new JLabel("Special Mode");
		JLabel gameBackLabel = new JLabel("Game Background");
		JLabel manaRegenLabel = new JLabel("Mana Regeneration Rate (%)");
		JLabel totalManaLabel = new JLabel("Total Mana (%)");
		regenRateSlider.setMaximum(100);
		regenRateSlider.setMinimum(0);
		regenRateSlider.setPaintLabels(true);
		regenRateSlider.setPaintTicks(true);
		regenRateSlider.setMajorTickSpacing(50);
		regenRateSlider.setValue(50);
		totalManaSlider.setMaximum(100);
		totalManaSlider.setMinimum(0);
		totalManaSlider.setPaintLabels(true);
		totalManaSlider.setPaintTicks(true);
		totalManaSlider.setMajorTickSpacing(50);
		totalManaSlider.setValue(50);
		JPanel jp5 = new JPanel();
		jp5.add(specialLabel);
		jp5.add(specialModeCombo);
		jp5.add(gameBackLabel);
		jp5.add(backgroundCombo);
		JPanel jp6 = new JPanel();
		jp6.add(manaRegenLabel);
		jp6.add(regenRateSlider);
		jp6.add(totalManaLabel);
		jp6.add(totalManaSlider);
		JPanel c9 = new JPanel();
		c9.add(continueButton);
		centerPanel.add(jp5);
		centerPanel.add(jp6);
		centerPanel.add(c9);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		class SlimeButtonListener implements ActionListener {
			int index = -1;
			
			SlimeButtonListener(int index) {
				this.index = index;
			}
			
			public void actionPerformed(ActionEvent e) {
				if (isPlayer1) {
					p1SlimeImageLabel.setIcon(slimeButtons[index].getIcon());
				} else {
					p2SlimeImageLabel.setIcon(slimeButtons[index].getIcon());
				}
			}
			
		}
		for (int i = 0; i < 10; i++) {
			slimeButtons[i].addActionListener(new SlimeButtonListener(i));
		}
	}
	
	public static void main(String[] args) {
		new MainMenuUserPlaySlime(true);
	}
}
