/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.truman.bengtsonc.DiceCityClient;
import gameControl.DiceCityGame;

/**
 * @author Owner Bengtson
 * A window used to tell the player how many players have connected to the game they 
 * are connected to, as well as allowing the player to start the game.  This window also
 * queuries the user for the ip and port of the server that they wish to connect to.
 */
public class ConnectingWindow {
	
	public final static int WINDOW_WIDTH = 350;
	public final static int WINDOW_HEIGHT = 150;
	
	private JFrame windowFrame;
	private JPanel ipEntry;
	private JPanel portEntry;
	private JPanel connectionPanel;
	private JTextField serverIPField;
	private JTextField serverPortField;
	private JLabel connectedText;
	private JLabel ipLabel;
	private JLabel portLabel;
	private JButton connectButton;
	private JButton startButton;
	
	private DiceCityGame theGame;
	
	private int playerCount;
	private boolean connected;
	private String ip;
	private int port;

	/**
	 * Constructor.
	 */
	public ConnectingWindow(DiceCityGame theGame){
		windowFrame = new JFrame();
		ipEntry = new JPanel();
		portEntry = new JPanel();
		connectionPanel = new JPanel();
		serverIPField = new JTextField();
		serverPortField = new JTextField();
		connectedText = new JLabel("Not connected to a server...");
		ipLabel = new JLabel("Server IP");
		portLabel = new JLabel("Server Port");
		startButton = new JButton("Start Game");
		connectButton = new JButton("Connect to Server");
		
		this.theGame = theGame;
		
		connected = false;
		
		windowFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		windowFrame.setLayout(new BorderLayout());
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ipEntry.setLayout(new BorderLayout());
		ipEntry.add(ipLabel, BorderLayout.WEST);
		ipEntry.add(serverIPField, BorderLayout.CENTER);
		
		portEntry.setLayout(new BorderLayout());
		portEntry.add(portLabel, BorderLayout.WEST);
		portEntry.add(serverPortField, BorderLayout.CENTER);
		
		connectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Get player number.
				if(connected == false){
					ip = serverIPField.getText();
					theGame.setServerIP(ip);
					port = Integer.parseInt(serverPortField.getText());
					theGame.setServerPort(port);
					DiceCityClient connectorClient = new DiceCityClient(ip, port);
					connectorClient.requestPlayerNumber();
					theGame.setPlayerNumber(connectorClient.getPlayerNumber());
					connected = true;
				}

			}
		});
		
		connectionPanel.setLayout(new BorderLayout());
		connectionPanel.add(ipEntry, BorderLayout.NORTH);
		connectionPanel.add(portEntry, BorderLayout.CENTER);
		connectionPanel.add(connectButton, BorderLayout.SOUTH);
		
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(connected){
					DiceCityClient gameStarter = new DiceCityClient(ip, port);
					gameStarter.tellStartGame();
					//windowFrame.dispose();
				}
			}
		});
		
		windowFrame.add(connectionPanel, BorderLayout.NORTH);
		windowFrame.add(connectedText, BorderLayout.CENTER);
		windowFrame.add(startButton, BorderLayout.SOUTH);
	}
	
	/**
	 * Displays the window
	 */
	public void displayWindow(){
		windowFrame.revalidate();
		windowFrame.repaint();
		windowFrame.setVisible(true);
	}
	
	/**
	 * Updates the text displayed on the window.
	 */
	public void updateText(){
		DiceCityClient playerCountChecker = new DiceCityClient(ip, port);
		playerCountChecker.requestPlayerCount();
		playerCount = playerCountChecker.getPlayerCount();
		
		connectedText.setText(playerCount+" Player(s) currently connected and waiting.  You are player "+theGame.getPlayerNumber());
		connectedText.revalidate();
		connectedText.repaint();
		
	}
	
	/**
	 * Returns the JFrame of the window.
	 * @return The JFrame of the window.
	 */
	public JFrame getFrame(){
		return this.windowFrame;
	}
	
	/**
	 * Returns the number of players connected.
	 * @return The number of players connected to the game.
	 */
	public int getPlayerCount(){
		return this.playerCount;
	}
	
	/**
	 * Returns a boolean values indicating whether or not the game is connected to the server.
	 * @return true if connected, false otherwise.
	 */
	public boolean isConnected(){
		return this.connected;
	}
	
}
