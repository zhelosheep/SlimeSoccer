package model;

import view.LoginPage;

public class Game {
	public Variables variables;
	
	public boolean slimeHasMoved_1 = false;
	public boolean slimeHasMoved_2 = false;
	
	public Game(String background, String player1_slimeType, String player2_slimeType, String player1_username, String player2_username, int player1_manaMax, int player2_manaMax, int manaRegenerationRate, String specialMode) {
		variables = new Variables();
		variables.setBackground(background);
		variables.setSlimeImage(1, player1_slimeType);
		variables.setSlimeImage(2, player2_slimeType);
		
		// set game stats
		variables.player1_username = player1_username;
		variables.player2_username = player2_username;
		variables.player1_manaCurrent = player1_manaMax;
		variables.player2_manaCurrent = player2_manaMax;
		variables.player1_manaMax = player1_manaMax;
		variables.player2_manaMax = player2_manaMax;
		variables.player1_score = 0;
		variables.player2_score = 0;
		variables.manaRegenerationRate = manaRegenerationRate;
		variables.specialMode = specialMode;
		variables.player1scored = false;
		variables.player2scored = false;
		
		// initialize objects
		if (player1_slimeType.equals("SlimeBomb")) {
			variables.slime1 = new SlimeBomb(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeBowAndArrow")) {
			variables.slime1 = new SlimeBowAndArrow(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeClone")) {
			variables.slime1 = new SlimeClone(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeCosmic")) {
			variables.slime1 = new SlimeCosmic(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeFireball")) {
			variables.slime1 = new SlimeFireball(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeFisher")) {
			variables.slime1 = new SlimeFisher(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeGeyser")) {
			variables.slime1 = new SlimeGeyser(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeMagnet")) {
			variables.slime1 = new SlimeMagnet(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeSuperSize")) {
			variables.slime1 = new SlimeSuperSize(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		} else if (player1_slimeType.equals("SlimeSuper")) {
			variables.slime1 = new SlimeSuper(variables.leftBoundary + 50, variables.groundLevel, 1, variables.imgSlime1, variables);
		}
		if (player2_slimeType.equals("SlimeBomb")) {
			variables.slime2 = new SlimeBomb(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeBowAndArrow")) {
			variables.slime2 = new SlimeBowAndArrow(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeClone")) {
			variables.slime2 = new SlimeClone(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeCosmic")) {
			variables.slime2 = new SlimeCosmic(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeFireball")) {
			variables.slime2 = new SlimeFireball(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeFisher")) {
			variables.slime2 = new SlimeFisher(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeGeyser")) {
			variables.slime2 = new SlimeGeyser(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeMagnet")) {
			variables.slime2 = new SlimeMagnet(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeSuperSize")) {
			variables.slime2 = new SlimeSuperSize(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		} else if (player2_slimeType.equals("SlimeSuper")) {
			variables.slime2 = new SlimeSuper(variables.rightBoundary - 50, variables.groundLevel, 2, variables.imgSlime2, variables);
		}
		variables.ball = new Ball((variables.leftBoundary + variables.rightBoundary)/2, variables.groundLevel - 12 - 100, variables);
		variables.goal1 = new Goal(variables.leftBoundary, variables);
		variables.goal2 = new Goal(variables.rightBoundary, variables);
	}
	
	public void update() {
		// update positions
		variables.slime1.update();
		variables.slime2.update();
		variables.ball.update();
		
		// check for ball collision with slimes
		Double [] slime1CollideNewVelocities = variables.slime1.detectCollision(variables.ball);
		Double [] slime2CollideNewVelocities = variables.slime2.detectCollision(variables.ball);
		if (slime1CollideNewVelocities[0] != null) {
			variables.ball.velocityX = slime1CollideNewVelocities[0].intValue();
			variables.ball.velocityY = slime1CollideNewVelocities[1].intValue();
			variables.ball.callMove();
		}
		if (slime2CollideNewVelocities[0] != null) {
			variables.ball.velocityX = slime2CollideNewVelocities[0].intValue();
			variables.ball.velocityY = slime2CollideNewVelocities[1].intValue();
			variables.ball.callMove();
		}
		// check for ball collision with top piece of goalpost
		Double [] goal1CollideNewVelocities = variables.goal1.detectCollision(variables.ball.x, variables.ball.y, variables.ball.velocityX, variables.ball.velocityY);
		Double [] goal2CollideNewVelocities = variables.goal2.detectCollision(variables.ball.x, variables.ball.y, variables.ball.velocityX, variables.ball.velocityY);
		if (goal1CollideNewVelocities != null) {
			variables.ball.velocityX = goal1CollideNewVelocities[0].intValue();
			variables.ball.velocityY = goal1CollideNewVelocities[1].intValue();
			variables.ball.callMove();
		}
		if (goal2CollideNewVelocities != null) {
			variables.ball.velocityX = goal2CollideNewVelocities[0].intValue();
			variables.ball.velocityY = goal2CollideNewVelocities[1].intValue();
			variables.ball.callMove();
		}
		
		// check for goals
		if (variables.goal1.checkGoal(variables.ball.x, variables.ball.y)) {
			goalScored(2);
		}
		if (variables.goal2.checkGoal(variables.ball.x, variables.ball.y)) {
			goalScored(1);
		}

	}
	
	private void goalScored(int playerThatScored) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException in goalScored() function: " + e.getMessage());
		}
		if (playerThatScored == 1) {
			variables.player1_score++;
			variables.player1scored = true;
			if (variables.player1_score == 5) {
				variables.gameOver = true;
				variables.playerThatWon = 1;
				saveStats(variables.playerThatWon);
			}
		}
		
		else if (playerThatScored == 2) {
			variables.player2_score++;
			variables.player2scored = true;
			if (variables.player2_score == 5) {
				variables.gameOver = true;
				variables.playerThatWon = 2;
				saveStats(variables.playerThatWon);
			}
		}
		
		if (playerThatScored == 1 || playerThatScored == 2) { // then reset ball position, slime positions, etc
			variables.ball.x = (variables.leftBoundary + variables.rightBoundary)/2;
			variables.ball.y = variables.groundLevel - 12 - 20;
			variables.ball.velocityX = 0;
			variables.ball.velocityY = 0;
			variables.slime1.x = variables.leftBoundary + 50;
			variables.slime1.y = variables.groundLevel;
			variables.slime1.velocityX = 0;
			variables.slime1.velocityY = 0;
			variables.slime2.x = variables.rightBoundary - 50;
			variables.slime2.y = variables.groundLevel;
			variables.slime2.velocityX = 0;
			variables.slime2.velocityY = 0;
		}
	}
	
	public void saveStats(int playerThatWon) {
		if (playerThatWon == 1) {
			LoginPage.sqli.updateStats(variables.player1_username, true);
			LoginPage.sqli.updateStats(variables.player2_username, false);
		} else {
			LoginPage.sqli.updateStats(variables.player1_username, false);
			LoginPage.sqli.updateStats(variables.player2_username, true);
		}
	}
}
