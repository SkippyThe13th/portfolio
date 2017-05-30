/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Mine Location
 */
public class Mine extends Location {

	final static private int VP = 0;
	final static private int HEALTH = 2;
	final static private int WOOD = 0;
	final static private int STONE = 0;
	final static private int METAL = 2;
	final static private String IMAGE_LOCATION = "images/Mine.png";

	/**
	 * Constructor for a Mine Location
	 */
	public Mine() {
		this.setName("Mine");
		this.setText("Get 2 metal.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("HARVEST");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for using the ability of the Location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		int metalToAdd = 2;
		
		System.out.println("A Mine was used.");
		theBoard.addAmountOfMetal(metalToAdd);
		
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
