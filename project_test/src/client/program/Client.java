package client.program;

import java.io.*;
import java.net.Socket;

import client.frame.JoinFrame;
import client.frame.LoginFrame;

public class Client {
	public static LoginFrame frame;
	
	public static void main(String[] args) {
		Socket socket = null; 
		//서버 ip
		final String serverIp = "localhost";
		//서버 port
		final int serverPort = 7777;
		frame = new LoginFrame();
		LoginFrame login = new LoginFrame();
		try {
			//서버 소켓 연결
			socket = new Socket(serverIp,serverPort);
			System.out.println("클라이언트 시작");
			
			//소켓 통신 스레드
			Thread clientHandler= new Thread(new ClientHandler(socket, frame));
			clientHandler.start();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
