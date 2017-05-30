/**
 * 
 */
package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import gui.ServerConsole;

/**
 * @author Owner Bengtson
 * The DiceCityServer creates a server socket for game clients to connect to.
 * When a client connects to the server, the server creates a new socket in order to communicate with the client.
 * The newly created socket is given to a new server-side thread, and the thread is added to the synchronized list
 * of client communicators.  The class actually responsible for communicating with the client is the ClientHandlerThread.
 */
public class DiceCityServer {

	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static ServerConsole serverConsole;
	
	private static final int PORT = 1331;
	private static SynchronizedThreads clientThreads;
	
	/**
	 * This server waits for for client sockets to connect to it, at which point it adds a new ClientHandlerThread object to
	 * the list of SynchronizedThreads, where it is run to handle the request of the client.
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Create the list of sockets that communicate with the clients.
		clientThreads = new SynchronizedThreads(serverConsole);
		//Create the server console window.
		serverConsole = new ServerConsole();
		
		//Try to create the server socket which clients will connet to.
		try{
			serverSocket = new ServerSocket(PORT);
		}catch(IOException e){
			System.out.println("Error when starting new server: " + e);
		}
		
		//Wait for new clients to connect.  When one connects,
		//create a new thread to communicate with it, and give this thread to the Synchronized list.
		while(true){
			try{
				//Wait for a client to connect.  
				//Once a client connects,create a new socket that is connected to their socket.
				clientSocket = serverSocket.accept();
				//Acknowledge client connection in console.
				serverConsole.println("New client connection.  Launching new handler.");
				//Create a new thread, giving it the newly created socket that is connected to the client.
				//This thread will be what is communicating with the client.
				clientThreads.addThread(new ClientHandlerThread(clientSocket, clientThreads, serverConsole));

			}catch(IOException e){
				System.out.println("Error when accepting new client connection: " + e);
			}
		}//End of while
		
	}//End of main

}
