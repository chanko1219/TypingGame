package typ_game;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class StartFrame extends JFrame implements ActionListener {
	
	private JTextField c_field;
	private JTextField s_field;
	private String c_name;
	private String s_name;
	
	StartFrame(){
	    JButton stBtn = new JButton("開始");
	    stBtn.addActionListener(this);
	    CloseWindowButton cBtn = new CloseWindowButton("終了");
	    JPanel p = new JPanel();
	    c_field = new JTextField("Input Client Name", 20);
	    s_field = new JTextField("localhost", 20);
	    p.add(c_field);
	    p.add(s_field);
	    p.add(stBtn);
	    p.add(cBtn);
	    getContentPane().add(p,BorderLayout.CENTER);
	}
	
	//開始ボタンを押したときの処理
	@Override
	public void actionPerformed(ActionEvent e) {
		c_name=c_field.getText();
		s_name=s_field.getText();
		
		GameMain.Frame.setVisible(false);
		GameFrame GF = new GameFrame(c_name,s_name);
		GF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//右上の×印を押した時の動作
		GF.setBounds(500, 100, 600, 300);					//生成したGameFrameeのインスタンスであるGFを画面に出力
		GF.setTitle("TypingGame"+"("+c_name+")");
		GF.setVisible(true); 
	}

}
