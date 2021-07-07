package client.program;

import java.util.*;

public class ClientTimer{
	Timer timer;
	int remain;
		
	public ClientTimer(int remain) {
		//���� �� �����ð� ����
		this.remain = remain;
		timer = new Timer();
		Client.frame.updateLabel(Integer.toString(remain));
		//1�ʸ��� ����
		timer.scheduleAtFixedRate(task,0,1000);
	}
	
	TimerTask task = new TimerTask() {
		public void run() {
			//�ʼ� ����
			remain --;
			Client.frame.updateLabel(Integer.toString(remain));
//			if(remain==10) {
//				//10�ʰ� �Ǹ� �˶�
//				Client.frame.alert10min();
//			}else if(remain<=0) {
//				//0�ʰ� �Ǹ� ����
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
