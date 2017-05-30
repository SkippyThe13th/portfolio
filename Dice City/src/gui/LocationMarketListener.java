/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameControl.DiceCityGame;
import location.Location;
import location.LumberMill;
import location.Mine;
import location.Quarry;
import location.RegularArmy;

/**
 * @author Owner Bengtson
 * Listener for the labels used to display the locations in the location market.
 */
public class LocationMarketListener extends MouseAdapter {

	//Indexs of the resources in the resources array.
	private final static int WOOD = 0;
	private final static int STONE = 1;
	private final static int METAL = 2;
	
	private final static String LUMBER_MILL_NAME = new LumberMill().getName();
	private final static String QUARRY_NAME = new Quarry().getName();
	private final static String MINE_NAME = new Mine().getName();
	private final static String REGULAR_ARMY_NAME = new RegularArmy().getName();
	
	private DiceCityGame theGame;
	private GameWindow gameWindow;
	private Location locationType;
	private int[] locationPrice;
	private boolean enabled;
	
	/**
	 * Constructor.
	 * @param aGame The DiceCityGame it is being used in.
	 * @param gameWindow The GameWindow displaying the label the listener is being attached to.
	 * @param aLocation The location represented by the label the listener is being attached to.
	 */
	public LocationMarketListener(DiceCityGame aGame, GameWindow gameWindow, Location aLocation){
		this.theGame = aGame;
		this.gameWindow = gameWindow;
		this.locationType = aLocation;
		this.locationPrice = new int[3];
		this.locationPrice[WOOD] = aLocation.getPrice()[WOOD];
		this.locationPrice[STONE] = aLocation.getPrice()[STONE];
		this.locationPrice[METAL] = aLocation.getPrice()[METAL];
		this.enabled = false;
		
	}
	
	/**
	 * Actions to take when the mouse clicks on the label this listener is attached to.
	 */
	public void mouseClicked(MouseEvent click){
		int playerWood = theGame.getPlayerBoard(theGame.getPlayerNumber()).getWood();
		int playerStone = theGame.getPlayerBoard(theGame.getPlayerNumber()).getStone();
		int playerMetal = theGame.getPlayerBoard(theGame.getPlayerNumber()).getMetal();
		//System.out.println("The Player attempts to buy a new building.");
		if(enabled){
			if(playerWood >= this.locationPrice[WOOD] && playerStone >= this.locationPrice[STONE] && 
					playerMetal >= this.locationPrice[METAL])
			{
				if(locationType.getName() == LUMBER_MILL_NAME){
					theGame.addLocationToPlacementBuffer(locationType);
					theGame.getLocationMarket().subtractLumberMill();
					//System.out.println("Lumber Mill taken.");
				}else if(locationType.getName() == QUARRY_NAME){
					theGame.addLocationToPlacementBuffer(locationType);
					theGame.getLocationMarket().subtractQuarry();
					//System.out.println("Quarry taken.");
				}else if(locationType.getName() == MINE_NAME){
					theGame.addLocationToPlacementBuffer(locationType);
					theGame.getLocationMarket().subtractMine();
					//System.out.println("Mine taken.");
				}else if(locationType.getName() == REGULAR_ARMY_NAME){
					theGame.addLocationToPlacementBuffer(locationType);
					theGame.getLocationMarket().subtractRegularArmy();
					//System.out.println("Regular Army taken");
				}else{
					theGame.addLocationToPlacementBuffer(locationType);
					theGame.getLocationMarket().removeLocation(locationType);
					//System.out.println(locationType.getName() + " taken.");
				}
				
				theGame.getPlayerBoard(theGame.getPlayerNumber()).addAmountOfWood(-this.locationPrice[WOOD]);
				theGame.getPlayerBoard(theGame.getPlayerNumber()).addAmountOfStone(-this.locationPrice[STONE]);
				theGame.getPlayerBoard(theGame.getPlayerNumber()).addAmountOfMetal(-this.locationPrice[METAL]);
			}
		}//End of if(enabled)
		gameWindow.updateResourceCounters(theGame);
		gameWindow.updateBoardImages(theGame);
		gameWindow.updateShopImages(theGame);
	}//End of mouseClicked
	
	/**
	 * Enables the listener.
	 */
	public void enable(){
		this.enabled = true;
	}
	
	/**
	 * Disables the listener.
	 */
	public void disable(){
		this.enabled = false;
	}
}
