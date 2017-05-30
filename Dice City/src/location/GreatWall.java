/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Location class specifically for the Great Wall location.
 */
public class GreatWall extends Location {

	final static private int VP = 3;
	final static private int HEALTH = 5;
	final static private int WOOD = 0;
	final static private int STONE = 2;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/GreatWall.png";

	/**
	 * Constructor for declaring a new Great Wall location.
	 */
	public GreatWall() {
		this.setName("Great Wall");
		this.setText("ONGOING: Other locations on this row have +2 health.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("CULTURE");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * A function to execute the card text of the location.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		//Insert card text
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
