package typ_game;


import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.io.*;

import javax.swing.text.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TypingGame extends JPanel implements KeyListener, ActionListener{
    private final int GAME_CONFIG_WAITING = 0;     //ゲーム状態フラグ定数（タイトル画面時）
    private final int GAME_CONFIG_PLAYING = 1;     //ゲーム状態フラグ定数（ゲーム中）
    private DispKey DKey;      //タイピング問題管理クラス
    private int gameConfig;    //ゲーム状態フラグ
    private int qetNum;        //問題番号
    private int sum_len;       //ハイライト表示位置
    private int bufNum;		   //問題配列の添字
    private double score;	   //ゲームの得点	
    private Calendar cal;      //カレンダークラス
    private long str_len;	   //文字列表示長さ
    private long sum_str_len;  //正解文字数の合計
    private long cnt;		   //カウンタ
    private long startTime;    //ゲーム開始時間
    private long endTime;      //ゲーム終了時間
    private String demoStr;    //経過時間文字列
    private FontMetrics fm;	   //文字幅取得
    private int CF;		   	   //正解判定フラグ
    private Timer timer;	   //残り時間計測
    private JLabel TimerLabel; //残り時間表示ラベル
    private String time_str;   //ラベル用文字列
    private int sec;		   //経過時間計測
    private DecimalFormat df; //スコア表示の小数桁数を設定
   
    
    TypingGame(){
    	        gameConfig = GAME_CONFIG_WAITING;
    	        addKeyListener(this);
    	        setFocusable(true);
    	        demoStr = "";
    	        df= new DecimalFormat("0.00");
    }
    
    public void gameStart(){
        qetNum = 0;
        CF = 0;
        bufNum=0;
        gameConfig = GAME_CONFIG_PLAYING;
        cal = Calendar.getInstance(); cnt=0;
        DKey = new DispKey(bufNum);
        str_len=DKey.getCharNumMax();
        sum_str_len=0;
        startTime = cal.getTimeInMillis();
        sum_len=10;
        timer =new Timer(1000,this);
        timer.start();
        sec=0;
        TimerLabel = new JLabel();
        this.add(TimerLabel);
    }
    public void gameContinue(){
        qetNum = 0;
        CF = 0;
        DKey = new DispKey(bufNum);
        str_len=DKey.getCharNumMax();
        sum_len=10;
    }
    public void gameEnd(){
        long sumTime;
        double crr;
        cal = Calendar.getInstance();
        endTime = cal.getTimeInMillis();
        gameConfig = GAME_CONFIG_WAITING;
        sumTime = (long)(endTime - startTime);
        if(cnt!=0){
        crr=(double)sum_str_len/cnt;
        score=(double)sum_str_len/sumTime*1000*60*crr*crr*crr;
        System.out.println(sum_str_len+","+cnt+".,"+crr+","+sumTime);
        }
        else{
        	score=0;
        }
        demoStr ="score : "+df.format(score);
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e){
        
    }
    public void keyReleased(KeyEvent e){
    
    }
    
    public void keyTyped(KeyEvent e){
    	switch(gameConfig){
        case GAME_CONFIG_WAITING:
        	  if(e.getKeyChar()==' '){
                  this.gameStart();
              }
              break;
            
       case GAME_CONFIG_PLAYING:
    	    cnt++;
   			CF=DKey.compareToInpKey(e.getKeyChar(), qetNum);
   			if(CF == 0){
   				this.nextChar();
   			}
   		    break;
    	}
    	repaint();
    }
    private void nextChar(){
        if(qetNum < str_len - 1){
            qetNum += 1;
        }else{
        	if(bufNum<30){
        		bufNum++;
        		gameContinue();
        	}
        	else{
        		this.gameEnd();
        		
        		 timer.stop();
   		      time_str="";
   		      TimerLabel.setText(time_str);
        	}
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
        g.setFont(new Font("Dialog", Font.PLAIN, 20));
        g.setColor(Color.black);
        
        switch(gameConfig){
            case GAME_CONFIG_WAITING:
            	g.drawString("Start SpaceKey", 3, 20);
                g.drawString(demoStr, 3, 37);
                break;
            case GAME_CONFIG_PLAYING:
            	String QetString = DKey.getQetString();
            	fm = g.getFontMetrics();
            	int fw=fm.charWidth(QetString.charAt(qetNum));
            	g.setColor(Color.red);
            	
            	if(CF==0){            	
            		g.fillRect(sum_len, 20, fw, 20);
            		sum_len+=fw;
            		sum_str_len++;
            		CF=-1;
            	}
            	else{
            		g.fillRect(sum_len-fw, 20, fw, 20);
            	}
            	g.setColor(Color.black);
            	g.drawString(QetString, 10, 40);
            	
                break;
        }
       
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		 time_str="残り時間は" + (60-sec) + "秒です";
		 TimerLabel.setText(time_str);

		    if (sec >= 60){
		      timer.stop();
		      time_str="";
		      TimerLabel.setText(time_str);
		      this.gameEnd();
		    }
		    else if(sec >=49){
		    	TimerLabel.setForeground(Color.red);
		    	sec++;
		    }
		    else{
		      sec++;
		    }
		
	}
}
