package typ_game;

import java.io.IOException;

public class ComFlag extends Thread {
	private Client clt;
	
	ComFlag(Client tempclt){
		this.clt=tempclt;
	}
	public void run(){
		try {
			clt.sendScore(-1);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
