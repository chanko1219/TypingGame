package TYP_GAME;

public class Client {
	private Server s;		//サーバークラス
	private TypingGame ap;	//タイピングゲームクラス
	
	Client(){
		ap = new TypingGame();
	}
	public int sendScore(){
		int score=0;
		return score;
	}
	
}
