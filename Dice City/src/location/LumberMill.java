/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Lumber Mill Location
 */
public class LumberMill extends Location {

	final static private int VP = 0;
	final static private int HEALTH = 2;
	final static private int WOOD = 2;
	final static private int STONE = 0;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/LumberMill.png";

	/**
	 * Constructor for a Lumber Mill Location
	 */
	public LumberMill() {
		this.setName("Lumber Mill");
		this.setText("Get 2 wood.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("HARVETS");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for using the ability of the Location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		int woodToAdd = 2;
		
		//System.out.println("A Lumber Mill was used.");
		theBoard.addAmountOfWood(woodToAdd);
		
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
