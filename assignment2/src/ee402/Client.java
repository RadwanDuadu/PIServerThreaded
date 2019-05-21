/* The Client Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 * 
 * 
 */

package ee402;

import java.net.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Client {
	
	private Socket socket = null;
	private TempAndDateGui gui;
	private ThreadededClientConnectionHandler con;
	private APP canvas;

	
    public Client() {
    	gui = new TempAndDateGui();
    }
    
 // the method expects the IP address of the server - the port is fixed
    private boolean connectToServer(String serverIP,int portNumber, int offset) {
    	try { // open a new socket to the server 
    			this.socket = new Socket(serverIP,portNumber);
	    		//this.os = new ObjectOutputStream(this.socket.getOutputStream());
	    		//this.is = new ObjectInputStream(this.socket.getInputStream());
	    		System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress() 
	    				+ " on port: " + this.socket.getPort());
	    		System.out.println("    -> from local address: " + this.socket.getLocalAddress() 
	    				+ " and port: " + this.socket.getLocalPort());
	    		
	    		if(socket != null && gui != null) {
					con = new ThreadededClientConnectionHandler(socket, gui, offset);
					con.start();
								}
    		
    	} 
        catch (Exception e) {
        	System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
        	System.out.println("    Exception: " + e.toString());	
        	return false;
        }
    	
		return true;
    }

   

    public static void main(String args[]) throws InterruptedException 
    {
    	System.out.println("**. Java Client Application - EE402 OOP Module, DCU");
    		Client theApp = new Client();
    		theApp.connectToServer("192.168.0.31", 5051, 0);
    		theApp.connectToServer("192.168.0.31", 5052, 40);
    		theApp.connectToServer("192.168.0.31", 5053, 80);
    		theApp.connectToServer("192.168.0.31", 5054, 120);
    		theApp.connectToServer("192.168.0.31", 5055, 160);	    
		
    }
}