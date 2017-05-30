/**
 * 
 */
package gui;

import javax.swing.JPopupMenu;

import gameControl.DiceCityGame;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * @author Owner Bengtson
 * The menu that appears when the dice labels are right clicked during
 * the use dice phase of a player's turn.
 */
public class DiePopUpMenu extends JPopupMenu {
	JMenuItem useLocation;
	JMenuItem moveAnotherDieLeft;
	JMenuItem moveAnotherDieRight;
	JMenuItem passDie;
	JMenuItem reactivateLocation;
	JMenuItem discardLocations;
	
	/**
	 * Constructor.  Displays the menu.
	 * @param game The DiceCityGame object being used to control the game.
	 * @param gameWindow The GameWindow object used to display the game
	 * @param die The die that has been right clicked
	 * @param row The row of the die that has been clicked.
	 */
	public DiePopUpMenu(DiceCityGame game, GameWindow gameWindow, Component die, int row){
		useLocation = new JMenuItem("Use location");
		moveAnotherDieLeft = new JMenuItem("Move another die one space left");
		moveAnotherDieRight = new JMenuItem("Move another die one space right");
		passDie = new JMenuItem("Pass for a Pass Token");
		reactivateLocation = new JMenuItem("Reactivate a location");
		discardLocations = new JMenuItem("Discard 4 locations");
		
		//If this option is clicked, the ability of the location indicated by the die will be used
		useLocation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent optionClicked){
				int adjustedDieNumber = game.getPlayerBoard(game.getPlayerNumber()).getDieFromRow(row).getValue() - 1;
				//Use the ability of the location indicated by the die.
				game.getPlayerBoard(game.getPlayerNumber()).getLocation(row, adjustedDieNumber).use(game.getPlayerBoard(game.getPlayerNumber()), die, row);
				
				gameWindow.updateResourceCounters(game);
				gameWindow.updateDiceDisplay(game);
				
				if(game.getPlayerBoard(game.getPlayerNumber()).checkIfDiceAreUsed() == true){
					game.attackPhase();
				}
			}
		});
		
		//If this option is clicked, the value of another die will be reduced by one, unless it's 1.
		moveAnotherDieLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		//If this option is clicked, the value of another die will be increased by one, unless it's 6.
		moveAnotherDieRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		//If this option is clicked, the die will be flagged as used, and the player will recieve a pass
		//  token.
		passDie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		//If this option is clicked, the player can select a deactivated location to reactivate.
		reactivateLocation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		//If this option is clicked, the player can choose 4 locations in the market to discard and replace.
		discardLocations.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		add(useLocation);
		add(moveAnotherDieLeft);
		add(moveAnotherDieRight);
		add(passDie);
		add(reactivateLocation);
		add(discardLocations);
	}
	
	
}
