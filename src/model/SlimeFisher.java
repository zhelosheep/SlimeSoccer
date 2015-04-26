package model;

import java.awt.image.BufferedImage;

public class SlimeFisher extends Slime {
	public SlimeFisher(int x, int y, int player, BufferedImage slimeImage, Game game) {
		super(x, y, player, slimeImage, game);
	}

	public void useSpecialPower() {
		slimeImage = game.imgFisherSpecial;
	}
	public void retractSpecialPower() {
		if (player == 1) {
			slimeImage = game.imgSlime1;
		} else if (player == 2) {
			slimeImage = game.imgSlime2;
		}
	}
}
