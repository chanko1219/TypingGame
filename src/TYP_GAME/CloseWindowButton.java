package typ_game;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class CloseWindowButton extends JButton implements ActionListener{	//画面を閉じるためのボタンを生成するクラス
	CloseWindowButton(){
		super("閉じる");
		addActionListener(this);
	}

	CloseWindowButton(String s){
		super(s);
		addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {		//actionPerformedをオーバーライドすることでボタンに画面を閉じる機能をもたせる
		Component c = (Component)e.getSource();
		Window w = SwingUtilities.getWindowAncestor(c);
		w.dispose();
	}

}
