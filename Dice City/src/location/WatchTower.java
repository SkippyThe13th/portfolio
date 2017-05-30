/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Watch Tower Location
 */
public class WatchTower extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 1;
	final static private int STONE = 1;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/WatchTower.png";

	/**
	 * Constructor for a Watch Tower Location
	 */
	public WatchTower() {
		this.setName("Watch Tower");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setText("Add 3 STRENGTH to your army that can only be used to attack BANDITS.");
		this.setTag("MILITARY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function to use the ability of the Location.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
