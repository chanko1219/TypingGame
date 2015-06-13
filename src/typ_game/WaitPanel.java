package typ_game;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WaitPanel extends JPanel {
	private JLabel WaitLabel;
	private String WaitString;
	
	WaitPanel(){
		WaitLabel = new JLabel();
		WaitString="waiting\n";
		WaitLabel.setText(WaitString);
		this.add(WaitLabel);
	}
	
	public void setText(String str){
		WaitString=str;
		WaitLabel.setText(WaitString);
	}

	public void changeWaitingText(int i){
		switch(i%5){
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
		case 3:
			WaitString="waiting....";
			WaitLabel.setText(WaitString);
			break;
		case 4:
			WaitString="waiting.....";
			WaitLabel.setText(WaitString);
			break;
			
		}
	}
}
