package client.program;

import java.io.*;
import java.net.Socket;

import message.Message;

public class ClientHandler implements Runnable{
	//�Է� ��Ʈ��
	public static ObjectInputStream ois;
	//��� ��Ʈ��
	public static ObjectOutputStream oos;
	
	Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	
	public void run() {
		System.out.println("Ŭ���̾�Ʈ �ڵ鷯 ����");
		try {
			//����� ��Ʈ�� ����
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"����");
			
			Message inMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					inMsg = (Message)obj;
					//�����κ��� msg �Է�
					int stat = inMsg.getState();
					switch (stat) {
					case 1:
					{
						Client.frame.updateLabel(inMsg.getStr());
					}
					}
				}
			}
			
			//�����ð� ����
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
