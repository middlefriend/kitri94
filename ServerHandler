package server.program;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.Message;
import pcuser.UserDAO;

public class ServerHandler implements Runnable {
	// 입력 스트림
	public ObjectInputStream ois;
	// 출력 스트림
	public ObjectOutputStream oos;

	Socket socket;
	int seat;

	public ServerHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		System.out.println("서버 핸들러 시작-" + socket);
		try {
			// 입력 스트림 생성
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois + "," + oos + "생성");

			Message inMsg = null;
			Object obj = null;

			while ((obj = ois.readObject()) != null) {
				if (obj instanceof Message) {
					inMsg = (Message) obj;
					Message outMsg = new Message();
					UserDAO udao = new UserDAO();
					// 클라이언트로 부터 입력
					int stat = inMsg.getState();

					switch (stat) {
						case 1: { // ID 중복확인
							String id = inMsg.getUvo().getUserID();
							int result = udao.checkID(id);
							// state - idcheck에 대한 결과 값으로 1입력
							outMsg.setState(1);
							// result
							outMsg.setResult(result);
							// writeObject
							oos.writeObject(outMsg);
							oos.flush();
	
						}
						case 2: {// 회원가입
							outMsg = new Message();
							int result = udao.insertUser(inMsg.getUvo().getUserID(), inMsg.getUvo().getPwd(),
									inMsg.getUvo().getName());
							outMsg.setState(2);
							outMsg.setResult(result);
							oos.writeObject(outMsg);
							oos.flush();
						}
						case 3: {// 로그인
							outMsg = new Message();
							int result = udao.getAuth(inMsg.getUvo().getUserID(), inMsg.getUvo().getPwd());
							outMsg.setState(3);
							outMsg.setResult(result);
							oos.writeObject(outMsg);
							oos.flush();
						}
						case 4: {// 좌석이동
							outMsg = new Message();
	
						}
						case 5: {
							// 시간충전
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
