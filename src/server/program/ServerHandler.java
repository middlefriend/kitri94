package server.program;

import java.io.*;
import java.net.Socket;
import message.Message;
import server.frame.*;
import pcuser.*;

public class ServerHandler implements Runnable{
	public ObjectInputStream ois;	//입력 스트림
	public ObjectOutputStream oos;	//출력 스트림

	public TestFrameServer frame;
	public UserDAO dao = new UserDAO();
	
	Socket socket;
	int seatNum = 0;	//배정 받은 좌석 번호(신규일 때 0)
	
	public ServerHandler(Socket socket,TestFrameServer frame) {
		this.socket = socket;
		this.frame = frame;
	}
	
	public void run() {
		System.out.println("서버 핸들러 시작-"+socket);
		try {
			//입출력 스트림 생성
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"생성");
			
			Message inMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					//클라이언트로 부터 입력받은 메세지
					inMsg = (Message)obj;
					System.out.println(inMsg);
					int state = inMsg.getState();
					switch(state) {
						case 1: {	// ID 중복확인
							int result = dao.checkID(inMSg.getUserID());
							Message outMsg = new Message();
							outMsg.setState(1);
							outMsg.setResult(result);
							oos.writeObject(outMsg);
							break;
						}
						case 2: {	// 회원가입
							int result = dao.insertUser(inMsg.getUserID(),inMsg.getPwd(),inMsg.getName());
							Message outMsg = new Message();
							outMsg.setState(2);
							outMsg.setResult(result);
							oos.writeObject(outMsg);
							break;
						}
						case 3: {	// 로그인
							int result = dao.getAuth(inMsg.getUserID(),inMsg.getPwd());
							Message outMsg = new Message();
							// if(result == 1){
								//로그인 성공 시 응답
								//남은 시간 전송
								outMsg.setState(3);
								outMsg.setResult(result);
								oos.writeObject(outMsg);
							// }else{
								//로그인 실패 시 응답
							// }
							break;
						}
						case 4: {	// 좌석 배정 / 이동
							//배정 받으려는 좌석 번호
							int newSeatNum = inMsg.getSeatNum();
							Message outMsg = new outMsg();
							if(Server.seatMap.containsKey(newSeatNum)){
								//해당 좌석 사용 중
								//배정 실패 응답
								outMsg.setState(4);
								outMsg.setResult(0);
								oos.writeObject(outMsg);
								break;
							}
							//좌석 배정
							Server.seatMap.put(newSeatNum,this);
							//기존 좌석이 있을 때 회수
							if(Server.seatMap.containsKey(seatNum)){
								Server.seatMap.remove(seatNum);	
							}
							seatNum = newSeatNum;
							//배정 성공 응답
							outMsg.setResult(1);
							oos.writeObject(outMsg);
							break;
						}
						case 5: {	// 시간충전
							int time = inMsg.getRemain();
							int result = dvo.chargeTime(inMsg.getUserID(),time);	//time 만큼 기존 시간에 추가
							Message outMsg = new outMsg();
							// if(result == 1){		//시간 충전 성공
								//남은 시간 전송
								outMsg.setState(5);
								outMsg.setResult(result);
								outMsg.setRemain(time);
								oos.writeObject(outMsg);
							// }else{
								//시간 충전 실패
							// }
							break;
						}
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
