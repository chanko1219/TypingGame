
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


//サーバー側に存在する、クライアントごとの端末
public class IndivServer extends Thread{
//	String name;
	int port;
	int num;
	int point=0;
	private Socket socket;
//	Client(String name,int port,Socket socket){
	IndivServer(int port,Socket socket){
		this.port=port;
		this.num=port-Starter2.PORT-1;/*参加者番号は0番スタートのため*/
		this.socket=socket;
		Starter2.addlog(Serverside.getName(num)+"さんとの接続を個別スレッドに移行");
	}

	public void run(){
		int qnum=0;
		boolean flag=true;
		String ope;
		double temp;
		int counter=1;
		Serverside.Pflag[num]=1;
		try{
			BufferedReader in=
					new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()));
			PrintWriter out=
					new PrintWriter(
							new BufferedWriter(
									new OutputStreamWriter(
											socket.getOutputStream())),true);
			while(flag){
				ope=in.readLine();
				if(ope.equals("GetNum")){
					Starter2.addlog(Serverside.getName(num)+"に問題番号"+qnum+"を出題");
					out.println(qnum+"");
					qnum++;
				}else if(ope.equals("SendScore")){
					temp=Double.parseDouble(in.readLine());

					Starter2.addlog(Serverside.getName(num)+"の"+(counter-1)+"回目のスコアは"+temp);
					Serverside.Pflag[num]++;//スコアを受信したらフラグの値を増やす。この値がみんな同じなら全員分のスコアを送信
					Serverside.addScore(num, temp);
					counter++;
					//Serverside.Pflag[num] ++;//スコアを受信したらフラグの値を増やす。この値がみんな同じなら全員分のスコアを送信
					/*if(temp>=0){
						System.out.println("test");
						Serverside.addScore(num, temp);
						Serverside.Pflag[num] ++;//スコアを受信したらフラグの値を増やす。この値がみんな同じなら全員分のスコアを送信
						System.out.println("Serverside.getflags="+Serverside.getflags()+",Plag"+Serverside.Pflag[num]);
						while(true){
							if(Serverside.getflags()==Serverside.Pflag[num]){
								for(int i=0;i<Serverside.MAX_PARTICIPANT;i++){
									if(Serverside.Pflag[i]>0){
										out.println("SCORE");
										out.println(Serverside.getName(i));
										out.println(Serverside.getScore(i)+"");
									}
								}
								out.println("FLAG");
								out.println(counter+"");
								counter ++;
								break;
							}
						}
					}else{
						while(true){
							//Starter2.addlog(Serverside.Pflag[num]+":"+Serverside.Flag);
							System.out.print("");
							if((Serverside.getflags()==Serverside.Pflag[num])&&Serverside.Flag){
								Starter2.addlog("startflag to "+Serverside.getName(num));
								out.println("FLAG");
								out.println(counter+"");
								counter ++;
								break;
							}
						}
					}*/
				}else if(ope.equals("END")){
					flag=false;
				}
				
				else if(ope.equals("getStartable")){
					if(counter==2){
						System.out.print("");
						if(Serverside.getflags()==Serverside.Pflag[num]&&Serverside.Flag){
							out.println("ok");
						}
						else{
							out.println("yet");
						}
					}
					else{
						System.out.print("");
						if(Serverside.getflags()==Serverside.Pflag[num]){
							out.println("ok");
						}
						else{
							out.println("yet");
						}
					}
				}
				else if(ope.equals("comScore")){
					for(int i=0;i<Serverside.MAX_PARTICIPANT;i++){
						if(Serverside.Pflag[i]>0){
							out.println("SCORE");
							out.println(Serverside.getName(i));
							out.println(Serverside.getScore(i)+"");
						}
					}
					out.println("all");
				}
				else if(ope.equals("")){
					
				}

			}
		}catch(IOException e){
			e.printStackTrace();
			Starter2.addlog(Serverside.getName(num)+"さんから強制切断されました");
			Serverside.Pflag[num]=-1;
		}finally{
			Starter2.addlog(Serverside.getName(num)+"さんとの接続を切断しました");
			Serverside.Pflag[num]=-1;
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
