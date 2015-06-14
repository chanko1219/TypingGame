package typ_game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private String c_name="localhost";		//クライアント名
	private String s_name="hoge";		//サーバー名
	private int qetNum;		//問題番号
	private int InitPORT=8080; //初期設定のポート番号
	private int port;
	private Socket s=null;
	private BufferedReader in;
	private PrintWriter out;
	private int flg;	//-2:スコア送信直後に通信切断 -1:スコアを表示せずにゲーム開始 正の数:スコア表示
	private final int MAX_PARTICIPANT=10;
	private double[] Scores= new double[MAX_PARTICIPANT];
	private String[] Names=new String[MAX_PARTICIPANT];
	private InetAddress add;
	private int ptc; //ゲームの実際の参加者
	
	Client(String str1, String str2){
		this.c_name=str1;
		this.s_name=str2;
		this.qetNum=0;
		this.flg=0;
	}
	//サーバーとの接続確立を行いqet_numにサーバーから得た問題番号を格納
	public void initServer()throws IOException{
		add=InetAddress.getByName(s_name);
		Socket socket=new Socket(add,InitPORT);
		try{
			in=
					new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()));
			out=
					new PrintWriter(
							new BufferedWriter(
									new OutputStreamWriter(
											socket.getOutputStream())),true);
			out.println(c_name);
			port=Integer.parseInt(in.readLine());
			System.out.println("port="+port);
		}finally{
			socket.close();
		}
		s=new Socket(add,port);
		in=
				new BufferedReader(
						new InputStreamReader(
								s.getInputStream()));
		out=
				new PrintWriter(
						new BufferedWriter(
								new OutputStreamWriter(
										s.getOutputStream())),true);
	}

	//問題番号を取得する
	public int getQetNum(){
		out.println("GetNum");
		try {
			qetNum= Integer.parseInt(in.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//フラグ初期化
		flg=-2;
		return qetNum;
	}


	//サーバーに得点を送信
	public void sendScore(double score) throws IOException{
		out.println("SendScore");
		out.println(score+"");
		/*int i=0;
		String ope="";
		ope=in.readLine();
		while(ope.equals("SCORE")){
			Names[i]=in.readLine();
			Scores[i]=Double.parseDouble(in.readLine());
			i++;
			ope=in.readLine();
		}
		this.ptc=i;		//参加者数を記録
		if(ope.equals("FLAG")){
			flg=Integer.parseInt(in.readLine());
		}
		if(flg==-2){
			//この関数を呼び出した後,TypginGame.javaの方でゲームの終了処理を入れる
			endConnection();
		}else if(flg==1){//初回

		}else if(flg>1){
			//暫定的なスコア表示の何か
			for(int j=0;j<i;j++){
				System.out.println(Names[j]+":"+Scores[j]);
			}
		}*/
		//System.out.println("end,sendScore");
	}
	public void endConnection(){
		out.println("END");
		try {
			s.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	//サーバーに参加者の得点を問い合わせる
	public void comScore() throws IOException{
		out.println("comScore");
		int i=0;
		String ope="";
		ope=in.readLine();
		System.out.println("test");
		while(ope.equals("SCORE")){
			Names[i]=in.readLine();
			Scores[i]=Double.parseDouble(in.readLine());
			i++;
			ope=in.readLine();
		}
		
		this.ptc=i;		//参加者数を記録
	}
	
	//参加者人数を取得（comScore実行後）
    public int getParticipant(){
    	return this.ptc;
    }
    
    //参加者全員の得点を取得
    public double[] getScore(){
    	return this.Scores;
    }
  
    //参加者全員の名前を取得
    public String[] getNames(){
    	return this.Names;
    }
    
	//ゲームが開始できるかサーバーに確認（得点要求できるかも確認）
	public boolean getStartable() throws IOException{
		out.println("getStartable");
		String ope="";
		ope=in.readLine();
		boolean temp=(ope.equals("ok"));
		return temp;
	}
}
