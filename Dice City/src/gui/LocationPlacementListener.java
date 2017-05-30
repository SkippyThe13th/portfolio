/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import gameControl.DiceCityGame;
import location.Location;

/**
 * @author Owner Bengtson
 * A Mouse listenr that listens for a label to be clicked, used for placing new locations on the player board.
 */
public class LocationPlacementListener extends MouseAdapter {

	private final static int FIRST_LOCATION = 0;
	
	private DiceCityGame theGame;
	private GameWindow gameWindow;
	private int x;
	private int y;
	
	/**
	 * Constructor.
	 * @param aGame The DiceCityGame it is being used in.
	 * @param gameWindow The GameWindow displaying the label the listener is being attached to.
	 * @param x The x coordinate of the label in the grid of the board.
	 * @param y The y coordinate of the label in the grid of the board.
	 */
	public LocationPlacementListener(DiceCityGame aGame, GameWindow gameWindow, int x, int y){
		this.theGame = aGame;
		this.gameWindow = gameWindow;
		this.x = x;
		this.y = y;
		//System.out.println("SU: New LocationPlacementListener created for location at " + this.x + ", " + this.y);
	}
	
	/**
	 * Actions to take when the mouse is clicked on the attached object.
	 */
	public void mouseClicked(MouseEvent click){
		Location[][] city = theGame.getPlayerBoard(theGame.getPlayerNumber()).getCity().clone();
		
		if(theGame.getLocationPlacementBuffer().size() >= 1){
			city[this.x][this.y] = theGame.getLocationPlacementBuffer().get(FIRST_LOCATION);
			theGame.getLocationPlacementBuffer().remove(FIRST_LOCATION);
			//System.out.println("Location placed at " + this.x + ", " + this.y + ".");
		}
		
		gameWindow.updateResourceCounters(theGame);
		gameWindow.updateBoardImages(theGame);
		gameWindow.updateShopImages(theGame);
		
	}
	
}
