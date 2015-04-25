package view;

import javax.swing.ImageIcon;

public class Achievement {
	private String name;
	private String description;
	private ImageIcon icon; 
	
	public Achievement(String n, String d, ImageIcon i) {
		name = n;
		description = d;
		icon = i;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
}
