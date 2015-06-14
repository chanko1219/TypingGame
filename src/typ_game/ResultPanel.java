package typ_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel implements ActionListener {
	//private Client clt;
	private GameFrame GF;
	private int ptc;
	private double[] Scores;
	private String[] Names;
	private JLabel resultLabel;
	private String resultText;
	private JButton nextGameButton;
	
	ResultPanel(GameFrame tempgf){
		//this.clt=tempclt;
		this.GF=tempgf;
		this.ptc=1;
		this.Scores=null;
		this.Names=null;
		this.resultLabel=new JLabel();
		this.resultText="";
		this.nextGameButton = new JButton("Next Game");
		nextGameButton.addActionListener(this);
	}
	
	public void setParticipant(){
		this.ptc=GF.clt.getParticipant();
	}
	
	public void setScores(){
		this.Scores=GF.clt.getScore();
	}
	
	public void setNames(){
		this.Names=GF.clt.getNames();
	}
	
	public void setResultText(){
		int i;
		resultText="<html>";
		for(i=0;i<ptc;i++){
			resultText+=Names[i]+":"+Scores[i]+"<br>";
		}
		resultText+="<html>";
		resultLabel.setText(resultText);
		this.add(resultLabel);
		this.add(nextGameButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GF.changePanel("typing");
	}
	
	
	
}
