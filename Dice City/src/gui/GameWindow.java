/**
 * 
 */
package gui;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import gameControl.*;
import location.BanditsLarge;
import location.BanditsMed;
import location.BanditsSmall;
import location.Location;
import location.LumberMill;
import location.Mine;
import location.Quarry;
import location.RegularArmy;
import pieces.D6;
import pieces.LocationMarket;
import gui.DieRightClickListener;

/**
 * @author Chase Bengtson
 * This class is responsible for creating everything that is displayed to the user, as well as managing the
 * interactive features of the game.
 */
public class GameWindow {
	
	private DiceCityGame soloGame;
	final static int RESOURCE_FONT_SIZE = 32;
	final static int FIRST_IN_LIST = 0;
	
	private JFrame gameWindow;
	private boolean mainMenuDisplayed;
	private boolean gameViewDisplayed;
	
	private JPanel boardPanel;
	private BoxLayout boardLayout;

	private Location[][] boardLocations;
	//Create array to store images for dice faces.
	private ImageIcon[][] diceFaces;
	private final static int WHITE_ROW = 0;
	private final static int YELLOW_ROW = 1;
	private final static int RED_ROW = 2;
	private final static int BLUE_ROW = 3;
	private final static int BLACK_ROW = 4;
	//Create Panels to hold each row of labels containing the locations in a given row.
	private JPanel whiteRowLocations;
	private JPanel yellowRowLocations;
	private JPanel redRowLocations;
	private JPanel blueRowLocations;
	private JPanel blackRowLocations;
	private JLabel[][] boardLocationLabels;
	//Create Labels to display dice faces
	private JLabel whiteDie;
	private JLabel yellowDie;
	private JLabel redDie;
	private JLabel blueDie;
	private JLabel blackDie;
	//Create Labels to display resource counts.
	private JLabel woodCounter;
	private JLabel stoneCounter;
	private JLabel metalCounter;
	private JLabel swordCounter;
	private JLabel vpCounter;
	//Declare Labels to display bandit cards.
	private JLabel smallBanditLabel;
	private JLabel mediumBanditLabel;
	private JLabel largeBanditLabel;
	//Declare Labels to display trade ship cards.
	private JLabel smallTradeShipLabel;
	private JLabel medTradeShipLabel;
	private JLabel largeTradeShipLabel;
	//Declare Labels to display basic location market
	private JLabel lumberMillDeck;
	private JLabel quarryDeck;
	private JLabel mineDeck;
	private JLabel armyDeck;
	//Declare labels to display location market
	private JLabel[] firstMarketRow;
	private JLabel[] secondMarketRow;
	
	/**
	 * Generic constructor
	 */
 	public GameWindow() {
		this.soloGame = new DiceCityGame(this);
		this.gameWindow = new JFrame();
		this.boardPanel = new JPanel();
		this.boardLayout  = new BoxLayout(boardPanel, BoxLayout.Y_AXIS);
		this.boardLocations = new Location[5][6];
		this.boardLocationLabels = new JLabel[5][6];
		this.diceFaces = new ImageIcon[5][6];
		this.whiteRowLocations = new JPanel();
		this.yellowRowLocations = new JPanel();
		this.redRowLocations = new JPanel();
		this.blueRowLocations = new JPanel();
		this.blackRowLocations = new JPanel();
		this.whiteDie = new JLabel();
		this.yellowDie = new JLabel();
		this.redDie = new JLabel();
		this.blueDie = new JLabel();
		this.blackDie = new JLabel();
	}
	
