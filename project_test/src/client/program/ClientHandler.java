package client.program;

import java.io.*;
import java.net.Socket;

<<<<<<< HEAD
import client.frame.*;
import message.Message;

public class ClientHandler implements Runnable{
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;
	
	LoginFrame login;

	ClientTimer timer; 
	Socket socket;

	public ClientHandler(Socket socket,LoginFrame login) {
		this.socket = socket;
		this.login = login;
	}

	
	public void run() {
		try {
			//입출력 스트림 생성
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"스트림 생성");
			
			Message inMsg = null;
			Object obj=null;
			
			//서버로부터 메세지 수신
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					inMsg = (Message)obj;
					int stat = inMsg.getState();
					switch (stat) {
						//ID 중복확인
						case 1: {
							login.jFrame.idCheckResult(inMsg.getResult());
							break;
						}
						//회원가입
						case 2: {
							login.jFrame.joinCheck(inMsg);
							break;
						}
						//로그인
						case 3: {
							login.loginResult(inMsg.getResult());
							break;
						}
						//좌석이동
						case 4: {
							login.cFrame.changeSeatResult();
							break;
						}
						//시간충전
						case 5: {
							login.pFrame.purchaseCheckResult();
							break;
						}
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
=======
import client.frame.JoinFrame;
import client.frame.LoginFrame;
import message.Message;

public class ClientHandler implements Runnable {
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;

	public LoginFrame login;
	ClientTimer timer;
	Socket socket;

	public ClientHandler(Socket socket, LoginFrame login) {
		this.socket = socket;
		this.login = login;
	}

	public void run() {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois + "," + oos + "스트림 생성");

			Message inMsg = null;
			Object obj = null;

			while ((obj = ois.readObject()) != null) {
				if (obj instanceof Message) {
					inMsg = (Message) obj;
					int stat = inMsg.getState();
					switch (stat) {
						case 1: {
							login.jFrame.idCheckResult(inMsg.getResult());//ID 중복확인
						}
						case 2: {
							login.jFrame.joinCheck(inMsg);//회원가입
						}
						case 3: {
							login.loginResult(inMsg);//로그인
						}
						case 4: {
							login.cFrame.changeSeatResult();//좌석이동
						}
						case 5: {
							login.pFrame.purchaseCheckResult();//시간충전
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
>>>>>>> refs/remotes/origin/경하
