package typ_game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TextRead {
	public String[] str = new String[100];
	private URL url;

	//指定された番号の問題を読み込み、それを返す
	public String[] getQetString(int qetNum){
		String s;
		int i=0;
		try{
			ClassLoader cl = this.getClass().getClassLoader();
			int SqetNum=qetNum%5;
			url=cl.getResource("res/QusetionText_div"+SqetNum+".txt");	
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
	
}