	/**
	 * Method for loading the main menu of the game.  It presents the options to play a singleplayer game, 
	 * read instructions, or to play a multiplayer game.
	 * This method is left over from my initial design, but currently is not used in the most recent build.
	 */
	public void loadMainMenu(){
		//Create Frame (window)
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximizes window size to cover whole screen.
		//gameWindow.setUndecorated(true);
		
		//Create main menu buttons. (Singleplayer, Help, Multiplayer)
		String singlePlayerButtonName = "Single Player";
		String multiplayerButtonName = "Multiplayer";
		String helpButtonName = "Help";
		//Singleplayer button creation
		JButton singlePlayerButton = new JButton(singlePlayerButtonName); 
		singlePlayerButton.setName(singlePlayerButtonName); 
		singlePlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		singlePlayerButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		//Help button creation
		JButton helpButton = new JButton(helpButtonName); 
		helpButton.setName(helpButtonName);
		helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		helpButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		//Multiplayer button creation
		JButton multiplayerButton = new JButton(multiplayerButtonName); 
		multiplayerButton.setName(multiplayerButtonName);
		multiplayerButton.setText(multiplayerButtonName);
		multiplayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		multiplayerButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		//Create Action Listeners for each button
		//When Singleplayer button is pressed
		singlePlayerButton.addMouseListener(new SinglePlayerButtonReleaseListener(soloGame));
		//When Multiplayer button is pressed
		multiplayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//
				//Code to load multiplayer connection screen & hide the main menu
				//
			}
		});
		//When Help button is pressed
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				//Code to load page detailing how to play the game.
				//
			}
		});
		
		//Create and set Layout and Panel of main menu
		JPanel mainMenu = new JPanel();
		BoxLayout mainMenuLayout = new BoxLayout(mainMenu, BoxLayout.Y_AXIS);
		mainMenu.setLayout(mainMenuLayout);
		
		//Add Buttons to main menu Panel
		mainMenu.add(Box.createVerticalGlue());
		mainMenu.add(singlePlayerButton);
		mainMenu.add(multiplayerButton);
		mainMenu.add(helpButton);
		mainMenu.add(Box.createVerticalGlue());
		
		//Add main menu Panel to window.
		gameWindow.getContentPane().add(mainMenu);
		
		//Make window visible
		gameWindow.setVisible(true);
		
		mainMenuDisplayed = true;
		gameViewDisplayed = false;
	}
	
	/**
	 * Method for displaying the game board screen.  Will display the player's board, the location deck, 
	 * victory card piles, and other controls.
	 */
	public void loadGameScreen(DiceCityGame game){
		//Create Frame (window) **Added during attempt to skip main menu.
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); //Maximizes window size to cover whole screen.
		
		//Remove previous GUI
		gameWindow.getContentPane().setVisible(false);
		gameWindow.getContentPane().removeAll();
		
		//Set Layout and add new game screen GUI
		gameWindow.getContentPane().setLayout(new BoxLayout(gameWindow.getContentPane(), BoxLayout.X_AXIS));
		gameWindow.getContentPane().add(createScorePanel(game));
		gameWindow.getContentPane().add(createBoardPanel(game));
		gameWindow.getContentPane().add(createShopPanel(game));
		
		//Load new GUI
		gameWindow.getContentPane().revalidate();
		gameWindow.getContentPane().repaint();
		gameWindow.getContentPane().setVisible(true);
		
		//Make window visible **Added during attempt to skip main menu**
		gameWindow.setVisible(true);
		
		gameViewDisplayed = true;
		mainMenuDisplayed = false;
	}
	
	/**
	 * Creates the panel containing the player board and returns the created JPanel.
	 * @param game The DiceCityGame instance being used to create the game overall.
	 * @return boardPanel  The JPanel created that contains the player board display.
	 */
	private JPanel createBoardPanel(DiceCityGame game){
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//CREATION OF PLAYER BOARD
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		boardPanel.setLayout(boardLayout);
		//Obtain needed location objects for the starting board locations and store them.
		for(int row=0; row<5;row++){
			for(int column=0; column<6; column++){
				boardLocations[row][column] = (game.getPlayerBoard(game.getPlayerNumber()).getCity()[row][column]);
			}
		}
		
		//Turn the list of locations acquired above into a list of labels.
		int boardRow = 0;
		int boardColumn = 0;
		for(Location[] locationList : boardLocations){
			for(Location location : locationList){
				ImageIcon newIcon = new ImageIcon(location.getImage());
				this.boardLocationLabels[boardRow][boardColumn] = new JLabel(newIcon);
				boardColumn++;
			}
			boardRow++;
			boardColumn = 0;
		}
		
		//Popluate list of die face images.
		for(int row=0; row<5;row++){
			for(int column=0; column<6; column++){
				diceFaces[row][column] = new ImageIcon(D6.getFaceImage(row, column));
			}
		}
		
		//Attatch listeners to the dice labels.
		whiteDie.addMouseListener(new DieRightClickListener(game, this, WHITE_ROW));
		yellowDie.addMouseListener(new DieRightClickListener(game, this, YELLOW_ROW));
		redDie.addMouseListener(new DieRightClickListener(game, this, RED_ROW));
		blueDie.addMouseListener(new DieRightClickListener(game, this, BLUE_ROW));
		blackDie.addMouseListener(new DieRightClickListener(game, this, BLACK_ROW));
		
		//Add dice labels to row panels.
		whiteRowLocations.add(whiteDie);
		yellowRowLocations.add(yellowDie);
		redRowLocations.add(redDie);
		blueRowLocations.add(blueDie);
		blackRowLocations.add(blackDie);
		
		//Add the lables containing location icons to the row panels.  Adds one location to each row then
		//loops until all rows have 6 items.
		/*
		for(int column=0; column<6; column++){
			row1Locations.add(new JLabel(boardLocationImages[0][column]));
			row2Locations.add(new JLabel(boardLocationImages[1][column]));
			row3Locations.add(new JLabel(boardLocationImages[2][column]));
			row4Locations.add(new JLabel(boardLocationImages[3][column]));
			row5Locations.add(new JLabel(boardLocationImages[4][column]));
		}*/
		int column = 0;
		for(JLabel locationLabel : this.boardLocationLabels[WHITE_ROW]){
			locationLabel.addMouseListener(new LocationPlacementListener(game, this, WHITE_ROW, column));
			this.whiteRowLocations.add(locationLabel);
			column++;
		}
		column = 0;
		for(JLabel locationLabel : this.boardLocationLabels[YELLOW_ROW]){
			locationLabel.addMouseListener(new LocationPlacementListener(game, this, YELLOW_ROW, column));
			this.yellowRowLocations.add(locationLabel);
			column++;
		}
		column = 0;
		for(JLabel locationLabel : this.boardLocationLabels[RED_ROW]){
			locationLabel.addMouseListener(new LocationPlacementListener(game, this, RED_ROW, column));
			this.redRowLocations.add(locationLabel);
			column++;
		}
		column = 0;
		for(JLabel locationLabel : this.boardLocationLabels[BLUE_ROW]){
			locationLabel.addMouseListener(new LocationPlacementListener(game, this, BLUE_ROW, column));
			this.blueRowLocations.add(locationLabel);
			column++;
		}
		column = 0;
		for(JLabel locationLabel : this.boardLocationLabels[BLACK_ROW]){
			locationLabel.addMouseListener(new LocationPlacementListener(game, this, BLACK_ROW, column));
			this.blackRowLocations.add(locationLabel);
			column++;
		}
		
		//Add rows to the board panel
		boardPanel.add(whiteRowLocations);
		boardPanel.add(yellowRowLocations);
		boardPanel.add(redRowLocations);
		boardPanel.add(blueRowLocations);
		boardPanel.add(blackRowLocations);
		
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		return boardPanel;
	}
	
	/**
	 * Creates and adds components to the panel that serves as the scorePanel for displaying the game.
	 * @param game The DiceCityGame instance being used to create the game overall.
	 * @return scorePanel The JPanel created that contains the player board display.
	 */
	private JPanel createScorePanel(DiceCityGame game){
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//CREATION OF VICTORY POINT DECKS AND UTILITY BUTTONS
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		//Create score panel and add components to it.
		JPanel scorePanel = new JPanel();
		BoxLayout scorePanelLayout = new BoxLayout(scorePanel, BoxLayout.Y_AXIS);
		scorePanel.setLayout(scorePanelLayout);
		
		JPanel tradeShipPanel = new JPanel();
		tradeShipPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ImageIcon smallTradeShipIcon = new ImageIcon(game.getTradeShipMarket().getSmallTradeShipLocation().getImage());
		ImageIcon medTradeShipIcon = new ImageIcon(game.getTradeShipMarket().getMedTradeShipLocation().getImage());
		ImageIcon largeTradeShipIcon = new ImageIcon(game.getTradeShipMarket().getLargeTradeShipLcoation().getImage());
		this.smallTradeShipLabel = new JLabel(smallTradeShipIcon);
		this.medTradeShipLabel = new JLabel(medTradeShipIcon);
		this.largeTradeShipLabel = new JLabel(largeTradeShipIcon);
		tradeShipPanel.add(smallTradeShipLabel);
		tradeShipPanel.add(medTradeShipLabel);
		tradeShipPanel.add(largeTradeShipLabel);
		
		
		JPanel banditsPanel = new JPanel();
		banditsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ImageIcon smallBanditIcon = new ImageIcon(game.getBanditMarket().getSmallBanditLocation().getImage());
		ImageIcon mediumBanditIcon = new ImageIcon(game.getBanditMarket().getMediumBanditLocation().getImage());
		ImageIcon largeBanditIcon = new ImageIcon(game.getBanditMarket().getLargeBanditLocation().getImage());
		this.smallBanditLabel = new JLabel(smallBanditIcon);
		this.mediumBanditLabel = new JLabel(mediumBanditIcon);
		this.largeBanditLabel = new JLabel(largeBanditIcon);
		banditsPanel.add(smallBanditLabel);
		banditsPanel.add(mediumBanditLabel);
		banditsPanel.add(largeBanditLabel);
		
		String vpAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getVP());
		vpCounter = new JLabel("Victory Points: " + vpAmount);
		vpCounter.setFont(new Font(vpCounter.getFont().getFontName(), Font.PLAIN, RESOURCE_FONT_SIZE));
		
		JButton viewOpponentBoards = new JButton("View Opponents' Boards");
		JButton helpButton = new JButton("Help");
		JButton doneBuilding = new JButton("Done Building");
		doneBuilding.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				game.endPhase();
			}
		});
		scorePanel.add(tradeShipPanel);
		scorePanel.add(banditsPanel);
		scorePanel.add(vpCounter);
		scorePanel.add(viewOpponentBoards);
		scorePanel.add(helpButton);
		scorePanel.add(doneBuilding);
		
		return scorePanel;
	}

	/**
	 * Creates and returns the Panel containing the graphics for the 8 buyable locations in the location 
	 * market.
	 * @param game The DiceCityGame instance being used to create the game overall.
	 * @return locationMarketPanel the created Panel containing the necissary labels.
	 */
	private JPanel createLocationMarketPanel(DiceCityGame game){
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// CREATION OF LOCATION SHOP
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		JPanel locationMarketPanel = new JPanel();
		JPanel firstRowPanel = new JPanel();
		JPanel secondRowPanel = new JPanel();
		BoxLayout locationMarketLayout = new BoxLayout(locationMarketPanel, BoxLayout.Y_AXIS);
		locationMarketPanel.setLayout(locationMarketLayout);
		locationMarketPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//Create list of location labels.
		this.firstMarketRow = new JLabel[4];
		this.secondMarketRow = new JLabel[4];

		//Create and assign Icons to the labels.
		for(int n = 0; n < 4; n++){
			this.firstMarketRow[n] = new JLabel();
			this.secondMarketRow[n] = new JLabel();
			Location rowOneLocation = game.getLocationMarket().getLocation(n);
			Location rowTwoLocation = game.getLocationMarket().getLocation(n+4);
			this.firstMarketRow[n].addMouseListener(new LocationMarketListener(game, this, rowOneLocation));
			this.secondMarketRow[n].addMouseListener(new LocationMarketListener(game, this, rowTwoLocation));
			
			ImageIcon firstRowImage = new ImageIcon(rowOneLocation.getImage());
			ImageIcon secondRowImage = new ImageIcon(rowTwoLocation.getImage());
			this.firstMarketRow[n].setIcon(firstRowImage);
			this.secondMarketRow[n].setIcon(secondRowImage);
			
			firstRowPanel.add(this.firstMarketRow[n]);
			secondRowPanel.add(this.secondMarketRow[n]);
		}
		locationMarketPanel.add(firstRowPanel);
		locationMarketPanel.add(secondRowPanel);
		
		return locationMarketPanel;
	}

	/**
	 * Creates and returns the JPanel containing the graphics for the 4 buyable basic locations in the 
	 * location market.
	 * @param game The DiceCityGame instance being used to create the game overall.
	 * @return basicLocationMarketPanel the created JPanel containing the necissary labels.
	 */
	private JPanel createBasicLocationMarketPanel(DiceCityGame game){
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// CREATION OF BASIC LOCATION DECKS
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		JPanel basicLocationMarketPanel = new JPanel();
		ImageIcon lumberMillImage = new ImageIcon(game.getLocationMarket().getLumberMill().getImage());
		ImageIcon mineImage = new ImageIcon(game.getLocationMarket().getMine().getImage());
		ImageIcon quarryImage = new ImageIcon(game.getLocationMarket().getQuarry().getImage());
		ImageIcon armyImage = new ImageIcon(game.getLocationMarket().getRegularArmy().getImage());
		
		this.lumberMillDeck = new JLabel(lumberMillImage);
		this.mineDeck = new JLabel(mineImage);
		this.quarryDeck = new JLabel(quarryImage);
		this.armyDeck = new JLabel(armyImage);
		
		lumberMillDeck.addMouseListener(new LocationMarketListener(game, this, new LumberMill()));
		mineDeck.addMouseListener(new LocationMarketListener(game, this, new Mine()));
		quarryDeck.addMouseListener(new LocationMarketListener(game, this, new Quarry()));
		armyDeck.addMouseListener(new LocationMarketListener(game, this, new RegularArmy()));
		//System.out.println("SU: Basic Location Market listeners added.");
		basicLocationMarketPanel.add(lumberMillDeck);
		basicLocationMarketPanel.add(mineDeck);
		basicLocationMarketPanel.add(quarryDeck);
		basicLocationMarketPanel.add(armyDeck);
		
		basicLocationMarketPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		return basicLocationMarketPanel;
	}

	/**
	 * Creates and returns the JPanel containing the graphics for the entire shop panel
	 * @param game The DiceCityGame instance being used to create the game overall.
	 * @return shopPanel The JPanel containing the graphics for the shop panel in the game.
	 */
	private JPanel createShopPanel(DiceCityGame game){
		//Create shop panel and location market panel
		JPanel shopPanel = new JPanel();
		BoxLayout shopPanelLayout = new BoxLayout(shopPanel, BoxLayout.Y_AXIS);
		shopPanel.setLayout(shopPanelLayout);
		
		JPanel resourceCountPanel = new JPanel();
		BoxLayout resourceCountPanelLayout = new BoxLayout(resourceCountPanel, BoxLayout.Y_AXIS);
		resourceCountPanel.setLayout(resourceCountPanelLayout);
		
		String woodAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getWood());
		String stoneAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getStone());
		String metalAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getMetal());
		String swordAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getSwords());
		this.woodCounter = new JLabel("Wood: " + woodAmount);
		this.stoneCounter = new JLabel("Stone: " + stoneAmount);
		this.metalCounter = new JLabel("Metal: " + metalAmount);
		this.swordCounter = new JLabel("Swords: " + swordAmount);
		woodCounter.setAlignmentX(Component.RIGHT_ALIGNMENT);
		stoneCounter.setAlignmentX(Component.RIGHT_ALIGNMENT);
		metalCounter.setAlignmentX(Component.RIGHT_ALIGNMENT);
		swordCounter.setAlignmentX(Component.RIGHT_ALIGNMENT);
		woodCounter.setFont(new Font(woodCounter.getFont().getFontName(), Font.PLAIN, RESOURCE_FONT_SIZE));
		stoneCounter.setFont(new Font(woodCounter.getFont().getFontName(), Font.PLAIN, RESOURCE_FONT_SIZE));
		metalCounter.setFont(new Font(woodCounter.getFont().getFontName(), Font.PLAIN, RESOURCE_FONT_SIZE));
		swordCounter.setFont(new Font(woodCounter.getFont().getFontName(), Font.PLAIN, RESOURCE_FONT_SIZE));
		resourceCountPanel.add(woodCounter);
		resourceCountPanel.add(stoneCounter);
		resourceCountPanel.add(metalCounter);
		resourceCountPanel.add(swordCounter);
		
		shopPanel.add(createLocationMarketPanel(game));
		shopPanel.add(createBasicLocationMarketPanel(game));
		shopPanel.add(resourceCountPanel);
		
		return shopPanel;
	}
	
	/**
	 * Updates the dice next to the board to display the current value of the dice.
	 * @param game The instance of the game controller being used.
	 */
	public void updateDiceDisplay(DiceCityGame game){
		int whiteDieAdjustedIndex = game.getPlayerBoard(game.getPlayerNumber()).getWhiteDieValue() - 1;
		int yellowDieAdjustedIndex = game.getPlayerBoard(game.getPlayerNumber()).getYellowDieValue() - 1;
		int redDieAdjustedIndex  = game.getPlayerBoard(game.getPlayerNumber()).getRedDieValue() - 1;
		int blueDieAdjustedIndex = game.getPlayerBoard(game.getPlayerNumber()).getBlueDieValue() - 1;
		int blackDieAdjustedIndex = game.getPlayerBoard(game.getPlayerNumber()).getBlackDieValue() - 1;
		
		whiteDie.setIcon(diceFaces[WHITE_ROW][whiteDieAdjustedIndex]);
		yellowDie.setIcon(diceFaces[YELLOW_ROW][yellowDieAdjustedIndex]);
		redDie.setIcon(diceFaces[RED_ROW][redDieAdjustedIndex]);
		blueDie.setIcon(diceFaces[BLUE_ROW][blueDieAdjustedIndex]);
		blackDie.setIcon(diceFaces[BLACK_ROW][blackDieAdjustedIndex]);
	}

	/**
	 * Changes the visibility of the Dice labels to the specified value.
	 * @param visible If true, the labels will be visible.  If false, they will be invisible.
	 */
	public void changeDiceVisibilityTo(boolean visible){
		//If visible = true, then make dice icons visible.
		if(visible == true){
			whiteDie.setVisible(true);
			yellowDie.setVisible(true);
			redDie.setVisible(true);
			blueDie.setVisible(true);
			blackDie.setVisible(true);
		}else{
			whiteDie.setVisible(false);
			yellowDie.setVisible(false);
			redDie.setVisible(false);
			blueDie.setVisible(false);
			blackDie.setVisible(false);
		}
	}
	
	/**
	 * Returns the flag marking whether the main menu has been created and displayed to completion.
	 * @return mainMenuDisplayed The flag.  True if the main menu is displayd, false otherwise.
	 */
	public boolean checkIfMainMenuDisplayed(){
		return this.mainMenuDisplayed;
	}
	
	/**
	 * Returns the flag marking whether the main menu has been created and displayed to completion.
	 * @return gameViewDisplayed The flag. True if the game assets have been displayed, false otherwise.
	 */
	public boolean checkIfGameViewDisplayed(){
		return this.gameViewDisplayed;
	}
	
	/**
	 * Updates the labels that display the amount of resources owned by the player.
	 * @param game The DiceCityGame object in control of the game.
	 */
	public void updateResourceCounters(DiceCityGame game){
		String woodAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getWood());
		String stoneAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getStone());
		String metalAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getMetal());
		String swordAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getSwords());
		String vpAmount = Integer.toString(game.getPlayerBoard(game.getPlayerNumber()).getVP());
		this.woodCounter.setText("Wood: " + woodAmount);
		this.stoneCounter.setText("Stone: " + stoneAmount);
		this.metalCounter.setText("Metal: " + metalAmount);
		this.swordCounter.setText("Swords: " + swordAmount);
		this.vpCounter.setText("Victory Points: " + vpAmount);
	}

	/**
	 * Enables the listener objects needed to execute the attack phase of a turn.
	 * @param game the DiceCityGame object that is requesting the listeners be disabled.
	 */
	public void enableAttackPhaseListeners(DiceCityGame game){
		this.smallBanditLabel.addMouseListener(new BanditClickListener(game, this, new BanditsSmall()));
		this.mediumBanditLabel.addMouseListener(new BanditClickListener(game, this, new BanditsMed()));
		this.largeBanditLabel.addMouseListener(new BanditClickListener(game, this, new BanditsLarge()));
	}
	
	/**
	 * Disables the listener objects needed during the attack phase of a turn.
	 * @param game The DiceCityGame object that is requesting the listeners be disabled.
	 */
	public void disableAttackPhaseListeners(DiceCityGame game){
		int theListener = 0;
		MouseListener[] listenersToRemove = smallBanditLabel.getMouseListeners();
		smallBanditLabel.removeMouseListener(listenersToRemove[theListener]);
		
		listenersToRemove = mediumBanditLabel.getMouseListeners();
		mediumBanditLabel.removeMouseListener(listenersToRemove[theListener]);
		
		listenersToRemove = largeBanditLabel.getMouseListeners();
		largeBanditLabel.removeMouseListener(listenersToRemove[theListener]);
	}

	/**
	 * Enables the listener objects required to execute the building phase of a turn.
	 * @param game The DiceCityGame object that is requesting the listeners be enabled.
	 */
	public void enableBuildPhaseListeners(DiceCityGame game){
		LocationMarketListener listener = (LocationMarketListener)lumberMillDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();
		
		listener = (LocationMarketListener)quarryDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();
		
		listener = (LocationMarketListener)mineDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();
		
		listener = (LocationMarketListener)armyDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();
		
		for(JLabel locationLabel : this.firstMarketRow){
			listener = (LocationMarketListener)locationLabel.getMouseListeners()[FIRST_IN_LIST];
			listener.enable();
		}
		for(JLabel locationLabel : this.secondMarketRow){
			listener = (LocationMarketListener)locationLabel.getMouseListeners()[FIRST_IN_LIST];
			listener.enable();
		}
	}

	/**
	 * Disables the listener objects required during the building phase of a turn.
	 * @param game The DiceCityGame object that is requesting the listeners be disabled.
	 */
	public void disableBuildPhaseListeners(DiceCityGame game){
		LocationMarketListener listener = (LocationMarketListener)lumberMillDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		listener = (LocationMarketListener)quarryDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		listener = (LocationMarketListener)mineDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		listener = (LocationMarketListener)armyDeck.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		for(JLabel locationLabel : this.firstMarketRow){
			listener = (LocationMarketListener)locationLabel.getMouseListeners()[FIRST_IN_LIST];
			listener.disable();
		}
		for(JLabel locationLabel : this.secondMarketRow){
			listener = (LocationMarketListener)locationLabel.getMouseListeners()[FIRST_IN_LIST];
			listener.disable();
		}
	}

	/**
	 * Updates (repaints) the location images on the Board.  Primarily used to update the display when a new
	 * building is placed.
	 * @param game The DiceCityGame object requesting the board images to be updated.
	 */
	public void updateBoardImages(DiceCityGame game){
		//Re-obtain list of board locations.
		for(int row=0; row<5;row++){
			for(int column=0; column<6; column++){
				boardLocations[row][column] = game.getPlayerBoard(game.getPlayerNumber()).getCity()[row][column];
			}
		}
		
		int boardRow = 0;
		int boardColumn = 0;
		for(Location[] locationList : boardLocations){
			for(Location location : locationList){
				ImageIcon newIcon = new ImageIcon(boardLocations[boardRow][boardColumn].getImage());
				this.boardLocationLabels[boardRow][boardColumn].setIcon(newIcon);;
				boardColumn++;
			}
			boardRow++;
			boardColumn = 0;
		}

	}

	/**
	 * Updates (repaints) the location images in the location shop.  Primarily used to update the dispaly
	 * after a location is bought from the location market.
	 * @param game The DiceCityGame object that is requesting the location market images be repainted.
	 */
	public void updateShopImages(DiceCityGame game){
		for(int n = 0; n < 4; n++){
			Location rowOneLocation = game.getLocationMarket().getLocation(n);
			Location rowTwoLocation = game.getLocationMarket().getLocation(n+4);
			
			ImageIcon firstRowImage = new ImageIcon(rowOneLocation.getImage());
			ImageIcon secondRowImage = new ImageIcon(rowTwoLocation.getImage());
			this.firstMarketRow[n].setIcon(firstRowImage);
			this.secondMarketRow[n].setIcon(secondRowImage);
		}
	}
	
	/**
	 * Enables the listener objects placed on the dice labels.
	 */
	public void enableUseDicePhaseListeners(){
		DieRightClickListener listener = (DieRightClickListener)whiteDie.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();
		
		listener = (DieRightClickListener)yellowDie.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();
		
		listener = (DieRightClickListener)redDie.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();

		listener = (DieRightClickListener)blueDie.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();

		listener = (DieRightClickListener)blackDie.getMouseListeners()[FIRST_IN_LIST];
		listener.enable();

	}
	
	/**
	 * Diables the listener objects placed on the dice labels.
	 */
	public void disableUseDicePhaseListeners(){
		DieRightClickListener listener = (DieRightClickListener)whiteDie.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		listener = (DieRightClickListener)yellowDie.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		listener = (DieRightClickListener)redDie.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		listener = (DieRightClickListener)blueDie.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
		
		listener = (DieRightClickListener)blackDie.getMouseListeners()[FIRST_IN_LIST];
		listener.disable();
	}
	
	
}
