/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * Class for a Manor location.
 */
public class Manor extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 1;
	final static private int STONE = 2;
	final static private int METAL = 0;
	final static private String IMAGE_LOCATION = "images/Manor.png";
	
	/**
	 * Constructor for a Manor location.  Initializes all stats to predetermined values.
	 */
	public Manor() {
		this.setName("Manor");
		this.setText("Get 2 vp");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("CULTURE");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function for executing the location's card text.
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		int VPToAdd = 2;
		
		//System.out.println("A Manor was used.");
		theBoard.addAmountOfVP(VPToAdd);
		
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}
}
