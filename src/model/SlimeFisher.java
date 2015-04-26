package model;

import java.awt.image.BufferedImage;

public class SlimeFisher extends Slime {
	public SlimeFisher(int x, int y, int player, BufferedImage slimeImage) {
		super(x, y, player, slimeImage);
	}

	public void useSpecialPower() {
		slimeImage = Game.imgFisherSpecial;
	}
	public void retractSpecialPower() {
		if (player == 1) {
			slimeImage = Game.imgSlime1;
		} else if (player == 2) {
			slimeImage = Game.imgSlime2;
		}
	}
}
