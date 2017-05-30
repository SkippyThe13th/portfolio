/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Training Camp location
 */
public class TrainingCamp extends Location {
	
	final static private int VP = 1;
	final static private int HEALTH = 3;
	final static private int WOOD = 1;
	final static private int STONE = 0;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/TrainingCamp.png";

	/**
	 * Constructor for a Training Camp location.
	 */
	public TrainingCamp() {
		this.setName("Training Camp");
		this.setText("All Bandits get -1 HEALTH (to a minimum of 1).");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("MILITARY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for using the ability of the location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
