package model;

import java.awt.image.BufferedImage;

public class SlimeFisher extends Slime {
	public SlimeFisher(int x, int y, int player, BufferedImage slimeImage, Variables variables) {
		super(x, y, player, slimeImage, variables);
	}

	public void useSpecialPower() {
		slimeImage = variables.imgFisherSpecial;
	}
	public void retractSpecialPower() {
		if (player == 1) {
			slimeImage = variables.imgSlime1;
		} else if (player == 2) {
			slimeImage = variables.imgSlime2;
		}
	}
}
