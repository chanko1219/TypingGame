
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Starter2 {
	private JFrame frame;
	private JRadioButton rdbtnSelClient;
	private JRadioButton rdbtnSolo;
	private static TextArea log;
	private final Action action = new SwingAction();
	private JTextField PORTnum;
	public static int PORT;
	private int startflag=0;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void addlog(String str){
		log.append(str+"\n");
		System.out.println(str);
	}
	public static void clearlog(){
		log.setText("");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Starter2 window = new Starter2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Starter2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CloseWindowButton cBtn = new CloseWindowButton("終了");
		frame.getContentPane().setLayout(null);

		lblNewLabel = new JLabel("PORT");
		lblNewLabel.setBounds(0, 0, 277, 66);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		PORTnum = new JTextField();
		PORTnum.setBounds(263, 0, 253, 66);
		PORTnum.setText("8080");
		frame.getContentPane().add(PORTnum);
		PORTnum.setColumns(10);


		JButton btnStart = new JButton("start");
		btnStart.setBounds(514, 0, 267, 66);
		btnStart.setAction(action);
		frame.getContentPane().add(btnStart);

		log = new TextArea();
		log.setFont(new Font("Dialog", Font.PLAIN, 20));
		log.setBounds(0, 72, 781, 472);
		frame.getContentPane().add(log);

		try {
			addlog("ホスト名:"+InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private class SwingAction extends AbstractAction {//スタートボタン
		public SwingAction() {
			putValue(NAME, "Start");
			putValue(SHORT_DESCRIPTION, "Server or Client start");
		}
		public void actionPerformed(ActionEvent e) {
//			log.setEnabled(false);
			PORT=Integer.parseInt(PORTnum.getText());
			System.out.println("stbtnispushed"+startflag);
			if(startflag==1){
				Serverside.Flag=true;
				halt();
				System.out.println("operation for game start from Starter2");
			}else{
				addlog("マッチングサーバー起動");
				putValue(NAME,"start game");
				putValue(SHORT_DESCRIPTION, "start game");
				startflag=1;
				Serverside server=new Serverside();
				server.start();
			}
		}
	}

	   public static void halt() {
	       try {
	        //accept()を回すために自分自身に接続
	        Socket socket = new Socket("127.0.0.1", PORT);
	        //すぐに閉じる
	        socket.close();
	      } catch (IOException e) {
	        e.printStackTrace(System.out);
	      }
	    }
}

