/**
 * 
 */
package sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import edu.truman.bengtsonc.DiceCityClient;
import gui.ServerConsole;

/**
 * @author Owner Bengtson
 * This class defines a Thread object used to handle client requests to the DiceCityServer object.
 */
public class ClientHandlerThread extends Thread {

	//Socket used to connect to the client.
	private Socket clientSocket;
	//The list of threads used to respond to client requests.
	private SynchronizedThreads clientThreads;
//	private ObjectInputStream input;
//	private ObjectOutputStream output;
	//Stream input and output.
	private static BufferedReader input;
	private static BufferedWriter output;
	//The server console
	private ServerConsole console;
	
	private String request;
	private int senderOfRequest;
	
	/**
	 * Constructor for a Thread object used to handle client requests.
	 * @param clientSocket The socket of the client that connected to the server.
	 * @param clientThreads The list of other ClientHandlerThread object used to respond to client requests.
	 * @param serverConsole The server console created by the server.
	 */
	public ClientHandlerThread(Socket clientSocket, SynchronizedThreads clientThreads, ServerConsole serverConsole){
		
		this.clientSocket = clientSocket;
		this.clientThreads = clientThreads;
		this.console = serverConsole;
	}
	
	/**
	 * Implemented from Runnable
	 */
	public void run(){
		
		//Try to create input and output streams
		//Listen for the client to send messages, and print them to the server console when it does.
		try{
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		}catch(IOException e){
			System.out.println("Error when opening streams in new ClientHandler: "+e);
		}
		
		try{
			while(!input.ready()){
				
			}


		}catch(IOException e){
			System.out.println("Error while waiting for client request: "+e);
		}
		
		try{
		this.request = input.readLine();
		
			if(request.equals(DiceCityClient.REQUEST_NUMBER)){
				handleNumberRequest();
			}else if(request.equals(DiceCityClient.REQUEST_PLAYER_TURN)){
				handleTurnRequest();
			}else if(request.equals(DiceCityClient.NOTIFY_END_OF_TURN)){
				handleEndOfTurnNotification();
			}else if(request.equals(DiceCityClient.NOTIFY_START_GAME)){
				handleStartGameNotification();
			}else if(request.equals(DiceCityClient.REQUEST_CHECK_IF_GAME_OPEN)){
				handleCheckGameClosedRequest();
			}else if(request.equals(DiceCityClient.REQUEST_PLAYER_COUNT)){
				handlePlayerCountRequest();
			}else{
				System.out.println("Server: The sent request <" +request+"> did not match any known commands.");
			}
		}catch(IOException e){
			System.out.println("Error when handling client's request: " + e);
		}
		try{
			while(!clientSocket.isClosed()){

			}
			
			//Close streams and socket.
			input.close();
			output.close();
			clientSocket.close();
			
		}catch(IOException e){
			System.out.println("Error when closing streams in ClientHandler: " + e);
		}
		
	}//End of run
	
	/**
	 * Handles the client request for a player number by calling the appropriate function to generate a new one.
	 * Replies to the client by sending the newly generated player number.
	 */
	private void handleNumberRequest(){
		try{
			System.out.println("Server: Request for player number recieved.");
			int playerNumber = generatePlayerNumber();
			String playerNumberString = Integer.toString(playerNumber);
			console.println("Sending player number "+playerNumberString+" to client.");
			output.write(playerNumberString);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when handline number request: "+e);
		}
	}
	
	/**
	 * Handles the client request asking which player's turn is currently active.
	 * Sends int representing current player's active turn.
	 */
	private void handleTurnRequest(){
		try{
			System.out.println("Server: Request for which player's turn is active recieved.");
			int playerTurn = clientThreads.getPlayerTurn();
			String playerTurnString = Integer.toString(playerTurn);
			console.println("Sending player turn "+playerTurnString+" to client.");
			output.write(playerTurnString);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when handling turn request: "+e);
		}
	}
	
	/**
	 * Handles the client notification indicating the end of a turn.  Signals the 
	 * SynchronizedThreads object to move the turn count to the next player.
	 */
	private void handleEndOfTurnNotification(){
		System.out.println("Server: Notification of end of turn recieved.");
		clientThreads.moveToNextTurn();
	}
	
	/**
	 * Handles the client notification that signals a player started the game.
	 * Tells the SynchronizedThread object to close the game so that no new players
	 * may join.
	 */
	private void handleStartGameNotification(){
		clientThreads.closeGame();
	}
	
	/**
	 * Handles the client request that asks if the game is open or closed.
	 * Sends the apropriate boolean value back to the client.
	 */
	private void handleCheckGameClosedRequest(){
		boolean gameOpenStatus = clientThreads.getGameStatus();
		
		try{
			if(gameOpenStatus == true){
				output.write("true");
			}else{
				output.write("false");
			}
			
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when sending game status to client: "+e);
		}
	}
	
	/**
	 * Handles the client request asking how many players are currently connected
	 * to the game.  Sends the corresponding number as a reply.
	 */
	private void handlePlayerCountRequest(){
		try{
			System.out.println("Server: Request for player count recieved.");
			int playerCount = clientThreads.getPlayerCount();
			String playerCountString = Integer.toString(playerCount);
			console.println("Sending player count "+playerCountString+" to client.");
			output.write(playerCountString);
			output.newLine();
			output.flush();
		}catch(IOException e){
			System.out.println("Error when handling player count request: "+e);
		}
	}
	
	/**
	 * Generates a new player number base on the current number of connected players.
	 * The highest value a player number can be is 4.  If 4 players are already accounted
	 * for, then the new number generated is -1, indicated that the recieving player is not
	 * a member of the current game.
	 * @return int between [1, 4] or -1 if there are already 4 players accounted for.
	 */
	public int generatePlayerNumber(){
		boolean maxPlayersReached = clientThreads.getPlayerCount() >= 4;
		
		if(!maxPlayersReached){
			clientThreads.addPlayer();
			return clientThreads.getPlayerCount();
		}else{
			return -1;
		}
	}
	
}
