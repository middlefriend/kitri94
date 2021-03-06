package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import client.program.ClientHandler;
import client.program.ClientTimer;
import message.Message;
import server.frame.ServerFrame;
import server.program.Server;

public class ClientFrame extends JFrame implements ActionListener {

	JPanel clientPanel;
	
	JTextField textField;

	JTextArea chatF = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(chatF);
	JLabel pcroomNameL;
	JLabel userIdL;
	JLabel notice1Label; 
	JLabel noticeNameL;
	JLabel notice2Label;
	JLabel noticeTimeL;
	JButton sendBt;
	JComboBox<String> seatCB;

	JButton changeBt;
	JButton logoutBt;
	JButton purchaseBt;
	
	Timer timer;
	TimerTask task;

	String[] seatNum = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15","16","17","18","19","20"};
	
	Font fTitleLabel = new Font("Comic Sans MS", Font.BOLD, 30);
    Font fLabel = new Font("굴림", Font.PLAIN, 12);
    Font fBt = new Font("굴림", Font.PLAIN, 12);
    
    public LoginFrame lFrame;
    public PurchaseFrame pFrame;
    public ServerFrame frame;
    // public ClientTimer cTimer;
    
    int remain;
    String id;
    String name;
	
    int seat;
	Image icon = new ImageIcon("./img/icon.png").getImage();

	public ClientFrame(int remain, String name, String id) {
		this.setTitle("VIP PC");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 500, 600);
		this.setLayout(null);
		this.name = name;
		this.remain = remain;
		this.id = id;
		setComponent();
		this.setIconImage(icon);

