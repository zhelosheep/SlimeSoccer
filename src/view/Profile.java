package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class Profile extends JFrame{
	private static final long serialVersionUID = 1L;
	private String u;
	private JFrame prevScreen;
	
	private JTextField searchField;
	private JButton searchButton, backButton, addFriend;
	
	Profile (String username, JFrame prev) {
		u = username;
		prevScreen = prev;
		
		setSize(800, 600);
		setLocation(300,100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		searchField = new JTextField(45);
		searchButton = new JButton("Search");
		backButton = new JButton("Back");
		addFriend = new JButton("Add Friend");
		
		if (MainMenuUser.username.equals(u) || LoginPage.sqli.findFriend(MainMenuUser.username, u)) {
			addFriend.setEnabled(false);
			addFriend.setVisible(false);
		}
	}
	
	private void addComponents() {
		JLabel name = new JLabel(LoginPage.sqli.getName(u));
		JLabel username = new JLabel(u);
		JLabel avatar = new JLabel(LoginPage.avatarImages[LoginPage.sqli.getImage(u)]);
		JLabel desc = new JLabel("Description: " + LoginPage.sqli.getDesc(u));
		int games = LoginPage.sqli.getGames(u);
		int wins = LoginPage.sqli.getWins(u);
		int loss = LoginPage.sqli.getLosses(u);
		ArrayList<model.Achievement> ach = LoginPage.sqli.getAchievements(u);
		ArrayList<String> friends = LoginPage.sqli.getFriends(u);
		
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
		desc.setAlignmentX(Component.LEFT_ALIGNMENT);
		addFriend.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel northPanel = new JPanel();
		JPanel header = new JPanel();
		JPanel searchBar = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel left = new JPanel();
		JPanel otherSide = new JPanel();
		JPanel bottomRight = new JPanel();
		JPanel middle = new JPanel();
		JPanel right = new JPanel();
		JPanel achievementPanel = new JPanel();
		JPanel friendsPanel = new JPanel();

		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		centerPanel.setLayout(new BorderLayout());
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		otherSide.setLayout(new BoxLayout(otherSide, BoxLayout.Y_AXIS));
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		achievementPanel.setLayout(new BoxLayout(achievementPanel, BoxLayout.X_AXIS));
		friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.X_AXIS));
		
		JScrollPane achieveScroll = new JScrollPane(achievementPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JScrollPane friendScroll = new JScrollPane(friendsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		achieveScroll.setMinimumSize(new Dimension(200, 120));
		achieveScroll.setPreferredSize(new Dimension(200, 120));
		friendScroll.setMinimumSize(new Dimension(200, 120));
		friendScroll.setPreferredSize(new Dimension(200, 120));
		
		otherSide.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		middle.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 20));
		right.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		
		header.add(slimeSoccerLabel);
		header.add(Box.createGlue());
		header.add(backButton);
		searchBar.add(new JLabel("Search for a user :"));
		searchBar.add(searchField);
		searchBar.add(searchButton);
		left.setBorder(BorderFactory.createEmptyBorder(80,  50,  0,  0));
		left.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.add(name);
		left.add(username);
		left.add(avatar);
		left.add(addFriend);
		left.add(Box.createGlue());
		otherSide.add(desc);
		
		JLabel stats = new JLabel("Statistics");
		JLabel wl = new JLabel("Win/Loss Ratio: " + wins + ":" + loss);
		JLabel gp = new JLabel("Games played: " + games);
		JLabel w = new JLabel("Wins: " + wins);
		JLabel l = new JLabel("Losses: " + loss);
		JLabel ac = new JLabel("Achievements");
		
		stats.setAlignmentX(Component.CENTER_ALIGNMENT);
		wl.setAlignmentX(Component.CENTER_ALIGNMENT);
		gp.setAlignmentX(Component.CENTER_ALIGNMENT);
		w.setAlignmentX(Component.CENTER_ALIGNMENT);
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		ac.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		middle.setAlignmentX(Component.CENTER_ALIGNMENT);
		middle.add(stats);
		middle.add(new JSeparator(SwingConstants.HORIZONTAL));
		middle.add(wl);
		middle.add(gp);
		middle.add(w);
		middle.add(l);
		middle.add(new JLabel(" "));
		middle.add(ac);
		middle.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		JLabel f = new JLabel("Friends");
		f.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		right.setAlignmentX(Component.CENTER_ALIGNMENT);
		right.add(f);
		right.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		for (int i = 0; i < friends.size(); i++) {
			JPanel group = new JPanel();
			group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
			group.setBorder(BorderFactory.createEmptyBorder(0,  5,  0,  5));
			group.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel friendAvatar = new JLabel(LoginPage.avatarImages[LoginPage.sqli.getImage(friends.get(i))]);
			friendAvatar.addMouseListener(new MouseAdapter() {
				int i;
				
				public void mouseClicked(MouseEvent me) {
					setVisible(false);
					Profile pro = new Profile(friends.get(i), Profile.this);
					pro.setVisible(true);
				}
				
				public MouseAdapter init (int i) {
					this.i = i;
					return this;
				}
			}.init(i));
			
			friendAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
			group.add(friendAvatar);
			
			JLabel temp = new JLabel(friends.get(i));
			temp.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			group.add(temp);
			
			friendsPanel.add(group);
		}
		
		right.add(friendScroll);
		
		for (int i = 0; i< ach.size(); i++) {
			JPanel group = new JPanel();
			group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
			group.setBorder(BorderFactory.createEmptyBorder(0,  5,  0,  5));
			group.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel icon = new JLabel(ach.get(i).getIcon());
			icon.addMouseListener(new MouseAdapter() {
				int i;
				
				public void mouseClicked(MouseEvent me) {
					JOptionPane.showMessageDialog(Profile.this, ach.get(i).getDescription(), ach.get(i).getName(), JOptionPane.INFORMATION_MESSAGE);
				}
				
				public MouseAdapter init(int i) {
					this.i = i;
					return this;
				}
			}.init(i));
			
			icon.setAlignmentX(Component.CENTER_ALIGNMENT);
			group.add(icon);
			
			JLabel temp = new JLabel(ach.get(i).getName());
			temp.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			group.add(temp);
			achievementPanel.add(group);
		}
		middle.add(achieveScroll);
		
		northPanel.add(header);
		northPanel.add(searchBar);
		bottomRight.add(middle);
		bottomRight.add(new JSeparator(SwingConstants.VERTICAL));
		bottomRight.add(right);
		otherSide.add(bottomRight);
		centerPanel.add(left, BorderLayout.WEST);
		centerPanel.add(new JSeparator(SwingConstants.VERTICAL));
		centerPanel.add(otherSide, BorderLayout.CENTER);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevScreen.setVisible(true);
				dispose();
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = searchField.getText();
				if (!LoginPage.sqli.findUser(u)) {
					JOptionPane.showMessageDialog(Profile.this, "Could not find user", "Error404", JOptionPane.ERROR_MESSAGE);
				} else {
					setVisible(false);
					Profile pro = new Profile(u, Profile.this);
					pro.setVisible(true);
				}
			}
		});
		
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = searchField.getText();
				if (!LoginPage.sqli.findUser(u)) {
					JOptionPane.showMessageDialog(Profile.this, "Could not find user", "Error404", JOptionPane.ERROR_MESSAGE);
				} else {
					setVisible(false);
					Profile pro = new Profile(u, Profile.this);
					pro.setVisible(true);
				}
			}
		});
		
		addFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check achievement
				// Social Butterfly – Have over 25 friends
				if (LoginPage.sqli.getFriends(MainMenuUser.username).size() == 25 && !LoginPage.sqli.checkAchievement(MainMenuUser.username, LoginPage.soc_a.getName())) {
					LoginPage.sqli.setAchievement(MainMenuUser.username, LoginPage.soc_a);
					JOptionPane.showMessageDialog(Profile.this, "You received the SOCIAL BUTTERFLY achievement!", "Achievement Earned!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
/*	public static void main (String [] args) {
		Profile p = new Profile("zhelo", null);
		p.setVisible(true);
	}*/
}
