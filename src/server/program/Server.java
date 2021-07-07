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
		//���� port
		final int serverPort = 7777;
		
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("���� ����");
			while(true) {
				//���� ���� ����
				Socket socket = serverSocket.accept();
				socketList.put(socket,-1);
				System.out.println("Ŭ���̾�Ʈ ���� �Ϸ� -("+socketList.size()+")");
				
				
				//��� ������ : �α���, ȸ������, �¼�����
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
