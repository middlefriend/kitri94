package client.program;

import java.util.*;

import client.frame.ClientFrame;

public class ClientTimer{
	Timer timer;
	long remain;
	ClientFrame clientFrame;
		
	public ClientTimer(int remain, ClientFrame clientFrame) {
		//생성 시 남은시간 저장
		this.remain = 60l*1000*remain;
		this.clientFrame = clientFrame;
		//화면에서 잔여시간 갱신
		//ClientFrame.notice2Label

		timer = new Timer();
		//1초마다 동작
		timer.scheduleAtFixedRate(task,0l,1000l);
	}
	
	TimerTask task = new TimerTask() {
		public void run() {
			//초수 감소
			remain -=1000l;
			//화면에서 잔여시간 갱신
			

			if(remain==10l*60*1000) {
				//잔여시간이 10분 되면 알람
			}else if(remain<=0) {
				//잔여시간이 0이 되면 종료
				timer.cancel();
			}
		}
	};

	public void updateRemain(int newRemain){
		//이전 타이머 해제
		timer.cancel();
		//남은 시간 갱신
		this.remain += 60l*1000*newRemain;
		//화면에서 잔여시간 갱신

		//갱신된 타이머 시작
		timer.scheduleAtFixedRate(task,1000l,1000l);
	}
	
}