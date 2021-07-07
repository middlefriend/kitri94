package server.program;

import java.io.*;
import java.net.Socket;

import message.Message;

public class ServerHandler implements Runnable{
	//입력 스트림
	public ObjectInputStream ois;
	//출력 스트림
	public ObjectOutputStream oos;
	
	Socket socket;
	int seat;
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("서버 핸들러 시작-"+socket);
		try {
			//입력 스트림 생성
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"생성");
			
			Message inMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					inMsg = (Message)obj;
					//클라이언트로 부터 입력
					System.out.println(inMsg);
					int stat = inMsg.getState();
					switch(stat) {
					case 1:
					{
						Server.frame.updateLabel(inMsg.getStr());
					}
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
