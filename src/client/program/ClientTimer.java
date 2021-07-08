package client.program;

import java.util.*;

import client.frame.ClientFrame;

public class ClientTimer{
	Timer timer;
	int remain;
	ClientFrame clientFrame;
		
	public ClientTimer(int remain,ClientFrame clientFrame) {
		//생성 시 남은시간 저장
		this.remain = remain;
		this.clientFrame = clientFrame;
		timer = new Timer();
		//1초마다 동작
		timer.scheduleAtFixedRate(task,0,1000);
	}
	
	TimerTask task = new TimerTask() {
		public void run() {
			//초수 감소
			remain --;
			// frame.updateLabel(Integer.toString(remain));
//			if(remain==10) {
//				//10초가 되면 알람
//				Client.frame.alert10min();
//			}else if(remain<=0) {
//				//0초가 되면 종료
			if(remain<=0) {
				timer.cancel();
				return;
			}
		}
	};
	
	public void finalize() {
		timer.cancel();
	}
}
