package typ_game;


import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Timer;
import java.io.*;

import javax.swing.text.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TypingGamePanel extends JPanel implements KeyListener{
    private final int WAITING = 0;     //ゲーム状態フラグ定数（タイトル画面時）
    private final int PLAYING = 1;     //ゲーム状態フラグ定数（ゲーム中）
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
    private FontMetrics fm;	   //文字幅取得r
    private int CF;		   	   //正解判定フラグ
    private Timer timer;		//タイマー
    private DecimalFormat df; //スコア表示の小数桁数を設定
    public GameFrame GF;		//ゲーム画面
   
    TypingGamePanel(GameFrame gf) throws IOException{
    	        gameConfig = WAITING;
    	        this.GF=gf;
    	        df= new DecimalFormat("0.00");
    }
    
    TypingGamePanel(){
    	
    }
    public void gameStart(){
        qetNum = 0;
        CF = 0;
        bufNum=0;
        gameConfig = PLAYING;
        cal = Calendar.getInstance(); cnt=0;
        DKey = new DispKey(GF.clt.getQetNum());
        DKey.setQuestion(bufNum);
        str_len=DKey.getCharNumMax();
        sum_len=10;
        sum_str_len=0;
        startTime = cal.getTimeInMillis();
		timer = new Timer();
		timer.schedule(new TypingTimerTask(this),0, 1000);
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
        gameConfig = WAITING;
        sumTime = (long)(endTime - startTime);
        if(cnt!=0){
        	crr=(double)sum_str_len/cnt;
        	score=(double)sum_str_len/sumTime*1000*60*crr*crr*crr;
        }
        else{
        	score=0;
        }
        repaint();
        GF.clt.sendScore(score);
        GF.setWaitText("Waiting result");	//各プレイヤー（クライアント）のスコアが揃うまで待つ
        GF.changePanel("waiting");
        while(!GF.clt.getStartable());{	
        }
        GF.clt.comScore();
        GF.setResultPanel();
        GF.changePanel("result");
        
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
        		//timer.stop();
   		        //tt.setText("");
        	}
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        
    }
    public void keyReleased(KeyEvent e){
    
    }
    
    public void keyTyped(KeyEvent e){
    	switch(gameConfig){
        case WAITING:
        	  if(e.getKeyChar()==' '){
                  this.gameStart();
              }
              break;
            
       case PLAYING:
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
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
        g.setFont(new Font("Dialog", Font.PLAIN, 20));
        g.setColor(Color.black);
        
        switch(gameConfig){
            case WAITING:
            	g.drawString("Start SpaceKey", 3, 20);
                break;
            case PLAYING:
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
