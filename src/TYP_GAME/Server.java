package TYP_GAME;

public class Server {
	private Client c; 		//クライアントクラス
	private int score;
	private String question="hello world";
	
	public String sendData(){
		return question;
	}
}