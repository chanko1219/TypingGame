package typ_game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TypingTimerTask extends TimerTask{
	//private Timer timer;	   //残り時間計測
    private JLabel TimerLabel; //残り時間表示ラベル
    private String time_str;   //ラベル用文字列
    private int sec;		   //経過時間計測
    private TypingGamePanel TGP;	   //実行中のTyingGameのインスタンス
	
    TypingTimerTask(TypingGamePanel temptgp){
    	 sec=0;
         TGP=temptgp;
         TimerLabel = new JLabel();
         TGP.add(TimerLabel);
    }
    
    public JLabel getTimerLabel(){
    	return this.TimerLabel;
    }
    
    /*public void TimerStop(){
    	timer.stop();
    }*/
    
    public void setText(String str){
    	time_str=str;
    	TimerLabel.setText(time_str);
    }
    /*@Override
	public void actionPerformed(ActionEvent e) {
		 time_str="残り時間は" + (60-sec) + "秒です";
		 TimerLabel.setText(time_str);

		    if (sec >= 60){
		      timer.stop();
		      time_str="";
		      TimerLabel.setText(time_str);
		      try {
				TGP.gameEnd();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		    }
		    else if(sec >=50){
		    	TimerLabel.setForeground(Color.red);
		    	sec++;
		    }
		    else{
		      sec++;
		    }
		
	}
	*/
	@Override
	public void run() {
		time_str="残り時間は" + (60-sec) + "秒です";
		 TimerLabel.setText(time_str);

		    if (sec >= 60){
		      this.cancel();
		      time_str="";
		      TimerLabel.setText(time_str);
		      try {
				TGP.gameEnd();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		    }
		    else if(sec >=50){
		    	TimerLabel.setForeground(Color.red);
		    	sec++;
		    }
		    else{
		      sec++;
		    }
		
	}
}
