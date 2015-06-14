package typ_game;

import java.io.IOException;
import java.util.TimerTask;

public class WaitTask extends TimerTask{
	private GameFrame GF;
	//private Client clt;
	private int cntTime;
	
	WaitTask(GameFrame tempgf){
		//this.clt=tempclt;
		this.GF=tempgf;
	}
	
	@Override
	public void run() {
		try {
			if(GF.clt.getStartable()||cntTime>60){
				this.cancel();
				GF.changePanel("typing");
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		GF.changeWaitingText(cntTime);
		cntTime++;
	}

}
