package techEquipment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pr.Message;

public class Reader {
	private static BufferedReader reader;
	private static String request;
	
	
	public static String readRequest(){
		if(reader == null){
			reader = new BufferedReader(new InputStreamReader(System.in));
		}
		try {			
			request = reader.readLine();
							
		} catch (IOException e) {
			Writer.write(Message.ifException);
			// logging e.printStackTrace(); 
		} 
				
		return request;	
	}

	//just in case
	public String getRequest(){
		return request;	
	}
	
	public static void closeReader(){
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}