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
	private JPanel WaitPanel;
	private JLabel WaitLabel;
	private String WaitString;
	private Timer wt;
	private TypingGame tp;
	private int cntTime;
	private JPanel cardPanel;
	private CardLayout layout;
	private int flg;		//現在の画面フラグ(0だと待ち画面,1だとゲーム開始画面)
	
	GameFrame(String str1, String str2){
		addKeyListener(this);
		cntTime=0;
		WaitPanel= new JPanel();
		WaitLabel = new JLabel();
		WaitString="waiting";
		WaitLabel.setText(WaitString);
		WaitPanel.add(WaitLabel);
		wt = new Timer(500,this);
		wt.start();
		tp = null;
		cl = new Client(str1, str2);
		try {
			cl.initServer();
			tp = new TypingGame(cl);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		 cardPanel = new JPanel();
		 layout = new CardLayout();
		 cardPanel.setLayout(layout);
		 flg=0;
		 cardPanel.add(WaitPanel, "waiting");
		 cardPanel.add(tp, "typing");
		 getContentPane().add(cardPanel, BorderLayout.CENTER);
	}
	
	@Override
	//一定時間経過するとClientのフラグが立ってなくてもゲームを開始する
	public void actionPerformed(ActionEvent e) {
		if(cl.getFlag()==1||cntTime>60){
			wt.stop();
			layout.next(cardPanel);
			flg=1;
		}
		switch(cntTime%3){
		case 0:
			WaitString="waiting.";
			WaitLabel.setText(WaitString);
			break;
		case 1:
			WaitString="waiting..";
			WaitLabel.setText(WaitString);
			break;
		case 2:
			WaitString="waiting...";
			WaitLabel.setText(WaitString);
			break;
			
		}
		cntTime++;
		System.out.println(cntTime);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(flg==1) tp.keyTyped(e);
	}

}
