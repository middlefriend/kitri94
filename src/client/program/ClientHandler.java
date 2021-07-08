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
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"스트림 생성");
			
			Message inMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					inMsg = (Message)obj;
					int stat = inMsg.getState();
					switch (stat) {
					case 1:
					{
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
