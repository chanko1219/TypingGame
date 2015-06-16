package typ_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class ResultPanel extends JPanel implements ActionListener {
	//private Client clt;
	private GameFrame GF;
	private int ptc;			//ゲームの参加者数
	private int myNum;			//自分の参加者番号
	private double[] Scores;
	private String[] Names;
	private double[] crr;
	private long[] sum_typ;
	private String[] resultText;
	private int[] rank;
	private JButton nextGameButton;
	private DecimalFormat df; //スコア表示の小数桁数を設定
	private Font font;
	
	ResultPanel(GameFrame tempgf){
		this.GF=tempgf;
		this.ptc=0;
		this.Scores=null;
		this.Names=null;
		this.crr=null;
		this.sum_typ=null;
		this.nextGameButton = new JButton("Next Game");
		df= new DecimalFormat("0.00");
		font = new Font("SansSerif",Font.BOLD,16);
		nextGameButton.addActionListener(this);
		this.add(nextGameButton);
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
	
	public void setCrr(){
		this.crr=GF.clt.getCrr();
	}
	
	public void setSum(){
		this.sum_typ=GF.clt.getSum();
	}
	
	public void setMyNum(){
		this.myNum=GF.clt.getMyNum();
	}
	
	public void setRank(){
		rank = new int[ptc];
		int i;
		int j;
		int temprank;
		double temp;
		double[] tempscore=new double[ptc];
		for(i=0;i<ptc;i++){
			tempscore[i]=Scores[i];
			rank[i]=i;
		}
		for(i=1;i<ptc;i++){
			for(j=i;j>0;j--){
				if(tempscore[j-1]<=tempscore[j]){
					temp=tempscore[j-1];
					tempscore[j-1]=tempscore[j];
					tempscore[j]=temp;
					temprank=rank[j-1];
					rank[j-1]=rank[j];
					rank[j]=temprank;
				}
				else{
					break;
				}
			}
		}
	}
	@Override
    public void paintComponent(Graphics g){
		super.paintComponent(g);
        g.setFont(font);
        g.setColor(Color.black);
        
        int i;
        resultText= new String[ptc];
        for(i=0;i<ptc;i++){
			resultText[i]=Names[rank[i]]+":"+df.format(Scores[rank[i]])+":"+df.format(crr[rank[i]])+":"+sum_typ[rank[i]];
			if(rank[i]==myNum){
				g.setColor(Color.red);
			}
			else{
				g.setColor(Color.black);
			}
			g.drawString(resultText[i], 250, 60+20*i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GF.changePanel("typing");
	}
	
	
	
}
