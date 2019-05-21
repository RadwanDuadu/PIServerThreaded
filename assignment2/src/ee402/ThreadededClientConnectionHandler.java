package ee402;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThreadededClientConnectionHandler extends Thread{
	private Socket Socket = null;				// Client socket object
	private ObjectInputStream is = null;			// Input stream
	private ObjectOutputStream os = null;			// Output stream
	private TempAndDateGui gui;
	private ArrayList<Integer> list = new ArrayList<Integer>();	
	private List<Integer> l20 = null; //to store the last 20 temperature values
	private APP canvas;
	private int offset; // temperature offset 

	
	//constructor for Client connection handler
	public ThreadededClientConnectionHandler(Socket Socket, TempAndDateGui gui, int offset) throws IOException {
		this.Socket = Socket;
		this.gui = gui;
		this.offset = offset;
		this.os = new ObjectOutputStream(this.Socket.getOutputStream());
		this.is= new ObjectInputStream(this.Socket.getInputStream());		
	}

	public void run() {
		while(true) {
			this.setSampleRate(); // sets the sample rate (1s)
		}
	}

	//gets the temperature values and puts the last 20 
	public void tempList() {	 
		int tempValue = (int) getTempValue() + offset;
		list.add(tempValue);	
		if(list.size() > 20) {				
			try {l20 = list.subList(list.size()-20, list.size());} 
			catch (IndexOutOfBoundsException e) 
			{	e.printStackTrace();
				l20 = list;
			} catch (NullPointerException e) {
				l20 = new ArrayList<Integer>();
			}
		}
		else {l20 = list;}
		System.out.println(l20);

	}

	//sets the sample rate 
	public int setSampleRate() {
		int sampleRate = gui.getSampleRate();
		while (sampleRate != 0) {
			this.tempList();		
			gui.Arraytemp(l20); 
			try {
				Thread.sleep(sampleRate);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			//updates the sample rate value 
			if(sampleRate!= gui.getSampleRate()) {
				sampleRate = gui.getSampleRate();				
			}				
		}	
		System.out.println(sampleRate);
		return sampleRate;
	}

	//specifically gets just temperature value, gets the TempService class 
	//from ThreadedConnectionHandler in the server
	public double getTempValue() {
		String theTempValueCommand = "GetTemp";
		String theTempValue = "";
		this.send(theTempValueCommand);
		try{
			theTempValue = (String) receive();	
		}
		catch (Exception e){		
			System.out.println(e);
		}	
		double tempvalue = Double.parseDouble(theTempValue)/1000;
		return tempvalue; 

	} 
	 public void LEDon() {
	    	String ledoncommand = "LEDON", ledon;
	    	System.out.println("01. -> Sending Command (" + ledoncommand + ") to the server...");
	    	this.send(ledoncommand);
	    	try{
	    		
	    		ledon = (String) receive();
	    		System.out.println("05. <- The Server responded with: ");
	    		System.out.println("    <- " + ledon);
	    		
	    	}
	    	catch (Exception e){
	    		System.out.println("XX. There was an invalid object sent back from the server");
	    	}
	    	System.out.println("06. -- Disconnected from Server.");
	    	
	    }
	 public void LEDoff() {
	    	String ledoffcommand = "LEDOFF", ledoff;
	    	System.out.println("01. -> Sending Command (" + ledoffcommand + ") to the server...");
	    	this.send(ledoffcommand);
	    	try{
	    		
	    		ledoff = (String) receive();
	    		System.out.println("05. <- The Server responded with: ");
	    		System.out.println("    <- " + ledoff);
	    		
	    	}
	    	catch (Exception e){
	    		System.out.println("XX. There was an invalid object sent back from the server");
	    	}
	    	System.out.println("06. -- Disconnected from Server.");
	    	
	    }
	 public void getDate() {
	    	String theDateCommand = "GetDate", theDateAndTime;
	    	System.out.println("01. -> Sending Command (" + theDateCommand + ") to the server...");
	    	this.send(theDateCommand);
	    	try{
	    		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss").format(new java.util.Date());
	    		theDateAndTime = " "+ timeStamp +receive();
	    		System.out.println("05. <- The Server responded with: ");
	    		System.out.println("    <- " + theDateAndTime);
	    		
	    	}
	    	catch (Exception e){
	    		System.out.println("XX. There was an invalid object sent back from the server");
	    	}
	    	System.out.println("06. -- Disconnected from Server.");
	    	
	    }
	 
    private void send(Object o) {
		try {
			    System.out.println("02. -> Sending an object...");
			    os.writeObject(o);
			    os.flush();
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Sending:" +  e.toString());
		}
    }

    // method to receive a generic object.
    private Object receive() 
    {
		Object o = null;
		
		
		try {
				System.out.println("03. -- About to receive an object...");
			    o= is.readObject();
			    System.out.println("04. <- Object received...");
			    } 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Receiving:" + e.toString());
		}
		
		return o;
    }


}
