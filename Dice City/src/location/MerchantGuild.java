/**
 * 
 */
package location;

import java.awt.Component;

import pieces.Board;

/**
 * @author Chase Bengtson
 * A Merchant Guild Location
 */
public class MerchantGuild extends Location {

	final static private int VP = 3;
	final static private int HEALTH = 5;
	final static private int WOOD = 2;
	final static private int STONE = 1;
	final static private int METAL = 1;
	final static private String IMAGE_LOCATION = "images/MerchantGuild.png";

	/**
	 * Constructor for a Merchant Guild
	 */
	public MerchantGuild() {
		this.setName("Merchant Guild");
		this.setText("DEACTIVATE: For each HARVEST location on this row get 1 resource of the type that each such location produces.");
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
