package server.program;

import java.io.IOException;
import java.util.*;

import message.Message;

public class ServerTimer implements Runnable{
	
	public ServerTimer() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Timer endTime = new Timer();
		
		TimerTask endSocket =  new TimerTask() {

			@Override
			public void run() {
				Message outMsg = new Message();
				outMsg.setStr("end process");
				try {
					ServerHandler.oos.writeObject(outMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		};
		endTime.schedule(endSocket, 3000);
	}
	
}
