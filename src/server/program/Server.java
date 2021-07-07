package server.program;

import java.io.*;
import java.net.*;
import java.util.HashMap;

import client.frame.TestFrameClient;
import message.Message;
import server.frame.TestFrameServer;

public class Server {
	
	static final int seatNum = 20;
	public static HashMap<Socket,Integer> socketList = new HashMap<Socket,Integer>(seatNum);
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		//서버 port
		final int serverPort = 7777;
		
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("서버 시작");
			while(true) {
				//서버 소켓 연결
				Socket socket = serverSocket.accept();
				socketList.put(socket,-1);
				System.out.println("클라이언트 접속 완료 -("+socketList.size()+")");
				
				
				//출력 스레드 : 로그인, 회원가입, 좌석선택
				Thread serverHandler = new Thread(new ServerHandler(socket));
				serverHandler.start();
//				Thread serverTimer = new Thread(new ServerTimer());
//				serverTimer.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
