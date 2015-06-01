package TYP_GAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextRead {
	String[] str = new String[50];
	public String[] getQetString(){
		String s;
		int i=0;
	try{
		File file = new File("/home/koki/workspace/TypingGame/QuestionText_div.txt");
		if (checkBeforeReadfile(file)){
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((s=br.readLine()) != null){
				str[i]=s;
				i++;
			}
			br.close();
		}
		else{
			System.out.println("File cannot find or open.");
		}
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



