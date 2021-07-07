package client.program;

import java.io.*;
import java.net.Socket;

import client.frame.TestFrameClient;
import message.Message;

public class ClientHandler implements Runnable{
	//�Է� ��Ʈ��
	public ObjectInputStream ois;
	//��� ��Ʈ��
	public ObjectOutputStream oos;
	
	TestFrameClient frame;
	Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		//�α���â ����
		frame= new TestFrameClient(this);
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
					frame.updateLabel(inMsg.getStr());
				}
			}
			
			//�����ð� ����
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
