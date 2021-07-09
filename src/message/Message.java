package message;

import java.io.Serializable;
import pcuser.UserVO;

public class Message implements Serializable{
	private int state;
	private int result;
	private String str;
	private UserVO uvo;

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
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public UserVO getUvo() {
		return uvo;
	}
	public void setUvo(UserVO uvo) {
		this.uvo = uvo;
	}

}
