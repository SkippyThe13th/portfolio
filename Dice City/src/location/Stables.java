/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Stables Location
 */
public class Stables extends Location {

	final static private int VP = 1;
	final static private int HEALTH = 3;
	final static private int WOOD = 2;
	final static private int STONE = 0;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/Stables.png";

	/**
	 * Constructor for a Stables Location
	 */
	public Stables() {
		this.setName("Stables");
		this.setText("Move one of your unused dice to any location on its row.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("UTILITY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**	
	 * Function to use the ability of this Location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
