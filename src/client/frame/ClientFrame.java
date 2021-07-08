package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClientFrame extends JFrame implements ActionListener {

	JPanel clientPanel;
	
	JTextField textField;

	JTextArea textArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(textArea);
	JLabel pcroomNameL;
	JLabel userIdL;
	JLabel notice1Label; 
	JLabel notice2Label;
	JLabel noticeTimeL;
	JComboBox seatCB;

	JButton changeBt;
	JButton logoutBt;
	JButton purchaseBt;

	String[] seatNum = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15","16","17","18","19","20"};
	
	Font fTitleLabel = new Font("Comic Sans MS", Font.BOLD, 30);
    Font fLabel = new Font("굴림", Font.PLAIN, 12);
    Font fBt = new Font("굴림", Font.PLAIN, 12);
    
    public LoginFrame lFrame;
    public PurchaseFrame pFrame;
	
	public ClientFrame() {
		this.setTitle("VIP PC");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 600);
		this.setLayout(null);
		setComponent();
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
		userIdL.setFont(fLabel);
		userIdL.setHorizontalAlignment(SwingConstants.RIGHT);
		userIdL.setBounds(250, 20, 50, 15);

		notice1Label = new JLabel("님 환영합니다!");
		notice1Label.setFont(fLabel);
		notice1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		notice1Label.setBounds(308, 20, 164, 15);

		notice2Label = new JLabel("잔여시간 : ");
		notice2Label.setFont(fLabel);
		notice2Label.setBounds(308, 39, 61, 15);

		noticeTimeL = new JLabel("");
		noticeTimeL.setFont(fLabel);
		noticeTimeL.setBounds(381, 39, 91, 15);

		seatCB = new JComboBox();
		seatCB.setMaximumRowCount(5);
		seatCB.setModel(new DefaultComboBoxModel(seatNum));
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
		
		//로그아웃
		if(logoutBt == e.getSource()) {
			dispose();
			lFrame = new LoginFrame();
		}
		//시간구매
		if(purchaseBt == e.getSource()) {
			pFrame = new PurchaseFrame();
		}
		
	}
	
	public static void main(String[] args) {
		new ClientFrame();
	}

}
