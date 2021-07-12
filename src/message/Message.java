package message;

import java.io.Serializable;
import java.util.ArrayList;

import pcuser.UserVO;

public class Message implements Serializable{
	private int state;					//상태
	private int result;					//결과
	private String userID;				//사용자 아이디
	private String name;				//사용자 이름
	private String pwd;					//사용자 암호
	private int remain;					//남은 시간
	private int seatNum;				//좌석 번호
	private UserVO uvo;					//유저 데이터
	private ArrayList<UserVO> ulist;	//유저 데이터 목록
	private String str;					//채팅 전송 문자열

	//Getter & Setter
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int result) {
		this.remain = remain;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getSeatNum(){
		return seatNum;
	}
	public void setSeatNum(int seatNum){
		this.seatNum = seatNum;
	}
	public UserVO getUvo() {
		return uvo;
	}
	public void setUvo(UserVO uvo) {
		this.uvo = uvo;
	}
	public ArrayList<UserVO> getUlist() {
		return ulist;
	}
	public void setUlist(ArrayList<UserVO> ulist) {
		this.ulist = ulist;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
}