
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverside extends Thread {
	static int MAX_PARTICIPANT=10;
	private static int participant=0;
	private static String[] name=new String[MAX_PARTICIPANT];
	private static double[] score=new double[MAX_PARTICIPANT];
	public static boolean Flag;
	public static int[] Pflag=new int[MAX_PARTICIPANT];//参加状態-1未参加、正の数参加,-2切断
	
	Serverside(){
		for(int i=0;i<MAX_PARTICIPANT;i++){
			Pflag[i]=0;
		}
	}
	public static String getName(int num){
		return name[num];
	}
	public static double getScore(int num){
		return score[num];
	}
	public static void addScore(int num,double s){
		score[num]+=s;
	}
	public static int getflags(){
		int temp=0;
		int i;
		int j=0;//アクティブユーザー数
		for(i=0;i<MAX_PARTICIPANT;i++){
			if(Pflag[i]>0){
				temp+=Pflag[i];
				j++;
			}
		}
		return temp/j;
	}
//	public static void main(String[] args)throws IOException{
//		server();
//	}
//開始までのサーバー
	public void run(){
		int PORT=Starter2.PORT;
		int cliport=0;
		IndivServer[] client=new IndivServer[MAX_PARTICIPANT];
		ServerSocket s=null;
		try {
			s = new ServerSocket(PORT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Socket socket= null;
		try{
			while(!Flag){
				socket=s.accept();
				if(Flag){
					break;
				}
				BufferedReader in=
						new BufferedReader(
								new InputStreamReader(
										socket.getInputStream()));
				PrintWriter out=
						new PrintWriter(
								new BufferedWriter(
										new OutputStreamWriter(
												socket.getOutputStream())),true);
				name[participant]=in.readLine();
				Starter2.addlog(name[participant]+"さんが参加しました。");
				cliport=PORT+participant+1;
				out.println(cliport);
				ServerSocket l=new ServerSocket(cliport);
				client[participant]=new IndivServer(cliport,l.accept());
				client[participant].start();
				participant ++;
				if(participant==MAX_PARTICIPANT||Flag){
					break;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			Starter2.addlog("受付終了");
			try {
				s.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
