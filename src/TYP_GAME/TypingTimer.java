package typ_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TypingTimer extends JLabel implements ActionListener{
	private Timer timer;
	public JLabel label;
	//public JPanel labelPanel;
	int sec;
	  
	TypingTimer(){
		 sec = 0;
		 timer = new Timer(1000 , this);
		 timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 this.setText(60-sec + " sec");

		    if (sec >= 60){
		      timer.stop();
		    }else{
		      sec++;
		    }
	}

}
