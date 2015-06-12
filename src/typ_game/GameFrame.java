package typ_game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JFrame implements ActionListener, KeyListener {
	private Client cl; 
	private WaitPanel WP;
	private Timer wt;
	private TypingGame GP;
	private int cntTime;
	private JPanel cardPanel;
	private CardLayout layout;
	private int flg;		//現在の画面フラグ(0:待ち画面,1:ゲーム開始画面)
	private ComFlag cmf;
	
	GameFrame(String str1, String str2){
		addKeyListener(this);
		cntTime=0;
		WP= new WaitPanel();
		wt = new Timer(500,this);
		wt.start();
		GP = null;
		cl = new Client(str1, str2);
		try {
			cl.initServer();
			cmf= new ComFlag(cl);
			cmf.start();
			//cl.sendScore(-1);
			GP = new TypingGame(cl,this);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		 cardPanel = new JPanel();
		 layout = new CardLayout();
		 cardPanel.setLayout(layout);
		 flg=0;
		 cardPanel.add(WP, "waiting");
		 cardPanel.add(GP, "typing");
		 getContentPane().add(cardPanel, BorderLayout.CENTER);
	}
	
	public void changePanel(String str){
		layout.show(cardPanel, str);
	}
	
	public void setWaitText(String str){
		WP.setText(str);
	}
	
	public void sendScore(){
		try {
			cl.sendScore(-1);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@Override
	//一定時間経過するとClientのフラグが立ってなくてもゲームを開始する
	public void actionPerformed(ActionEvent e) {
		if(cl.getFlag()>0||cntTime>20){
			wt.stop();
			layout.show(cardPanel,"typing");
			this.flg=1;
		}
		WP.changeWaitingText(cntTime);
		cntTime++;
		//System.out.println(cntTime);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(this.flg==1) GP.keyTyped(e);
	}

}
