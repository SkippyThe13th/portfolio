/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A class defining a Festival Hall location.
 */
public class FestivalHall extends Location {

	final static private int VP = 1;
	final static private int HEALTH = 3;
	final static private int WOOD  = 2;
	final static private int STONE = 0;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/FestivalHall.png";

	/**
	 * Constructor for a Festival Hall location
	 */
	public FestivalHall() {
		this.setName("Festival Hall");
		this.setText("Get 1 vp for every two resources of the same type that you have in your stock.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("CULTURE");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for executing the card text of this location when it is used.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}
}
