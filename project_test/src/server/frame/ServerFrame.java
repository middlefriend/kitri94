package server.frame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import client.program.ClientHandler;
import hist.HistVO;
import server.program.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
import message.Message;
import pcuser.UserVO;
import server.program.*;


public class ServerFrame extends JFrame implements ActionListener{

	JPanel serverPanel;
	JPanel seatPanel;
	JTextArea chat;
	
	JButton chatBt;

	JButton num1,num2,num3,num4,num5,num6,num7,num8,num9,num10;
	JButton num11,num12,num13,num14,num15,num16,num17,num18,num19,num20;
	
	JButton[] btn = {num1, num2,num3,num4,num5,num6,num7,num8,num9,num10,
			num11,num12,num13,num14,num15,num16,num17,num18,num19,num20};
	
	JLabel vipL;
	JLabel seatL;
	JTextField chatF;
	JLabel chatL;
	
	int seat;
	
	private JScrollPane scrollPane = new JScrollPane(chat);
	Image icon = new ImageIcon("./img/icon.png").getImage();

	public ServerFrame() {
		this.setTitle("vip pc room");
		this.setBounds(100, 100, 650, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
//		this.name = name;
		setComponent();
		this.setVisible(true);
		this.setIconImage(icon);

		this.setVisible(true);
	}
	
	
		public void setComponent() {
		serverPanel = new JPanel();
		serverPanel.setBounds(0, 0, 650, 500);
		serverPanel.setLayout(null);
		serverPanel.setBackground(Color.white);
		
		vipL = new JLabel("VIP PC ROOM");
		vipL.setFont(new Font("Cambria", Font.BOLD,24));
		vipL.setBounds(10, 10, 500, 50);
		
		seatL = new JLabel("좌석현황");
		seatL.setBounds(10, 70, 500, 30);
		
		seatPanel = new JPanel();
		seatPanel.setBounds(10, 110, 300, 330);
		seatPanel.setBackground(new Color(224,224,224));
		seatPanel.setLayout(null);
		
		for(int i = 0; i < btn.length; i++) {
			btn[i] = new JButton();	
		}
		
		buttonSetting();

		chatL = new JLabel();
		chatL.setBounds(325, 70, 300, 30);
		chatF = new JTextField();
		chatF.setBounds(320, 410, 230, 30);
		
		chatBt = new JButton("send");
		chatBt.setBounds(551, 410, 68, 28);
		chatBt.setBackground(Color.lightGray);
		chatBt.setBorderPainted(true);
		chatBt.setFocusPainted(false);

		chat = new JTextArea();
		chat.setEditable(false);
		chat.setBackground(new Color(224,224,224));

		scrollPane = new JScrollPane(chat);
		scrollPane.setBounds(320, 110, 300, 300);
		// scrollPane.setBackground(new Color(224,224,224));

		for(int i = 0; i < btn.length; i++) {
			seatPanel.add(btn[i]);
		}
		
		serverPanel.add(chatL);
		serverPanel.add(chatBt);
		serverPanel.add(chatF);
		// serverPanel.add(chat);
		
		serverPanel.add(vipL);
		serverPanel.add(seatL);
		serverPanel.add(seatPanel);
		// serverPanel.add(scrollPane,BorderLayout.CENTER);
		serverPanel.add(scrollPane);
		
		this.setContentPane(serverPanel);
		eventList();
		
	}
	
	public void eventList() {

		for(int i = 0; i < btn.length; i++) {
			btn[i].addActionListener(this);
		}
		chatBt.addActionListener(this);	
	}

	public void buttonSetting() {
		btn[0].setText("1");
		btn[0].setBounds(20, 30, 50, 50);
		btn[0].setBackground(Color.lightGray);
		btn[0].setBorderPainted(false);
		btn[0].setFocusPainted(false);
		btn[1].setText("2");
		btn[1].setBounds(70, 30, 50, 50);
		btn[1].setBackground(Color.lightGray);
		btn[1].setBorderPainted(false);
		btn[1].setFocusPainted(false);
		btn[2].setText("3");
		btn[2].setBounds(120, 30, 50, 50);
		btn[2].setBackground(Color.lightGray);
		btn[2].setBorderPainted(false);
		btn[2].setFocusPainted(false);
		btn[3].setText("4");
		btn[3].setBounds(170, 30, 50, 50);
		btn[3].setBackground(Color.lightGray);
		btn[3].setBorderPainted(false);
		btn[3].setFocusPainted(false);
		btn[4].setText("5");
		btn[4].setBounds(220, 30, 50, 50);
		btn[4].setBackground(Color.lightGray);
		btn[4].setBorderPainted(false);
		btn[4].setFocusPainted(false);
		btn[5].setText("6");
		btn[5].setBounds(20, 110, 50, 50);
		btn[5].setBackground(Color.lightGray);
		btn[5].setBorderPainted(false);
		btn[5].setFocusPainted(false);
		btn[6].setText("7");
		btn[6].setBounds(70, 110, 50, 50);
		btn[6].setBackground(Color.lightGray);
		btn[6].setBorderPainted(false);
		btn[6].setFocusPainted(false);
		btn[7].setText("8");
		btn[7].setBounds(120, 110, 50, 50);
		btn[7].setBackground(Color.lightGray);
		btn[7].setBorderPainted(false);
		btn[7].setFocusPainted(false);
		btn[8] .setText("9");
		btn[8].setBounds(170, 110, 50, 50);
		btn[8].setBackground(Color.lightGray);
		btn[8].setBorderPainted(false);
		btn[8].setFocusPainted(false);
		btn[9].setText("10");
		btn[9].setBounds(220, 110, 50, 50);
		btn[9].setBackground(Color.lightGray);
		btn[9].setBorderPainted(false);
		btn[9].setFocusPainted(false);
		btn[10].setText("11");
		btn[10].setBounds(20, 170, 50, 50);
		btn[10].setBackground(Color.lightGray);
		btn[10].setBorderPainted(false);
		btn[10].setFocusPainted(false);
		btn[11].setText("12");
		btn[11].setBounds(70, 170, 50, 50);
		btn[11].setBackground(Color.lightGray);
		btn[11].setBorderPainted(false);
		btn[11].setFocusPainted(false);
		btn[12].setText("13");
		btn[12].setBounds(120, 170, 50, 50);
		btn[12].setBackground(Color.lightGray);
		btn[12].setBorderPainted(false);
		btn[12].setFocusPainted(false);
		btn[13].setText("14");
		btn[13].setBounds(170, 170, 50, 50);
		btn[13].setBackground(Color.lightGray);
		btn[13].setBorderPainted(false);
		btn[13].setFocusPainted(false);
		btn[14].setText("15");
		btn[14].setBounds(220, 170, 50, 50);
		btn[14].setBackground(Color.lightGray);
		btn[14].setBorderPainted(false);
		btn[14].setFocusPainted(false);
		btn[15].setText("16");
		btn[15].setBounds(20, 250, 50, 50);
		btn[15].setBackground(Color.lightGray);
		btn[15].setBorderPainted(false);
		btn[15].setFocusPainted(false);
		btn[16].setText("17");
		btn[16].setBounds(70, 250, 50, 50);
		btn[16].setBackground(Color.lightGray);
		btn[16].setBorderPainted(false);
		btn[16].setFocusPainted(false);
		btn[17].setText("18");
		btn[17].setBounds(120, 250, 50, 50);
		btn[17].setBackground(Color.lightGray);
		btn[17].setBorderPainted(false);
		btn[17].setFocusPainted(false);
		btn[18].setText("19");
		btn[18].setBounds(170, 250, 50, 50);
		btn[18].setBackground(Color.lightGray);
		btn[18].setBorderPainted(false);
		btn[18].setFocusPainted(false);
		btn[19].setText("20");
		btn[19].setBounds(220, 250, 50, 50);
		btn[19].setBackground(Color.lightGray);
		btn[19].setBorderPainted(false);
		btn[19].setFocusPainted(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < btn.length; i ++) {
			if(btn[i] == e.getSource()) {
				seat = i+1;
				chatL.setText((i+1)+"번 좌석");
			}
		}
		if(chatBt == e.getSource()){
			String chat = chatF.getText();
			//chatF 필드에 아무것도 입력하지 않았을때 동작하지 않음
			if(chat.equals("")) return;
			//배정되지 않은 좌석일때 알림
			if(!Server.seatMap.containsKey(seat)){
				JOptionPane.showMessageDialog(null, "미사용 좌석입니다.");
				return;
			}
			Message outMsg = new Message();
			outMsg.setChat(chat);
			outMsg.setState(8);
			try {
				//채팅 해당 좌석 사용자에게 전송
				Server.seatMap.get(seat).writeObject(outMsg);
				//서버프레임에 전송한 내용 표시
				echoChat(seat,chat);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	public void seatInfoRefresh(String userID, int seatNum, HashMap<Integer, ObjectOutputStream> seatMap) {
		for (int i = 0; i < 20; i++) {
			if (seatMap.containsKey(i + 1) && (i + 1) == seatNum) {
				btn[i].setText("<HTML><center>" + String.valueOf(i + 1) + "</center><br>" + userID + "</HTML>");
			} else if (seatMap.containsKey(i + 1) == false) {
				btn[i].setText("<HTML><center>" + String.valueOf(i + 1) + "</center></HTML>");
			}

		}
	}

	public ServerFrame returnServerF() {
		return this;
	}
	
	public void updateChat(int seat,String chat){
		System.out.println("["+seat+"번 좌석]: "+chat);
		this.chat.append("["+seat+"번 좌석]: "+chat+"\n");
		this.chat.setCaretPosition(chat.length());
	}

	public void echoChat(int seat,String chat){
		System.out.println("[관리자]>["+seat+"번 좌석]: "+chat);
		this.chat.append("[관리자]>["+seat+"번 좌석]: "+chat+"\n");
		this.chat.setCaretPosition(chat.length());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerFrame();
		
	}
}