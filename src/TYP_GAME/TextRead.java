package typ_game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TextRead {
	String[] str = new String[50];
	public String[] getQetString(){
		String s;
		int i=0;
	try{
		ClassLoader cl = this.getClass().getClassLoader();
		URL url=cl.getResource("res/QusetionText_div.txt");	
		BufferedReader br = new BufferedReader( 
				new InputStreamReader(url.openStream()));
			while((s=br.readLine()) != null){
				str[i]=s;
				i++;
			}
			br.close();
		
	}catch(FileNotFoundException e){
		
	}catch(IOException e){
		
	}
	return str;
	}
	
	private static boolean checkBeforeReadfile(File file){
	    if (file.exists()){
	      if (file.isFile() && file.canRead()){
	        return true;
	      }
	    }

	    return false;
	  }
}



