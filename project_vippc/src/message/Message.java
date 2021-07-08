package message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{
	private int state;
	private int seat;
	private int remain;
	private String userId;
	private String pwd;
	private String name;
	private int result;
	
//	private UserVO uvo;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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

//	public UserVO getUvo() {
//		return uvo;
//	}
//	public void setUvo(UserVO uvo) {
//		this.uvo = uvo;
//	}

}
