package typ_game;

import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.*;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GameFrame(String str1, String str2){
		TypingGame tp = new TypingGame(str1,str2);
		this.getContentPane().add(tp, BorderLayout.CENTER);
		
	}

}
