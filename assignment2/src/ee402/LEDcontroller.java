package ee402;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class LEDcontroller {
	public static String PATH ="/sys/class/leds/led0/trigger";
	
	public void LEDON() {
		try {		 
		BufferedWriter bw = new BufferedWriter(new FileWriter(PATH));
		 bw.write("none");        
		 bw.close();        
		 bw = new BufferedWriter(new FileWriter(PATH+"/brightness")); 
		 bw.write(1);        
		 bw.close();  }
		catch(FileNotFoundException e){
			System.out.println("Unable to open file");
			e.printStackTrace();
		}
		catch(IOException e){
			System.out.println("Error write file");
			e.printStackTrace();
		}
		 }    
	
	public void LEDOFF() {   
		try {
		BufferedWriter bw = new BufferedWriter(new FileWriter(PATH)); 
		bw.write("none");       
		bw.close();        
		bw = new BufferedWriter(new FileWriter(PATH+"/brightness")); 
		bw.write(0);       
		bw.close();
	}
	catch(FileNotFoundException e){
		System.out.println("Unable to open file");
		e.printStackTrace();
	}
	catch(IOException e){
		System.out.println("Error write file");
		e.printStackTrace();
	}
		} 

}
