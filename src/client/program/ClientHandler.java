package client.program;

import java.io.*;
import java.net.Socket;

import message.Message;

public class ClientHandler implements Runnable{
	//입력 스트림
	public static ObjectInputStream ois;
	//출력 스트림
	public static ObjectOutputStream oos;
	
	ClientTimer timer; 
	Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	
	public void run() {
		System.out.println("클라이언트 핸들러 시작");
		try {
			//입출력 스트림 생성
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"생성");
			
			Message inMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					inMsg = (Message)obj;
					//서버로부터 msg 입력
					int stat = inMsg.getState();
					switch (stat) {
					//str 전송 테스트
					case 1:
					{
						Client.frame.updateLabel(inMsg.getStr());
						break;
					}
					//남은 시간 설정/변경
					case 2:
					{
						int remain = inMsg.getRemain();
						timer = new ClientTimer(remain);
					}
					}
				}
			}
			
			//남은시간 전송
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
