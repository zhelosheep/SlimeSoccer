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
	public String p1Username, p2Username, p1SlimeType, p2SlimeType;
	private String[] slimeNames, slimeAbilities, specialSlimeNames, specialSlimeDescriptions;	//for label purposes
	private String[] slimeTypes; //for passing to game instantiation
	private JButton[] slimeButtons;
	private JButton[] specialSlimes;
	public JLabel p1UsernameLabel, p2UsernameLabel;
	private JLabel p1SlimeNameLabel, p2SlimeNameLabel, p1SlimeImageLabel, p2SlimeImageLabel, p1SlimeAbilityLabel, p2SlimeAbilityLabel;
	private JComboBox<String> specialModeCombo, backgroundCombo;
	private JSlider regenRateSlider, totalManaSlider; 
	public boolean isPlayer1 = true, isPvCGame = true;
	private JFrame prevScreen;

	public MainMenuUserPlaySlime(JFrame prevScreen, boolean isPvCGame) {
		setSize(800, 600);
		setLocation(300,100);
		this.prevScreen = prevScreen;
		this.isPvCGame = isPvCGame;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		if (isPvCGame) {
			p1Username = "<html><div style=\"text-align: center;\">" + ((MainMenuUser)prevScreen).getUsername();
			p2Username = "<html><div style=\"text-align: center;\">Computer";
		} else {
			if (isPlayer1) {
				p1Username = "<html><div style=\"text-align: center;\">" + ((MainMenuUserPlayPlayer)prevScreen).getUsername();			
				p2Username = "<html><div style=\"text-align: center;\">derpyderp";
			} else {
				p1Username = "<html><div style=\"text-align: center;\">derpyderp";
				p2Username = "<html><div style=\"text-align: center;\">" + ((MainMenuUserPlayPlayer)prevScreen).getUsername();						
			}			
		}
		continueButton = new JButton("Continue");
		backButton = new JButton("Back");
		p1SlimeNameLabel = new JLabel("<html><div style=\"text-align: center;\">Bomber Slime");
		p2SlimeNameLabel = new JLabel("<html><div style=\"text-align: center;\">Bow And Arrow Slime");
		p1SlimeAbilityLabel = new JLabel("<html><div style=\"text-align: center;\">Explode");
		p2SlimeAbilityLabel = new JLabel("<html><div style=\"text-align: center;\">Shoot Arrows");
		p1UsernameLabel = new JLabel();
		p2UsernameLabel= new JLabel();		
		//Instantiate slimeButtons JButton array to hold image icons of each slime
		slimeButtons = new JButton[10];
		slimeButtons[0] = new JButton(new ImageIcon("resources/game/slimes/BombSlime.png"));
		slimeButtons[1] = new JButton(new ImageIcon("resources/game/slimes/BowAndArrowSlime.png"));
		slimeButtons[2] = new JButton(new ImageIcon("resources/game/slimes/CloneSlime.png"));
		slimeButtons[3] = new JButton(new ImageIcon("resources/game/slimes/CosmicSlime.png"));
		slimeButtons[4] = new JButton(new ImageIcon("resources/game/slimes/FireballSlime.png"));
		slimeButtons[5] = new JButton(new ImageIcon("resources/game/slimes/FisherSlime.png"));
		slimeButtons[6] = new JButton(new ImageIcon("resources/game/slimes/GeyserSlime.png"));
		slimeButtons[7] = new JButton(new ImageIcon("resources/game/slimes/MagnetSlime.png"));
		slimeButtons[8] = new JButton(new ImageIcon("resources/game/slimes/SuperSizeSlime.png"));
		slimeButtons[9] = new JButton(new ImageIcon("resources/game/slimes/SuperSlime.png"));
		
		//Instantiate slimeNames
		slimeNames = new String[10];
		slimeNames[0] = "<html><div style=\"text-align: center;\">Bomber Slime";
		slimeNames[1] = "<html><div style=\"text-align: center;\">Bow And Arrow Slime";
		slimeNames[2] = "<html><div style=\"text-align: center;\">Clone Slime";
		slimeNames[3] = "<html><div style=\"text-align: center;\">Cosmic Slime";
		slimeNames[4] = "<html><div style=\"text-align: center;\">Fireball Slime";
		slimeNames[5] = "<html><div style=\"text-align: center;\">Fisher Slime";
		slimeNames[6] = "<html><div style=\"text-align: center;\">Geyser Slime";
		slimeNames[7] = "<html><div style=\"text-align: center;\">Magnet Slime";
		slimeNames[8] = "<html><div style=\"text-align: center;\">Super Size Slime";
		slimeNames[9] = "<html><div style=\"text-align: center;\">Super Slime";
		
		//Instantiate slimeAbilities
		slimeAbilities = new String[10];
		slimeAbilities[0] = "<html><div style=\"text-align: center;\">Explode";
		slimeAbilities[1] = "<html><div style=\"text-align: center;\">Katniss?";
		slimeAbilities[2] = "<html><div style=\"text-align: center;\">Multiple Slimes";
		slimeAbilities[3] = "<html><div style=\"text-align: center;\">Wormhole";
		slimeAbilities[4] = "<html><div style=\"text-align: center;\">This Ball is on Fiyah";
		slimeAbilities[5] = "<html><div style=\"text-align: center;\">Gone Fishin'";
		slimeAbilities[6] = "<html><div style=\"text-align: center;\">HM03 Surf";
		slimeAbilities[7] = "<html><div style=\"text-align: center;\">Super Attractive ;)";
		slimeAbilities[8] = "<html><div style=\"text-align: center;\">Super-Size Me";
		slimeAbilities[9] = "<html><div style=\"text-align: center;\">Superslime, away!";
		
		// ~ *** ~~ SPECIAL SLIMES ~~~ *** ~
		// list of achievements: chris_a, loser_a, nolife_a, noob_a, pack_a, soc_a, unath_a, vict_a, wrongw_a
		// images
		specialSlimes = new JButton[8];
		specialSlimes[0] = new JButton(new ImageIcon("resources/game/specialslimes/3DSlime.png"));
		specialSlimes[1] = new JButton(new ImageIcon("resources/game/specialslimes/Butterfly.png"));
		specialSlimes[2] = new JButton(new ImageIcon("resources/game/specialslimes/CristianoRonaldoSlime.png"));
		specialSlimes[3] = new JButton(new ImageIcon("resources/game/specialslimes/SweaterSlime.png"));
		specialSlimes[4] = new JButton(new ImageIcon("resources/game/specialslimes/Crown.png"));
		specialSlimes[5] = new JButton(new ImageIcon("resources/game/specialslimes/Dunce.png"));
		specialSlimes[6] = new JButton(new ImageIcon("resources/game/specialslimes/LSlime.png"));
		specialSlimes[7] = new JButton(new ImageIcon("resources/game/specialslimes/Potato.png"));
		
		for (int i = 0; i < specialSlimes.length; i++) {
			specialSlimes[i].setEnabled(false);
		}
		
		// names
		specialSlimeNames = new String[8];
		specialSlimeNames[0] = "<html><div style=\"text-align: center;\">3D Slime";
		specialSlimeNames[1] = "<html><div style=\"text-align: center;\">Butterfly Slime";
		specialSlimeNames[2] = "<html><div style=\"text-align: center;\">Cristiano Ronaldo Slime";
		specialSlimeNames[3] = "<html><div style=\"text-align: center;\">Sweater Slime";
		specialSlimeNames[4] = "<html><div style=\"text-align: center;\">Crown Slime";
		specialSlimeNames[5] = "<html><div style=\"text-align: center;\">Dunce Slime'";
		specialSlimeNames[6] = "<html><div style=\"text-align: center;\">LSlime";
		specialSlimeNames[7] = "<html><div style=\"text-align: center;\">Potato Slime";
		
		// slimeTypes for passing to SHT and using in game instantiation
		slimeTypes = new String[19];
		slimeTypes[0] = "SlimeBomb";
		slimeTypes[1] = "SlimeBowAndArrow";
		slimeTypes[2] = "SlimeClone";
		slimeTypes[3] = "SlimeCosmic";
		slimeTypes[4] = "SlimeFireBall";
		slimeTypes[5] = "SlimeFisher";
		slimeTypes[6] = "SlimeGeyser";
		slimeTypes[7] = "SlimeMagnet";
		slimeTypes[8] = "SlimeSuperSize";
		slimeTypes[9] = "SlimeSuper";
		slimeTypes[10] = "Slime3D";
		slimeTypes[11] = "SlimeButterfly";
		slimeTypes[12] = "SlimeRonaldo";
		slimeTypes[13] = "SlimeCrossEyed";
		slimeTypes[14] = "SlimeCrown";
		slimeTypes[15] = "SlimeDunce";
		slimeTypes[16] = "LSlime";
		slimeTypes[17] = "SlimePotato";
		slimeTypes[18] = "SlimeSweater";
		
		// descriptions
		specialSlimeDescriptions = new String[8];
		specialSlimeDescriptions[0] = "<html><div style=\"text-align: center;\">Packing on the Pounds";
		specialSlimeDescriptions[1] = "<html><div style=\"text-align: center;\">Social Butterfly";
		specialSlimeDescriptions[2] = "<html><div style=\"text-align: center;\">Cristiano Ronaldo";
		specialSlimeDescriptions[3] = "<html><div style=\"text-align: center;\">Unathletic Athlete";
		specialSlimeDescriptions[4] = "<html><div style=\"text-align: center;\">Victorious";
		specialSlimeDescriptions[5] = "<html><div style=\"text-align: center;\">N00b'";
		specialSlimeDescriptions[6] = "<html><div style=\"text-align: center;\">Loser";
		specialSlimeDescriptions[7] = "<html><div style=\"text-align: center;\">No Life Award";
		
		p1SlimeImageLabel = new JLabel(slimeButtons[0].getIcon());
		p2SlimeImageLabel = new JLabel(slimeButtons[1].getIcon());
		
		String[] specialModeOptions = { "Anti-Gravity", "Giant Ball", "Mini Slime" };
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
		for (int i = 0; i < slimeButtons.length; i++) jp4.add(slimeButtons[i]);
		for (int i = 0; i < specialSlimes.length; i++) jp4.add(specialSlimes[i]);
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
		
		class SlimeButtonListener implements ActionListener {
			int index = -1;
			
			SlimeButtonListener(int index) {
				this.index = index;
			}
			
			public void actionPerformed(ActionEvent e) {
				if (isPlayer1) {
					p1SlimeImageLabel.setIcon(slimeButtons[index].getIcon());
					p1SlimeNameLabel.setText(slimeNames[index]);
					p1SlimeAbilityLabel.setText(slimeAbilities[index]);
					p1SlimeType = slimeTypes[index];
				} else {
					p2SlimeImageLabel.setIcon(slimeButtons[index].getIcon());
					p2SlimeNameLabel.setText(slimeNames[index]);
					p2SlimeAbilityLabel.setText(slimeAbilities[index]);
					p2SlimeType = slimeTypes[index];
				}
			}	
		}
		
		class SpecialSlimeListener implements ActionListener {
			int index = -1;
			
			SpecialSlimeListener(int index) {
				this.index = index;
			}
			
			public void actionPerformed(ActionEvent e) {
				if (isPlayer1) {
					p1SlimeImageLabel.setIcon(specialSlimes[index].getIcon());
					p1SlimeNameLabel.setText(specialSlimeNames[index]);
					p1SlimeAbilityLabel.setText(specialSlimeDescriptions[index]);
					p1SlimeType = slimeTypes[10 + index];
				} else {
					p2SlimeImageLabel.setIcon(specialSlimes[index].getIcon());
					p2SlimeNameLabel.setText(specialSlimeNames[index]);
					p2SlimeAbilityLabel.setText(specialSlimeDescriptions[index]);
					p2SlimeType = slimeTypes[10 + index];
				}
			}
		}
		
		for (int i = 0; i < slimeButtons.length; i++) {
			slimeButtons[i].addActionListener(new SlimeButtonListener(i));
		}
		
		for (int i = 0; i < specialSlimes.length; i++) {
			specialSlimes[i].addActionListener(new SpecialSlimeListener(i));
		}
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevScreen.setVisible(true);
				//no need to add code that updates chat. Chat should not be retroactive since login
			}
		});
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPvCGame) {
					if (isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen).prevScreen.sWriter.println("O1" + "$" + p1Username + "$" + p2Username + "$" + specialModeCombo.getSelectedItem().toString() + "$" + backgroundCombo.getSelectedItem().toString() + "$" + regenRateSlider.getValue() + "$" + totalManaSlider.getValue()); // append game settings!						
						//System.out.println("O1" + "$" + specialModeCombo.getSelectedItem().toString() + "$" + backgroundCombo.getSelectedItem().toString() + "$" + regenRateSlider.getValue() + "$" + totalManaSlider.getValue());
						((MainMenuUserPlayPlayer) prevScreen).prevScreen.sWriter.flush();						
					} else {
						((MainMenuUserPlayPlayer) prevScreen).prevScreen.sWriter.println("O2" + "$" + specialModeCombo.getSelectedItem().toString() + "$" + backgroundCombo.getSelectedItem().toString() + "$" + regenRateSlider.getValue() + "$" + totalManaSlider.getValue()); // append game settings!						
						//System.out.println("O2" + "$" + specialModeCombo.getSelectedItem().toString() + "$" + backgroundCombo.getSelectedItem().toString() + "$" + regenRateSlider.getValue() + "$" + totalManaSlider.getValue());												
						((MainMenuUserPlayPlayer) prevScreen).prevScreen.sWriter.flush();
					}
				}
			}
		});
		//addd action listener on continue button; this should bring us to a settingspage
	}
	
	void quit() {
		if (isPvCGame) {
			((MainMenuUser) prevScreen).quit();
		} else {
			((MainMenuUserPlayPlayer) prevScreen).quit();
		}
	}
}
