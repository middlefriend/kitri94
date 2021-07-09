package server.program;


import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.HashSet;

import client.frame.LoginFrame;
import server.frame.ServerFrame;


public class Server {
	
	public static HashSet<ServerHandler> serverSet = new HashSet<ServerHandler>();
	public static LoginFrame lframe;
	static HashMap<Integer, ObjectOutputStream> seatMap = new HashMap<Integer, ObjectOutputStream>();
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		//서버 port
		final int serverPort = 7777;
		lframe = new LoginFrame();
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("서버 시작");
			while(true) {
				//서버 소켓 연결
				Socket socket = serverSocket.accept();
				ServerHandler serverHandler= new ServerHandler(socket, lframe);
				serverSet.add(serverHandler);
				System.out.println("클라이언트 접속 완료 -("+serverSet.size()+")");
				
				
				//출력 스레드 : 로그인, 회원가입, 좌석선택
				Thread serverHandlerT = new Thread(serverHandler);
				serverHandlerT.start();
//				Thread serverTimer = new Thread(new ServerTimer());
//				serverTimer.start();
			}
		}catch(IOException e) {
=======
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import server.frame.ServerFrame;

public class Server {

	public static HashSet<ServerHandler> serverSet = new HashSet<ServerHandler>();
//	public static LoginFrame lframe;
	public static ServerFrame sframe;
	static boolean[] seatStat = new boolean[20];

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		// 서버 port
		final int serverPort = 7777;
//		lframe = new LoginFrame();
		sframe = new ServerFrame();
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("서버 시작");
			while (true) {
				// 서버 소켓 연결
				Socket socket = serverSocket.accept();
				ServerHandler serverHandler = new ServerHandler(socket, sframe);
				serverSet.add(serverHandler);
				System.out.println("클라이언트 접속 완료 -(" + serverSet.size() + ")");

				// 출력 스레드 : 로그인, 회원가입, 좌석선택
				Thread serverHandlerT = new Thread(serverHandler);
				serverHandlerT.start();
//				Thread serverTimer = new Thread(new ServerTimer());
//				serverTimer.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
