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
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener, KeyListener {
	public Client clt; 
	private WaitPanel WP;
	private TypingGamePanel TGP;
	private ResultPanel RP;
	private Timer timer;
	private JPanel cardPanel;
	private CardLayout layout;
	private int flg;		//現在の画面フラグ(0:待ち画面,1:ゲーム開始画面,2:結果表示画面)
	
	GameFrame(String str1, String str2){
		addKeyListener(this);
		WP= new WaitPanel();
		 flg=0;
		TGP = null;
		clt = new Client(str1, str2);
		RP= new ResultPanel(this);
		try {
			clt.initServer();
			timer = new Timer();
			timer.schedule(new WaitTask(this), 500, 500);
			clt.sendScore(-1);
			TGP = new TypingGamePanel(this);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		 cardPanel = new JPanel();
		 layout = new CardLayout();
		 cardPanel.setLayout(layout);
		 cardPanel.add(WP, "waiting");
		 cardPanel.add(RP,"result");
		 cardPanel.add(TGP, "typing");
		 getContentPane().add(cardPanel, BorderLayout.CENTER);
	}
	
	public void changePanel(String str){
		layout.show(cardPanel, str);
		switch(str){
			case "waiting":
				this.flg=0;
				break;
			case "typing":
				this.flg=1;
				setFocusable(true);
				break;
			case "result":
				this.flg=2;
				break;
		}
	}
	
	public void setWaitText(String str){
		WP.setText(str);
	}
	
	public void sendScore(){
		try {
			clt.sendScore(-1);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void setResultPanel(){
		RP.setParticipant();
		RP.setScores();
		RP.setNames();
		RP.setResultText();
	}
	
	public void changeWaitingText(int i){
		WP.changeWaitingText(i);
	}
	@Override
	//一定時間経過するとClientのフラグが立ってなくてもゲームを開始する
	public void actionPerformed(ActionEvent e) {
		switch(flg){
		case 0:
			break;
		case 1:
			break;
		case 2:
			RP.actionPerformed(e);
			break;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		switch(flg){
		case 0:
			break;
		case 1:
			TGP.keyTyped(e);
			break;
		case 2:
			break;
		}
	}

}
