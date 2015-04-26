package model;

import java.io.Serializable;

import javax.swing.ImageIcon;

// all achievement names: chris_a, loser_a, nolife_a, noob_a, pack_a, soc_a, unath_a, vict_a

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
