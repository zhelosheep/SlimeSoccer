package game;

import javax.swing.ImageIcon;

public class Slime {
	String slimeName, abilityName;
	ImageIcon slimeLooks;
	
	public Slime() {
		slimeName = "NO SLIME";
		abilityName = "NO ABILITY";
		slimeLooks = null;
	}
	public Slime(String slimeName) {
		this.slimeName = slimeName;
		if (slimeName.equals("Bomb Slime")) {
			abilityName = "Explode";
			slimeLooks = new ImageIcon("BombSlime.png");
		} else if (slimeName.equals("Bow And Arrow Slime")) {
			abilityName = "Shoots Arrows";
			slimeLooks = new ImageIcon("BowAndArrowSlime.png");
		} else if (slimeName.equals("Clone Slime")) {
			abilityName = "Clone";
			slimeLooks = new ImageIcon("CloneSlime.png");
		} // finish the rest
	}
	
	public String getSlimeName() {
		return slimeName;
	}
	
	public String getAbilityName() {
		return abilityName;
	}
	
	public ImageIcon getSlimeLooks() {
		return slimeLooks;
	}
}
