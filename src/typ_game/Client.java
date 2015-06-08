package typ_game;

public class Client {
	private String c_name;		//クライアント名
	private String s_name;		//サーバー名
	private int qetNum;		//問題番号
	
	Client(String str1, String str2){
		this.c_name=str1;
		this.s_name=str2;
		this.qetNum=0;
	}
	
	//サーバーとの接続確立を行いqet_numにサーバーから得た問題番号を格納
	public void initServer(){
		
		
	}
	
	//問題番号を取得する
	public int getQetNum(){
		return this.qetNum;
	}
	
	
	//サーバーに得点を送信
	public void sendScore(double score){
		
	}
}
