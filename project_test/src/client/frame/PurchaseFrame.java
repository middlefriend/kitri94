package client.frame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutput;

import javax.swing.*;

import client.program.ClientHandler;
import message.Message;

public class PurchaseFrame extends JFrame implements ActionListener {

	int time = 0;
	int hour;
	int min;
	int totalMoney;
	
	JPanel purchasePanel;
	
	JLabel idL;
	JTextField idField;
	JButton idCheckBt;
	
	JButton thirtyBt;
	JButton oneHourBt;
	JButton twoHourBt;
	JButton fiveHourBt;
	JButton tenHourBt;
	JButton twentyHourBt;
	
	JLabel timeL;
	JLabel totlaL;
	
	JButton purchaseBt;
	JButton cancelBt;
	
	int idCheck=0;
	
	public ClientFrame cFrame;
	
	public PurchaseFrame() {
		this.setTitle("시간 구매");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 380, 450);
		this.setLayout(null);
		setComponent();
		this.setVisible(true);
	}
	
	public void setComponent() {
		purchasePanel = new JPanel();
		purchasePanel.setLayout(null);
		purchasePanel.setBounds(0, 0, 380, 450);
		purchasePanel.setBackground(Color.white);
		
		idL = new JLabel();
		idL.setText("ID ");
		idL.setBounds(20, 20, 20, 30);
		
		idField = new JTextField();
		idField.setBounds(50, 20, 195, 30);
		idCheckBt = new JButton();
		idCheckBt.setText("ID 확인");
		idCheckBt.setBounds(250, 20, 90, 30);
		idCheckBt.setBackground(new Color(224,224,224));
		idCheckBt.setBorderPainted(false);
		idCheckBt.setFocusPainted(false);
		
		thirtyBt = new JButton();
		thirtyBt.setText("<HTML>30분<br>400₩</HTML>");
		thirtyBt.setBounds(20, 80, 100, 100);
		thirtyBt.setBackground(new Color(224,224,224));
		thirtyBt.setBorderPainted(false);
		thirtyBt.setFocusPainted(false);
		oneHourBt = new JButton();
		oneHourBt.setText("<HTML>1시간<br>700₩</HTML>");
		oneHourBt.setBounds(130, 80, 100, 100);
		oneHourBt.setBackground(new Color(224,224,224));
		oneHourBt.setBorderPainted(false);
		oneHourBt.setFocusPainted(false);
		twoHourBt = new JButton();
		twoHourBt.setText("<HTML>2시간<br>1400₩</HTML>");
		twoHourBt.setBounds(240, 80, 100, 100);
		twoHourBt.setBackground(new Color(224,224,224));
		twoHourBt.setBorderPainted(false);
		twoHourBt.setFocusPainted(false);
		fiveHourBt = new JButton();
		fiveHourBt.setText("<HTML>5시간<br>3,000₩</HTML>");
		fiveHourBt.setBounds(20, 190, 100, 100);
		fiveHourBt.setBackground(new Color(224,224,224));
		fiveHourBt.setBorderPainted(false);
		fiveHourBt.setFocusPainted(false);
		tenHourBt = new JButton();
		tenHourBt.setText("<HTML>10시간<br>6,000₩</HTML>");
		tenHourBt.setBounds(130, 190, 100, 100);
		tenHourBt.setBackground(new Color(224,224,224));
		tenHourBt.setBorderPainted(false);
		tenHourBt.setFocusPainted(false);
		twentyHourBt = new JButton();
		twentyHourBt.setText("<HTML>20시간<br>14,000₩</HTML>");
		twentyHourBt.setBounds(240, 190, 100, 100);
		twentyHourBt.setBackground(new Color(224,224,224));
		twentyHourBt.setBorderPainted(false);
		twentyHourBt.setFocusPainted(false);
		
		timeL = new JLabel();
		timeL.setBounds(20, 305, 100, 30);
		timeL.setHorizontalAlignment(JLabel.CENTER);
		timeL.setOpaque(true);
		timeL.setBackground(Color.lightGray);
		totlaL = new JLabel();
		totlaL.setBounds(130, 305, 210, 30);
		totlaL.setOpaque(true);
		totlaL.setBackground(Color.lightGray);
		
		purchaseBt = new JButton();
		purchaseBt.setText("충전");
		purchaseBt.setBounds(70, 350, 100, 40);
		purchaseBt.setBackground(new Color(224,224,224));
		purchaseBt.setBorderPainted(false);
		purchaseBt.setFocusPainted(false);
		cancelBt = new JButton();
		cancelBt.setText("취소");
		cancelBt.setBounds(190, 350, 100, 40);
		cancelBt.setBackground(new Color(224,224,224));
		cancelBt.setBorderPainted(false);
		cancelBt.setFocusPainted(false);
		
		purchasePanel.add(idL);
		purchasePanel.add(idField);
		purchasePanel.add(idCheckBt);
		purchasePanel.add(thirtyBt);
		purchasePanel.add(oneHourBt);
		purchasePanel.add(twoHourBt);
		purchasePanel.add(fiveHourBt);
		purchasePanel.add(tenHourBt);
		purchasePanel.add(twentyHourBt);
		purchasePanel.add(timeL);
		purchasePanel.add(totlaL);
		purchasePanel.add(purchaseBt);
		purchasePanel.add(cancelBt);
		
		this.setContentPane(purchasePanel);
		eventList();
	}
	
	public void eventList() {
		idCheckBt.addActionListener(this);
		thirtyBt.addActionListener(this);
		oneHourBt.addActionListener(this);
		twoHourBt.addActionListener(this);
		fiveHourBt.addActionListener(this);
		tenHourBt.addActionListener(this);
		twentyHourBt.addActionListener(this);
		purchaseBt.addActionListener(this);
		cancelBt.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ID확인 버튼
		if (idCheckBt == e.getSource()) { // ID 공백
			String id = idField.getText();
			if (id.equals("")) {
				JOptionPane.showConfirmDialog(null, "ID를 입력해 주세요.", "경고", JOptionPane.DEFAULT_OPTION);
				idField.requestFocus();
				return;
			}
			// ID 값 받아서 비교
			System.out.println("pframe oos 실행1 "+id);
			Message outMsg = new Message();
			outMsg.setUserID(id);
			outMsg.setState(7); //id확인

			ObjectOutput oos = ClientHandler.oos;
			try {
				oos.writeObject(outMsg);
				System.out.println("pframe oos 실행2");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// 시간
		if(thirtyBt == e.getSource()) {
			time += 30;
			totalMoney += 400;
			if(time % 60 == 0) {
				timeL.setText(String.valueOf(time/60) + " : 00");
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}else {
				timeL.setText(String.valueOf(time/60) + " : " + String.valueOf(time%60));
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}
		}
		if(oneHourBt == e.getSource()) {
			time += 60;
			totalMoney += 700;
			if(time % 60 == 0) {
				timeL.setText(String.valueOf(time/60) + " : 00");
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}else {
				timeL.setText(String.valueOf(time/60) + " : " + String.valueOf(time%60));
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}
		}
		if(twoHourBt == e.getSource()) {
			time += 120;
			totalMoney += 1400;
			if(time % 60 == 0) {
				timeL.setText(String.valueOf(time/60) + " : 00");
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}else {
				timeL.setText(String.valueOf(time/60) + " : " + String.valueOf(time%60));
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}
		}
		if(fiveHourBt == e.getSource()) {
			time += 300;
			totalMoney += 3000;
			if(time % 60 == 0) {
				timeL.setText(String.valueOf(time/60) + " : 00");
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}else {
				timeL.setText(String.valueOf(time/60) + " : " + String.valueOf(time%60));
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}
		}
		if(tenHourBt == e.getSource()) {
			time += 600;
			totalMoney += 6000;
			if(time % 60 == 0) {
				timeL.setText(String.valueOf(time/60) + " : 00");
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}else {
				timeL.setText(String.valueOf(time/60) + " : " + String.valueOf(time%60));
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}
		}
		if(twentyHourBt == e.getSource()) {
			time += 1200;
			totalMoney += 14000;
			if(time % 60 == 0) {
				timeL.setText(String.valueOf(time/60) + " : 00");
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}else {
				timeL.setText(String.valueOf(time/60) + " : " + String.valueOf(time%60));
				totlaL.setText("<HTML><pre> ₩ " + String.valueOf(totalMoney) + "</pre></HTML>");	
			}
		}

		// 충전
		if(purchaseBt == e.getSource()) {
			if(idCheck==0){
				JOptionPane.showConfirmDialog(null, "ID를 확인해 주세요.", "경고", JOptionPane.DEFAULT_OPTION);
			}
			String id = idField.getText();
			Message outMsg = new Message();
			outMsg.setUserID(id);
			outMsg.setRemain(time);
			
			outMsg.setState(5); //purchase
			
			try {
				ObjectOutput oos = ClientHandler.oos;
				oos.writeObject(outMsg);
				oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//취소
		if(cancelBt == e.getSource()) {
			dispose();
		}
		
	}

	public void idCheckResultp(int idCheck) {
		if(idCheck ==1 ) {
			idCheck = 1;
			JOptionPane.showMessageDialog(null, "아이디가 확인되었습니다. 시간을 선택해 주세요.");
			idField.setEditable(false);
		}else {
			JOptionPane.showConfirmDialog(null, "존재하지 않는 아이디입니다.", "경고", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			idField.setText("");
			idField.requestFocus();
			idCheck = 0;
		}
	}
	
	public void purchaseCheckResult(int result) {
		if(result == 1) {
			JOptionPane.showMessageDialog(null, "시간 충전이 완료되었습니다.");
			dispose();

		}else {
			JOptionPane.showConfirmDialog(null, "충전에 실패하였습니다.", "경고", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
	}

	
//	public static void main(String[] args) {
//		new PurchaseFrame();
//	}
	
}