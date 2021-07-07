package server.program;

import java.io.*;
import java.util.Scanner;

import client.frame.TestFrameClient;

import java.net.Socket;

import message.Message;
import server.frame.TestFrameServer;

public class ServerHandler implements Runnable{
	//�Է� ��Ʈ��
	public ObjectInputStream ois;
	//��� ��Ʈ��
	public ObjectOutputStream oos;
	
	Socket socket;
	int seat;
	
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("���� �ڵ鷯 ����-"+socket);
		try {
			//�Է� ��Ʈ�� ����
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"����");
			
			Message inMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					inMsg = (Message)obj;
					//Ŭ���̾�Ʈ�� ���� �Է�
					Server.frame.updateLabel(inMsg.getStr());
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
