package server.program;

import java.io.*;
import java.net.*;
import java.util.HashSet;

import server.frame.TestFrameServer;

public class Server {
	
	public static HashSet<ServerHandler> serverSet = new HashSet<ServerHandler>();
	public static TestFrameServer frame;
	
	static boolean[] seatStat = new boolean[20];
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		//���� port
		final int serverPort = 7777;
		
		frame = new TestFrameServer();
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("���� ����");
			while(true) {
				//���� ���� ����
				Socket socket = serverSocket.accept();
				ServerHandler serverHandler= new ServerHandler(socket);
				serverSet.add(serverHandler);
				System.out.println("Ŭ���̾�Ʈ ���� �Ϸ� -("+serverSet.size()+")");
				
				
				//��� ������ : �α���, ȸ������, �¼�����
				Thread serverHandlerT = new Thread(serverHandler);
				serverHandlerT.start();
//				Thread serverTimer = new Thread(new ServerTimer());
//				serverTimer.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
