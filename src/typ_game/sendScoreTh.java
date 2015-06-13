package typ_game;

import java.io.IOException;

public class sendScoreTh extends Thread {
	private Client clt;
	private double score;
	
	sendScoreTh(Client tempclt, double tempscore){
		this.clt=tempclt;
		this.score=tempscore;
	}
	public void run(){
		try {
			clt.sendScore(score);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
