package typ_game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TypingTimer implements ActionListener {
	private Timer timer;	   //残り時間計測
    private JLabel TimerLabel; //残り時間表示ラベル
    private String time_str;   //ラベル用文字列
    private int sec;		   //経過時間計測
    private TypingGame TG;	   //実行中のTyingGameのインスタンス
	
    TypingTimer(TypingGame tg){
    	 sec=0;
    	 timer=new Timer(1000,this);
    	 timer.start();
         TimerLabel = new JLabel();
         TG=tg;
    }
    
    public JLabel getTimerLabel(){
    	return this.TimerLabel;
    }
    
    public void TimerStop(){
    	timer.stop();
    }
    
    public void setText(String str){
    	time_str=str;
    	TimerLabel.setText(time_str);
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		 time_str="残り時間は" + (60-sec) + "秒です";
		 TimerLabel.setText(time_str);

		    if (sec >= 60){
		      timer.stop();
		      time_str="";
		      TimerLabel.setText(time_str);
		      try {
				TG.gameEnd();
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
