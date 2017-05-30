/**
 * 
 */
package gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameControl.DiceCityGame;

/**
 * @author Owner Bengtson
 * Listens for the right click of the mouse.  Used to produce the pop-up menus when the dice lables
 * are right clicked.
 */
public class DieRightClickListener extends MouseAdapter {

	private DiceCityGame game;
	private GameWindow gameWindow;
	private int row;
	private boolean enabled;
	
	/**
	 * Constructor.
	 * @param game The DiceCityGame it is being used in.
	 * @param gameWindow The GameWindow displaying the label the listener is being attached to.
	 * @param row The row of the die the listener is beign attached to.
	 */
	public DieRightClickListener(DiceCityGame game, GameWindow gameWindow, int row){
		super();
		
		this.game = game;
		this.gameWindow = gameWindow;
		this.row = row;
		this.enabled = false;
	}
	
	/**
	 * Action to take when the mouse is pressed on the listener.
	 */
	public void mousePressed(MouseEvent mousePress){
		if(mousePress.isPopupTrigger()){
			display(mousePress);
		}
	}
	
	/**
	 * Action to take when the mouse is released over the listener.
	 */
	public void mouseReleased(MouseEvent mouseRelease){
		if(mouseRelease.isPopupTrigger()){
			display(mouseRelease);
		}
	}
	
	/**
	 * Displays the pop-up menu.
	 * @param aMouseEvent The event that triggered the listener.
	 */
	private void display(MouseEvent aMouseEvent){
		if(enabled){
			DiePopUpMenu menu = new DiePopUpMenu(game, gameWindow, aMouseEvent.getComponent(), row);	
			menu.show(aMouseEvent.getComponent(), aMouseEvent.getX(), aMouseEvent.getY());
		}
	}
	
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
