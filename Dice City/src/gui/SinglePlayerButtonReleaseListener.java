/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameControl.DiceCityGame;

/**
 * @author Owner Bengtson
 * Depricated.  Not used.
 */
public class SinglePlayerButtonReleaseListener extends MouseAdapter {
	private DiceCityGame game;
	
	public SinglePlayerButtonReleaseListener(DiceCityGame game){
		super();
		
		this.game = game;
	}
	
	public void mouseReleased(MouseEvent mouseRelease){
		game.setUpNewGame();
		game.playGame();
	}
}
