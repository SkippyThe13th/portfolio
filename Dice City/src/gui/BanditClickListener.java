/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameControl.DiceCityGame;
import location.Location;
import pieces.BanditMarket;

/**
 * @author Owner Bengtson
 *
 */
public class BanditClickListener extends MouseAdapter {
	
	private DiceCityGame theGame;
	private GameWindow gameWindow;
	private Location banditType;
	private BanditMarket theMarket;
	private int banditHealth;
	
	/**
	 * Constructor
	 * @param aGame The DiceCityGame object that is controlling the game.
	 * @param theBanditLocation The bandit location that the component represents.
	 */
	public BanditClickListener(DiceCityGame aGame, GameWindow gameWindow, Location theBanditLocation){
		this.theGame = aGame;
		this.gameWindow = gameWindow;
		this.banditType = theBanditLocation;
		this.theMarket = theGame.getBanditMarket();
		this.banditHealth = banditType.getHealth();
	}
	
	/**
	 * If the component is clicked.
	 */
	public void mouseClicked(MouseEvent click){
		int playerSwords = this.theGame.getPlayerBoard(theGame.getPlayerNumber()).getSwords();
		
		if(playerSwords >= banditHealth){
			if(this.theMarket.removeBandit(banditType)){
				//System.out.println("Bandit removed successfully.");
				int swordCost = -banditHealth;
				this.theGame.getPlayerBoard(theGame.getPlayerNumber()).addAmountOfSwords(swordCost);
				this.theGame.getPlayerBoard(theGame.getPlayerNumber()).addAmountOfVP(banditType.getVP());
				//System.out.println("Points credited to player.");
			}else{
				System.out.println("No bandits left in the deck.");
			}
		}else{
			//do nothing
			//System.out.println("Not enough swords to purchase this bandit.  Swords: " + playerSwords + " Bandit Health: "
			//		+ banditHealth);
		}
		
		gameWindow.updateResourceCounters(theGame);
		
		if(theGame.getPlayerBoard(theGame.getPlayerNumber()).getSwords() <= 2){
			theGame.buildPhase();
		}
	}
	
}
