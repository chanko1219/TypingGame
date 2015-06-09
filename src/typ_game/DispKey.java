package typ_game;

public class DispKey {
	private TextRead TR;
	private String question="?'123456789";  //タイピング問題文字列
	private String[] qet_buf = new String[100];	//問題配列 
    private int charNumMax;   //タイピング問題文字列の文字列長
    
    DispKey(int qetNum){
    	TR= new TextRead();
    	qet_buf=TR.getQetString(qetNum);
    }
    
    //bufNum行の文字列を問題分に設定する
    public void setQuestion(int bufNum){
    	if(bufNum<100&&qet_buf[bufNum]!=null){
    		question=qet_buf[bufNum];
    	}
        charNumMax = question.length();
    }
    
    /*引数「num」で指定された問題番号に対応する文字を返す*/
    private char getActiveChar(int num){
        char activeChar = 0;
        if(num < charNumMax){
            activeChar = question.charAt(num);
            //if(activeChar >= 'a' && activeChar<= 'z') activeChar=activeChar-32;
        }
        return activeChar;
    }
    
    /*正解・不正解判定*/
    public int compareToInpKey(char input_c, int num){
        int ret;
        int upperKeyChar = 0;
        
        ret = 0;
        
        if(input_c >= 'A' && input_c <= 'Z'||input_c >= 'a' && input_c <= 'z'|| input_c >= '0' && input_c <= '9'||
        		input_c==' ' || input_c=='.'||input_c==',' ||input_c=='?'||input_c=='\''||input_c=='-'){
            upperKeyChar = input_c;
       }
        else{
            ret = 2;
        }
        
        if(ret != 2){
            /*正解・不正解判定*/
            if(upperKeyChar == this.getActiveChar(num)){
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
    
    

}
