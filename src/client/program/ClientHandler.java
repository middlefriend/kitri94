package client.program;

import java.io.*;
import java.net.Socket;

import client.frame.JoinFrame;
import message.Message;

public class ClientHandler implements Runnable{
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;
	
	public JoinFrame joinFrame;
	ClientTimer timer; 
	Socket socket;

	public ClientHandler(Socket socket,JoinFrame joinFrame) {
		this.socket = socket;
		this.joinFrame = joinFrame;
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
							login.loginResult(inMsg);
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
