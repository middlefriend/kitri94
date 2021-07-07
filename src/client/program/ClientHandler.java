package client.program;

import java.io.*;
import java.net.Socket;

import client.frame.TestFrameClient;
import message.Message;

public class ClientHandler implements Runnable{
	//입력 스트림
	public ObjectInputStream ois;
	//출력 스트림
	public ObjectOutputStream oos;
	
	TestFrameClient frame;
	Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		//로그인창 열기
		frame= new TestFrameClient(this);
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
					frame.updateLabel(inMsg.getStr());
				}
			}
			
			//남은시간 전송
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
