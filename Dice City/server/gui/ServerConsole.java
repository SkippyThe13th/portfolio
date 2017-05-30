/**
 * 
 */
package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Owner Bengtson
 * This is a class for a very basic server console object.  It creates a window with 
 * a text area that can print text via use of it's print function.  However the console
 * window does not scroll once the text hits the bottom.  This is a very simple way of 
 * recieving visual confirmation of player connection and requests.
 */
public class ServerConsole {
	
	JFrame consoleFrame;
	JScrollPane scrollPane;
	JTextArea textArea;
	
	/**
	 * Constructor.
	 */
	public ServerConsole(){
		
		consoleFrame = new JFrame("Dice City Server Log");
		consoleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		consoleFrame.setPreferredSize(new Dimension(640, 480));
		consoleFrame.setSize(640, 480);
		
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(640, 480));
		
		scrollPane = new JScrollPane(textArea);
		
		consoleFrame.add(scrollPane);
		consoleFrame.revalidate();
		consoleFrame.repaint();
		consoleFrame.setVisible(true);
		
	}
	
	/**
	 * Prinst the passed string tot he console window on a new line.
	 */
	public void println(String message){
		textArea.append(message);
		textArea.append("\n");
	}
	
}
