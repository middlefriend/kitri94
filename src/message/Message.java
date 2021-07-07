package message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{
	private int state;
	private int seat;
	private String str;
	private int remain;
//	private UserVO uvo;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
//	public UserVO getUvo() {
//		return uvo;
//	}
//	public void setUvo(UserVO uvo) {
//		this.uvo = uvo;
//	}

}
