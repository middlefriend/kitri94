package server.program;

import java.io.*;
import java.net.Socket;

import hist.HistDAO;
import message.Message;
import server.frame.*;
import pcuser.*;

public class ServerHandler implements Runnable{
	public ObjectInputStream ois;	//입력 스트림
	public ObjectOutputStream oos;	//출력 스트림

	public ServerFrame frame;
	public UserDAO dao = new UserDAO();
	
	Socket socket;
	int seatNum = 0;	//배정 받은 좌석 번호(신규일 때 0)
	
	public ServerHandler(Socket socket, ServerFrame frame) {
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
					// System.out.println(inMsg);
					int state = inMsg.getState();
					Message outMsg = new Message();
					boolean noNeedReply = false;
					switch(state) {
						case 1: {	// ID 중복확인
							int result = dao.checkID(inMsg.getUserID());
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
							UserVO uvo = dao.getUser(inMsg.getUserID());
							//사용자 이름 전송
							outMsg.setName(uvo.getName());
							//남은 시간 전송
							outMsg.setRemain(uvo.getRemain());
							outMsg.setUserID(uvo.getUserID());
							//로그인 성공 시 좌석 배정
							seatNum = inMsg.getSeatNum();
							Server.seatMap.put(seatNum,this.oos);
							//로그
							HistDAO hdao = new HistDAO();
							hdao.insertHistory(inMsg.getUserID(),inMsg.getSeatNum(),"로그인");
							System.out.println(outMsg.getRemain());
							frame.seatInfoRefresh(inMsg.getUserID()); 
							break;
						}
						case 4: {	// 좌석 이동
							//배정 받으려는 좌석 번호
							int newSeatNum = inMsg.getSeatNum();
							outMsg.setState(4);
							String id = inMsg.getUserID();
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
							hdao.insertHistory(id, seatNum,"자리이동");
							frame.seatInfoRefresh(inMsg.getUserID()); 
							break;
						}
						case 5: {	// 시간충전
							int time = inMsg.getRemain();
							String id = inMsg.getUserID();
							//time 만큼 기존 시간에 추가
							int result = dao.chargeTime(inMsg.getUserID(),time);
							outMsg.setState(5);
							outMsg.setResult(result);
							UserVO uvo = dao.getUser(id);
							outMsg.setRemain(uvo.getRemain());
							if(result == 1){
								//로그
								HistDAO hdao = new HistDAO();
								hdao.insertChargeTime(id, time,"충전");
							}
							break;
						}
						case 6: {	// 로그아웃
							int remain = inMsg.getRemain();
							String id = inMsg.getUserID();
							int result = dao.chargeTime(inMsg.getUserID(),remain);
							outMsg.setState(6);
							outMsg.setResult(result);
							//남은 시간 갱신 완료
							if(result == 1){
								//로그
								HistDAO hdao = new HistDAO();
								hdao.insertHistory(id, seatNum, "로그아웃");
								UserDAO udao = new UserDAO();
								udao.changeRemain(id, remain);
							}
							UserVO uvo = dao.getUser(id);
							outMsg.setRemain(uvo.getRemain());
							frame.seatInfoRefresh(inMsg.getUserID()); 
							break;
						}
						case 7: {	// ID 중복확인
							int result = dao.checkID(inMsg.getUserID());
							outMsg.setState(7);
							outMsg.setResult(result);
							break;
							//로그인 안하고 충전하면 에러
						}
						case 8:{	// 채팅
							String str = inMsg.getChat();
							frame.updateChat(seatNum,str);
							noNeedReply = true;
							break;
						}
					}
					//회신이 필요없는 동작이면 스킵
					if(noNeedReply) continue;
					oos.writeObject(outMsg);
					oos.flush();
					//좌석 정보 새로고침
						
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
