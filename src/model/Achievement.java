package model;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Achievement implements Serializable{
	private static final long serialVersionUID = 1L;
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
