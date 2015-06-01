package TYP_GAME;

public class DispKey {
	private TextRead TR;
	private String question="hello world";  //タイピング問題文字列
	public String[] qet_buf = new String[50];	//問題配列 
    private int charNumMax;   //タイピング問題文字列の文字列長
    
    DispKey(int bufNum){
    	TR= new TextRead();
    	qet_buf=TR.getQetString();
    	if(bufNum<50&&qet_buf[bufNum]!=null){
    		question=qet_buf[bufNum];
    	}
        charNumMax = question.length();
    }
    
    /*引数「num」で指定された問題番号に対応する文字コードを返す*/
    private int getActiveCharCode(int num){
        int activeCharCode = 0;
        if(num < charNumMax){
            activeCharCode = (int)question.charAt(num);
            if(activeCharCode >= 97 && activeCharCode<= 122) activeCharCode=activeCharCode-32;
        }
        return activeCharCode;
    }
    
    /*正解・不正解判定*/
    public int compareToInpKey(int inpKeyCode, int num){
        int ret;
        int upperKeyCode = 0;
        
        ret = 0;
        
        if(inpKeyCode >= 65 && inpKeyCode <= 90|| inpKeyCode==(int)' ' || inpKeyCode==(int)'.'||inpKeyCode==(int)',' ){
            upperKeyCode = inpKeyCode;
       }
        else{
            ret = 2;
        }
        
        if(ret != 2){
            /*正解・不正解判定*/
            if(upperKeyCode == this.getActiveCharCode(num)){
                ret = 0;
            }else{
                ret = 1;
            }
        }
        
        /*戻り値　0:正解
                　1:不正解
                　2:解答対象外 */
        return ret;
   
    }
    
    /*問題分の文字列を返す*/
    public String getQetString(){
        return question;
    }
    
    /*タイピング問題文字列の文字列長を返す*/
    public int getCharNumMax(){
        return charNumMax;
    }
    
    /*得点を返す*/
    

}
