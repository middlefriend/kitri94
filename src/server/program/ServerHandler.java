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
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois+","+oos+"생성");
			
			Message inMsg = null;
			Object obj=null;
			
			while((obj=ois.readObject())!=null) {
				if(obj instanceof Message) {
					//클라이언트로 부터 입력받은 메세지
					inMsg = (Message)obj;
					System.out.println(inMsg);
					int state = inMsg.getState();
					Message outMsg = new Message();
					switch(state) {
						case 1: {	// ID 중복확인
							int result = dao.checkID(inMSg.getUserID());
							outMsg.setState(1);
							outMsg.setResult(result);
							break;
						}
						case 2: {	// 회원가입
							int result = dao.insertUser(inMsg.getUserID(),inMsg.getPwd(),inMsg.getName());
							outMsg.setState(2);
							outMsg.setResult(result);
							break;
						}
						case 3: {	// 로그인
							int result = dao.getAuth(inMsg.getUserID(),inMsg.getPwd());
							outMsg.setState(3);
							outMsg.setResult(result);
							//로그인 실패 시
							if(result==0) break;
							//로그인 성공 시 좌석 배정
							seatNum = inMsg.getSeatNum();
							Server.seatMap.put(seatNum,this.oos);
							//남은 시간 전송
							int remain = 0;	//select remain where userid=inMsg.userID
							outMsg.setRemain(remain);
							//로그
							HistDAO hdao = new HistDAO();
							hdao.insertHistory(id,seatNum,"로그인");
							break;
						}
						case 4: {	// 좌석 이동
							//배정 받으려는 좌석 번호
							int newSeatNum = inMsg.getSeatNum();
							outMsg.setState(4);
							//해당 좌석 사용 중
							if(Server.seatMap.containsKey(newSeatNum)){
								//배정 실패 응답
								outMsg.setResult(0);
								break;
							}
							//좌석 배정
							Server.seatMap.put(newSeatNum,this.oos);
							this.seatNum = newSeatNum;
							//기존 좌석 회수
							Server.seatMap.remove(seatNum);	
							//배정 성공 응답
							outMsg.setResult(1);
							//로그
							HistDAO hdao = new HistDAO();
							hdao.insertHistory(id,seatNum,"자리이동");
							break;
						}
						case 5: {	// 시간충전
							int time = inMsg.getRemain();
							//time 만큼 기존 시간에 추가
							int result = dvo.chargeTime(inMsg.getUserID(),time);
							outMsg.setState(5);
							outMsg.setResult(result);
							if(result == 1){
								//로그
								HistDAO hdao = new HistDAO();
								hdao.insertChargeTime(id,time,"충전");
							}
							break;
						}
						case 6: {	// 로그아웃
							int remain = inMsg.getRemain();
							int result = dvo.chargeTime(inMsg.getUserID(),remain);
							outMsg.setState(6);
							outMsg.setResult(result);
							//남은 시간 갱신 완료
							if(result == 1){
								//로그
								HistDAO hdao = new HistDAO();
								hdao.insertHistory(id,seatNum,"로그아웃");
							}
							break;
						}
					}
					oos.writeObject(outMsg);
					oos.flush();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
