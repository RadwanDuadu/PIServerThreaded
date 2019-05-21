package ee402;


import java.io.*;

public class TempService
{
	private String filename = "/sys/class/thermal/thermal_zone0/temp";
	private String line = null;
	private String tempValue;

	public String getTemperature(){
		try{
		       BufferedReader br = new BufferedReader(new FileReader(filename)); 
		       while((line = br.readLine()) != null) {
		          System.out.println(line);
		          tempValue = line;
		       } 
		       br.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Unable to open file");
			e.printStackTrace();
		}
		catch(IOException e){
			System.out.println("Error reading file");
			e.printStackTrace();
		}
		return  tempValue;
	}
}
