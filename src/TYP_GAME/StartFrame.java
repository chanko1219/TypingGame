package TYP_GAME;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class StartFrame extends JFrame implements ActionListener {
	
	StartFrame(){
	    JButton stBtn = new JButton("開始");
	    stBtn.addActionListener(this);
	    CloseWindowButton cBtn = new CloseWindowButton("終了");
	    JPanel p = new JPanel();
	    p.add(stBtn);
	    p.add(cBtn);

	    getContentPane().add(p,BorderLayout.CENTER);
	   
 }

	@Override
	public void actionPerformed(ActionEvent e) {
		GameMain.Frame.setVisible(false);
		GameFrame GF = new GameFrame();
		GF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//右上の×印を押した時の動作
		GF.setBounds(500, 100, 500, 300);					//生成したGameFrameeのインスタンスであるGFを画面に出力
		GF.setTitle("TypingGame");
		GF.setVisible(true); 
		
	}

}
