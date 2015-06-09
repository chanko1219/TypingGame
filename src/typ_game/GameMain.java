package typ_game;
import javax.swing.JFrame;

import typ_game.StartFrame;


public class GameMain extends JFrame {
	 static StartFrame Frame;
		public static void main(String[] args){
		    Frame = new StartFrame();		//初期画面にボタンを配置するためにStartFrameのインスタンスを生成
		    Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//右上の×印を押した時の動作
		    Frame.setBounds(500, 100, 600, 300);					//生成したStartFrameのインスタンスであるFrameを画面に出力
		    Frame.setTitle("TypingGame");
		    Frame.setVisible(true);
		    
		  }

}
