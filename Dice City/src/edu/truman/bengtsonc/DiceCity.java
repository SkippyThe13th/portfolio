/**
 * 
 */
package edu.truman.bengtsonc;

import gameControl.*;
import gui.*;

/**
 * @author Chase Bengtson
 * Begins play of a Dice City Client, which is the actual game, as opposed to the server.
 */
public class DiceCity {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		GameWindow window = new GameWindow();
		DiceCityGame aGame = new DiceCityGame(window);
		
		aGame.setUpNewGame();
		aGame.playGame();
	}

}

