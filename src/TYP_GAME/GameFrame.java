package TYP_GAME;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GameFrame(){
		TypingGame tp = new TypingGame();;
		this.getContentPane().add(tp, BorderLayout.CENTER);
	}

}
