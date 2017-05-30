/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Forest Location
 */
public class Forest extends Location {

	final static int VP = 0;
	final static int HEALTH = 0;
	final static int WOOD = 0;
	final static int STONE = 0;
	final static int METAL = 0;
	final static String IMAGE_LOCATION = "images/Forest.png";

	/**
	 * Constructor for a Forest Location
	 */
	public Forest() {
		this.setName("Forest");
		this.setText("Get 1 wood.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("BOARD");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function to use the ability of the Location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		int woodToAdd = 1;
		
		System.out.println("A Forest was used.");
		theBoard.addAmountOfWood(woodToAdd);
		
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
