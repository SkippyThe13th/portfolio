/**
 * 
 */
package edu.truman.bengtsonc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gameControl.*;
import gui.*;

/**
 * @author Chase Bengtson
 * This class is the way in which lone games connect and "talk" with the DiceCityServer object.  It contains
 * various functions that connect to the server, then send a request, wait for a reply, and then usually 
 * store the response.  Each of these communications with the server creates a socket connection to communicate
 * with the server.
 */
public class DiceCityClient{
	
	//Port number is hard-coded in, until a method for user input is made.
	public static final String REQUEST_NUMBER = "?number";
	public static final String REQUEST_PLAYER_TURN = "?turn";
	public static final String NOTIFY_END_OF_TURN = "!endturn";
	public static final String NOTIFY_START_GAME = "!start";
	public static final String REQUEST_CHECK_IF_GAME_OPEN = "?open";
	public static final String REQUEST_PLAYER_COUNT = "?count";

	private static Socket clientSocket = null;
//	private static ObjectInputStream input;
//	private static ObjectOutputStream output;
	private static BufferedReader input = null;
	private static BufferedWriter output = null;
	private int port;
	private String ip;
	
	//Variables to store information returned by the server.
	private int playerNumber;
	private int playerTurn;
	private int playerCount;
	private static boolean closed = false;
	
	/**
	 * Constructor.  Sets the default plalyer number, which is -1, merely to indicate that this client
	 * has not been issued a player number.  To be issued one, it must call the requestPlayerNumber function.
	 */
	public DiceCityClient(String ip, int port){
		this.ip = ip;
		this.port = port;
		this.playerNumber = -1;
	}
	
	/**
	 * Requests a player number from the server.  If the game controlled by the server is full,
	 * the returned value will be -1.  The returned value is stored in the playerNumber variable.
	 */
	public void requestPlayerNumber(){
		try{
			//Initialize socket and streams
			clientSocket = new Socket(ip, port);
			output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			output.write(REQUEST_NUMBER);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error requesting player number: " + e);
		}
		
		try{
			
			//Wait until server responds.
			while(!input.ready()){
				
			}
			//Store server response (should be a number between [1, 4]
			String playerNumberString = input.readLine();
			//System.out.println("New player number "+playerNumberString+" generated.");
			this.playerNumber = Integer.parseInt(playerNumberString);
			
			//Close Streams and socket, severing the connection.
			output.close();
			input.close();
			clientSocket.close();
		}catch(IOException e){
			System.out.println("Error retrieving player number: " + e);
		}

	}

	/**
	 * Asks the server which player's turn is currently active.  Stores the integer returned in the 
	 * playerTurn variable.
	 */
	public void requestTurn(){
		try{
			System.out.println("Attempting connection to server to request current player turn.");
			clientSocket = new Socket(ip, port);
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			
			//Request the current player turn from server.
			output.write(REQUEST_PLAYER_TURN);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when requesting turn: "+e);
		}
		
		try{
			//Wait for server response
			while(!input.ready()){
				
			}
			//Store server response
			String turnString = input.readLine();
			this.playerTurn = Integer.parseInt(turnString);
			//System.out.println("The value of playerTurn on client side is: "+this.playerTurn);
			
			//Close socket and streams.
			output.close();
			input.close();
			clientSocket.close();
			
		}catch(IOException e){
			System.out.println("Error when storing turn: "+e);
		}

	}
	
	/**
	 * Asks the server if the game it is controlling has begun.
	 * @return Returns true if the game has not yet started, and so can be joined.  False otherwise.
	 */
	public boolean requestCheckIfGameOpen(){
		try{
			clientSocket = new Socket(ip, port);
			output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			output.write(REQUEST_CHECK_IF_GAME_OPEN);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when requesting check if game is closed: "+e);
		}
		boolean replyBoolean = false;
		try{
			while(!input.ready()){
				
			}
			
			String reply = input.readLine();

			if(reply.equals("true")){
				replyBoolean = true;
			}
			
			input.close();
			output.close();
			clientSocket.close();

		}catch(IOException e){
			System.out.println("Error when processing server reply to check game closed request: "+e);
		}
		return replyBoolean;
	}

	/**
	 * Asks the server how many players are currently connected to the game.  Stores the 
	 * response in the playerCount variable.
	 */
	public void requestPlayerCount(){
		try{
			System.out.println("Attempting connection to server to request current player count.");
			clientSocket = new Socket(ip, port);
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			
			//Request the current player turn from server.
			output.write(REQUEST_PLAYER_COUNT);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when requesting player count: "+e);
		}
		
		try{
			//Wait for server response
			while(!input.ready()){
				
			}
			//Store server response
			String countString = input.readLine();
			this.playerCount = Integer.parseInt(countString);
			
			//Close socket and streams.
			output.close();
			input.close();
			clientSocket.close();
			
		}catch(IOException e){
			System.out.println("Error when storing player count: "+e);
		}
	}
	
	/**
	 * Informs the server that a player has just ended their turn.
	 * @param senderNumber The player number of the player who ended their turn.
	 */
	public void tellTurnEnded(int senderNumber){
		try{
			//Initialize socket and output
			clientSocket = new Socket(ip, port);
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			
			System.out.println("Informing server of end of turn from player "+senderNumber);
			output.write(NOTIFY_END_OF_TURN);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when trying to tell end of turn.");
		}
		
		try{
			output.close();
			input.close();
			clientSocket.close();
		}catch(IOException e){
			System.out.println("Error when trying to close streams in tellTurnEnded: "+e);
		}
		
	}
	
	/**
	 * Tells the server to start the game.  This is triggered when the Start button
	 * is pressed on a player's connecting window.
	 */
	public void tellStartGame(){
		try{
			System.out.println("Requesting that server start game.");
			clientSocket = new Socket(ip, port);
			output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			output.write(NOTIFY_START_GAME);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when requesting start of game: "+e);
		}
		
		try{
			input.close();
			output.close();
			clientSocket.close();
		}catch(IOException e){
			System.out.println("Error when closing streams and socket after requesting start of game: ");
		}
	}
	
	/**
	 * Returns the player number stored in this object.
	 * @return int playerNumber The player number
	 */
	public int getPlayerNumber(){
		return this.playerNumber;
	}
	
	/**
	 * Returns the integer indicating which player's turn is currently active.
	 * @return The integer indicating who's turn is active. (Player 1, 2, 3, or 4)
	 */
	public int getTurn(){
		return this.playerTurn;
	}
	
	/**
	 * Returns the playerCount variable stored in this object.
	 * @return the integer indicating how many players are connected to the game at the time it was acquired.
	 */
	public int getPlayerCount(){
		return this.playerCount;
	}
	
}
