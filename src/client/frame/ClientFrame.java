package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import client.program.ClientHandler;
import client.program.ClientTimer;
import message.Message;

public class ClientFrame extends JFrame implements ActionListener {

	JPanel clientPanel;
	
	JTextField textField;

	JTextArea textArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(textArea);
	JLabel pcroomNameL;
	JLabel userIdL;
	JLabel notice1Label; 
	JLabel noticeNameL;
	JLabel notice2Label;
	JLabel noticeTimeL;
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
    public ClientTimer cTimer;
    
    int remain;
    String id;
    String name;
	
    int seat;

	public ClientFrame(int remain, String name, String id) {
		this.setTitle("VIP PC");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 600);
		this.setLayout(null);
		this.name = name;
		this.remain = remain;
		this.id = id;
		System.out.println(remain);
		setComponent();
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

		seatCB = new JComboBox();
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

		textArea.setLineWrap(true);
		scrollPane.setBounds(30, 140, 430, 370);

		textField = new JTextField();
		textField.setBackground(new Color(224, 224, 224));
		textField.setForeground(Color.BLACK);
		textField.setBounds(30, 509, 430, 30);
		textField.setColumns(10);
		
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
		clientPanel.add(textArea);
		clientPanel.add(scrollPane);
		clientPanel.add(textField);

		this.setContentPane(clientPanel);
		eventList();
	}
	
	public void eventList() {
		changeBt.addActionListener(this);
		logoutBt.addActionListener(this);
		purchaseBt.addActionListener(this);
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
			
			lFrame = new LoginFrame();
			
			Message outMsg = new Message();
			outMsg.setUserID(id);
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


//	public static void main(String[] args) {
//		new ClientFrame();
//	}

}