		setTimer();
		this.setVisible(true);
	}

	public void setComponent() {
		clientPanel = new JPanel();
		clientPanel.setLayout(null);
		clientPanel.setBounds(0, 0, 500, 600);
		clientPanel.setBackground(Color.white);
		
		pcroomNameL = new JLabel("VIP PC ROOM");
		pcroomNameL.setFont(fTitleLabel);
		pcroomNameL.setBounds(30, 20, 266, 35);
		
		userIdL = new JLabel();
		userIdL.setText(name);
		userIdL.setFont(fLabel);
		userIdL.setHorizontalAlignment(SwingConstants.RIGHT);
		userIdL.setBounds(250, 20, 100, 15);

		notice1Label = new JLabel("님 환영합니다!");
		notice1Label.setFont(fLabel);
		notice1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		notice1Label.setBounds(308, 20, 164, 15);
		
		noticeNameL = new JLabel();
		noticeNameL.setFont(fLabel);
		noticeNameL.setBounds(230, 20, 70, 15);

		notice2Label = new JLabel("잔여시간 : ");
		notice2Label.setFont(fLabel);
		notice2Label.setBounds(308, 39, 61, 15);

		noticeTimeL = new JLabel(String.valueOf(remain/60)+"시간"+String.valueOf(remain%60)+"분");
		noticeTimeL.setFont(fLabel);
		noticeTimeL.setBounds(381, 39, 91, 15);

		seatCB = new JComboBox<String>();
		seatCB.setMaximumRowCount(5);
		seatCB.setModel(new DefaultComboBoxModel<String>(seatNum));
		seatCB.setBackground(new Color(224, 224, 224));
		seatCB.setBounds(30, 88, 80, 25);

		changeBt = new JButton("자리이동");
		changeBt.setFont(fBt);
		changeBt.setBounds(120, 80, 100, 40);
		changeBt.setBackground(new Color(224, 224, 224));
		changeBt.setBorderPainted(false);
		changeBt.setFocusPainted(false);

		logoutBt = new JButton("로그아웃");
		logoutBt.setFont(fBt);
		logoutBt.setBounds(240, 80, 100, 40);
		logoutBt.setBackground(new Color(224, 224, 224));
		logoutBt.setBorderPainted(false);
		logoutBt.setFocusPainted(false);

		purchaseBt = new JButton("시간 충전");
		purchaseBt.setFont(fBt);
		purchaseBt.setBounds(360, 80, 100, 40);
		purchaseBt.setBackground(new Color(224, 224, 224));
		purchaseBt.setBorderPainted(false);
		purchaseBt.setFocusPainted(false);

		chatF.setEditable(false);
		chatF.setLineWrap(true);
		scrollPane.setBounds(30, 140, 430, 370);

		textField = new JTextField();
		textField.setBackground(new Color(224, 224, 224));
		textField.setForeground(Color.BLACK);
		textField.setBounds(30, 509, 360, 30);
		textField.setColumns(10);
		
		sendBt = new JButton("send");
		sendBt.setFont(fBt);
		sendBt.setBounds(390, 509, 69, 29);
		sendBt.setBackground(new Color(224, 224, 224));
		sendBt.setFocusPainted(false);
		
		clientPanel.add(pcroomNameL);
		clientPanel.add(userIdL);
		clientPanel.add(notice1Label);
		clientPanel.add(notice2Label);
		clientPanel.add(noticeTimeL);
		clientPanel.add(seatCB);
		clientPanel.add(changeBt);
		clientPanel.add(logoutBt);
		clientPanel.add(purchaseBt);
		clientPanel.add(seatCB);
		// clientPanel.add(textArea);
		clientPanel.add(scrollPane);
		clientPanel.add(textField);
		clientPanel.add(sendBt);

		this.setContentPane(clientPanel);
		eventList();
	}
	
	public void eventList() {
		changeBt.addActionListener(this);
		logoutBt.addActionListener(this);
		purchaseBt.addActionListener(this);
		sendBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//자리이동
		if(changeBt == e.getSource()) {
			seat = Integer.parseInt(seatCB.getSelectedItem().toString());	
			//자리 중복 확인
			Message outMsg = new Message();
			outMsg.setUserID(id);
			outMsg.setSeatNum(seat);
			outMsg.setState(4);
			
			try {
				ClientHandler.oos.writeObject(outMsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//로그아웃
		if(logoutBt == e.getSource()) {
			dispose();
			
			lFrame = new LoginFrame(false);
			
			Message outMsg = new Message();
			outMsg.setUserID(id);
			outMsg.setRemain(remain);
			outMsg.setState(6);
			
			try {
				ClientHandler.oos.writeObject(outMsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//history남기기
		}
		//시간구매
		if(purchaseBt == e.getSource()) {
			
			pFrame = new PurchaseFrame();
			pFrame.getRemainTime(remain);
		}

		if(sendBt == e.getSource()){
			String chat = textField.getText();
			//chatF 필드에 아무것도 입력하지 않았을때 동작하지 않음
			if(chat == "") return;
			Message outMsg = new Message();
			outMsg.setChat(chat);
			outMsg.setState(8);
			try {
				//채팅 해당 좌석 사용자에게 전송
				ClientHandler.oos.writeObject(outMsg);
				//서버프레임에 전송한 내용 표시
				echoChat(seat,chat);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void changeSeatResult(int result) {
		if (result == 1) {
			JOptionPane.showMessageDialog(null, "좌석 이동에 성공하였습니다.");
		} else {
			JOptionPane.showConfirmDialog(null, "좌석이동에 실패하였습니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	void setTimer(){
		updateTime();
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				//분수 감소
				remain --;
				//화면에서 잔여시간 갱신
				updateTime();
			}
		};
		 timer.scheduleAtFixedRate(task,60l*1000,60l*1000);
//		timer.scheduleAtFixedRate(task,1l*1000,1l*1000); //초로 테스트
	}
	
	void updateTime() {
		// System.out.println(remain);
		noticeTimeL.setText(String.valueOf((remain/60) + "시간" + (remain%60) +"분"));
		if (remain == 10) {
			// 잔여시간 10이 되면 경고
			JOptionPane.showConfirmDialog(null, "종료시간 10분 전입니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
		} else if (remain == 0) {
			// 잔여시간이 0이 되면 종료
			JOptionPane.showConfirmDialog(null, "자동 종료되었습니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);
			task.cancel();
			// 종료알림
			dispose();
		}
	}
	


	public void resetTimer(int remain) {
		this.remain = remain;
		updateTime();
	}

	public void updateChat(String chat){
		chatF.append("[관리자]: "+chat+"\n");
		chatF.setCaretPosition(chat.length());
	}

	public void echoChat(int seat,String chat){
		chatF.append("["+name+"]: "+chat+"\n");
		chatF.setCaretPosition(chat.length());
	}

//	public static void main(String[] args) {
//		new ClientFrame();
//	}

}