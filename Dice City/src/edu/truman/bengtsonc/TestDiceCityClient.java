/**
 * 
 */
package edu.truman.bengtsonc;

import static org.junit.Assert.*;

import org.junit.Test;

import sockets.DiceCityServer;

/**
 * @author Owner Bengtson
 *
 */
public class TestDiceCityClient {

	public class TestServerThread implements Runnable{

		public TestServerThread(){
			
		}
		
		@Override
		public void run() {
			DiceCityServer.main(null);
			
		}
		
	}
	
	private String ip = "192.168.0.6";
	private int port = 1331;
	
	//@Test
	public void testRequestPlayerNumber() {
		new Thread(new TestServerThread()).start();
		DiceCityClient newClient = new DiceCityClient(ip, port);
		
		newClient.requestPlayerNumber();
		int playerNumber = newClient.getPlayerNumber();
		
		assertEquals(1, playerNumber);
	}
	
	//@Test
	public void testRequestPlayerTurn(){
		new Thread(new TestServerThread()).start();
		DiceCityClient newClient = new DiceCityClient(ip, port);
		
		newClient.requestTurn();
		int playerTurn = newClient.getTurn();
		
		assertEquals(1, playerTurn);
	}
	
	//@Test
	public void testTellTurnEnded(){
		new Thread(new TestServerThread()).start();
		DiceCityClient firstClient = new DiceCityClient(ip, port);
		DiceCityClient secondClient = new DiceCityClient(ip, port);
		
		firstClient.requestPlayerNumber();
		assertEquals(1, firstClient.getPlayerNumber());
		secondClient.requestPlayerNumber();
		assertEquals(2, secondClient.getPlayerNumber());
		
		firstClient.requestTurn();
		int playerTurn = firstClient.getTurn();
		assertEquals(1, playerTurn);
		
		//End first turn, should move to player 2's turn.
		firstClient.tellTurnEnded(firstClient.getPlayerNumber());
		firstClient.requestTurn();
		playerTurn = firstClient.getTurn();
		System.out.println("Value of playerTurn before assert is: "+playerTurn);
		
		assertEquals(2, playerTurn);
	}

	@Test
	public void testCheckGameClosed(){
		new Thread(new TestServerThread()).start();
		DiceCityClient newClient = new DiceCityClient(ip, port);
		
		System.out.println("Requesting state of game.");
		boolean checkResult = newClient.requestCheckIfGameOpen();
		System.out.println("State of game recieved");
		
		assertEquals(true, checkResult);
		
		//Close game
		newClient.tellStartGame();

		checkResult = newClient.requestCheckIfGameOpen();
		
		assertEquals(false, checkResult);
	}
	
}
