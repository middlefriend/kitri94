package client.program;

import java.io.*;
import java.net.Socket;

import client.frame.TestFrameClient;
import message.Message;

public class Client {
	
	public static void main(String[] args) {
		Socket socket = null; 
		//���� ip
		final String serverIp = "localhost";
		//���� port
		final int serverPort = 7777;

		try {
			
			//���� ���� ����
			socket = new Socket(serverIp,serverPort);
			System.out.println("Ŭ���̾�Ʈ ����");
			
			//���� ��� ������
			Thread clientHandler= new Thread(new ClientHandler(socket));
			clientHandler.start();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
