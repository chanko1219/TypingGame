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

public class TypingGame extends JPanel implements KeyListener{
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
    private long cnt;		   //入力回数カウンタ
    private long startTime;    //ゲーム開始時間
    private long endTime;      //ゲーム終了時間
    private String demoStr;    //経過時間文字列
    private FontMetrics fm;	   //文字幅取得r
    private int CF;		   	   //正解判定フラグ
    private TypingTimer tt;	   //タイピングゲームのタイマー	
    private DecimalFormat df; //スコア表示の小数桁数を設定
    private Client clt;		   //通信用のクライアント
    private GameFrame GF;		//ゲーム画面
   
    
    TypingGame(Client tempclt, GameFrame gf) throws IOException{
    	        gameConfig = GAME_CONFIG_WAITING;
    	        this.clt= tempclt;
    	        this.GF=gf;
    	        demoStr = "";
    	        df= new DecimalFormat("0.00");
    }
    
    TypingGame(){
    	
    }
    public void gameStart(){
        qetNum = 0;
        CF = 0;
        bufNum=0;
        gameConfig = GAME_CONFIG_PLAYING;
        cal = Calendar.getInstance(); cnt=0;
        DKey = new DispKey(clt.getQetNum());
        DKey.setQuestion(bufNum);
        str_len=DKey.getCharNumMax();
        sum_len=10;
        sum_str_len=0;
        startTime = cal.getTimeInMillis();
        tt = new TypingTimer(this);
        this.add(tt.getTimerLabel());
    }
    
    public void gameContinue(){
        qetNum = 0;
        CF = 0;
        DKey.setQuestion(bufNum);
        str_len=DKey.getCharNumMax();
        sum_len=10;
    }
    
    public void gameEnd() throws IOException{
        long sumTime;
        double crr;
        cal = Calendar.getInstance();
        endTime = cal.getTimeInMillis();
        gameConfig = GAME_CONFIG_WAITING;
        sumTime = (long)(endTime - startTime);
        if(cnt!=0){
        	crr=(double)sum_str_len/cnt;
        	score=(double)sum_str_len/sumTime*1000*60*crr*crr*crr;
        }
        else{
        	score=0;
        }
        demoStr ="score : "+df.format(score);
        GF.setWaitText("Waiting result");
        GF.changePanel("waiting");
        repaint();
        //clt.sendScore(score);		//各プレイヤー（クライアント）のスコアが揃うまで待つ
        
    }
    @Override
    public void keyPressed(KeyEvent e){
        System.out.println("test");
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
   				try {
					this.nextChar();
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
   			}
   		    break;
    	}
    	repaint();
    }
    private void nextChar() throws IOException{
        if(qetNum < str_len - 1){
            qetNum += 1;
        }else{
        	if(bufNum<30){
        		bufNum++;
        		gameContinue();
        	}
        	else{
        		this.gameEnd();
        		tt.TimerStop();
   		        tt.setText("");
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
            	else if(CF==-1){
            		g.fillRect(sum_len-fw, 20, fw, 20);
            	}
            	else{
            		g.fillRect(sum_len-fw, 20, fw, 20);
            		g.drawString("miss!", sum_len-fw, 60);
            	}
            	g.setColor(Color.black);
            	g.drawString(QetString, 10, 40);
            	
                break;
        }
       
    }

}
