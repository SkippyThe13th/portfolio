/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Market Place Location
 */
public class MarketPlace extends Location {

	final static private int VP = 2;
	final static private int HEALTH = 4;
	final static private int WOOD = 2;
	final static private int STONE = 0;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/MarketPlace.png";

	/**
	 * Constructor for a Market Place
	 */
	public MarketPlace() {
		this.setName("Market Place");
		this.setText("Pay 1 or 2 of any 1 resource.  For reach 1 paid, get 2 of any other resource.");
		this.setVP(VP);
		this.setHealth(HEALTH);
		this.setTag("ECONOMY");
		this.setPrice(WOOD, STONE, METAL);
		this.setImage(IMAGE_LOCATION);
	}
	
	/**
	 * Function to use the ability of the Location
	 */
	public void use(Board theBoard, Component die, int rowOfLocation){
		theBoard.getDieFromRow(rowOfLocation).flagAsUsed();
		die.setVisible(false);
	}

}
