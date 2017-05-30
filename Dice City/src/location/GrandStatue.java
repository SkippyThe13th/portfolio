/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A location class for specifically the Grand Statue location.
 */
public class GrandStatue extends Location {
	
	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 0;
	final static private int STONE = 1;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/GrandStatue.png";

	/**
	 * Constructor for a Grand Statue location.
	 */
	public GrandStatue() {
		this.setName("Grand Statue");
		this.setText("Pay up to 3 additional reasources of metal or stone when you build Grand Statue.  Get 1 vp for each resource paid.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("CULTURE");
		this.setPrice(WOOD,  STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for executing the card text of the location.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}
}
