package typ_game;

import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GameFrame(){
		TypingGame tp = new TypingGame();
		this.getContentPane().add(tp, BorderLayout.CENTER);
		
	}

}
