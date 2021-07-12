package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

import client.program.ClientHandler;
import message.Message;
import pcuser.UserVO;

public class LoginFrame extends JFrame implements ActionListener {
	
	JPanel loginPanel;

	JLabel loginL;

	JLabel idL;
	JTextField idField;

	JLabel pwdL;
	JPasswordField pwdField;

	JLabel seatL;
	JComboBox seatCB;

	JButton loginBt;
	JButton joinBt;
	JButton purchaseBt;
	JButton exitBt;
	
	JLabel inputLabel;

	public JoinFrame jFrame;
	public PurchaseFrame pFrame;
	public ClientFrame cFrame;
	
	String id;
	int seat;
	
	Font fTitleLabel = new Font("굴림", Font.BOLD, 25);
	Font fLabel = new Font("굴림", Font.PLAIN, 12);
	Font fBt = new Font("굴림", Font.PLAIN, 12);

	Color colorFrame = new Color(255, 255, 255);
	Color cBt = new Color(224, 224, 224);

	String[] seatNum = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20" };

	public LoginFrame() {
		this.setTitle("VIP LOGIN");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 300, 300);
		this.setLayout(null);
		setComponent();
		this.setVisible(true);
	}

	public void setComponent() {

		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBounds(0, 0, 300, 300);
		loginPanel.setBackground(Color.white);

		loginL = new JLabel("로 그 인");
		loginL.setFont(fTitleLabel);
		loginL.setHorizontalAlignment(SwingConstants.CENTER);
		loginL.setBounds(12, 10, 260, 33);

		idL = new JLabel("아이디 : ");
		idL.setFont(fLabel);
		idL.setHorizontalAlignment(SwingConstants.RIGHT);
		idL.setBounds(12, 62, 100, 15);

		idField = new JTextField();
		idL.setLabelFor(idField);
		idField.setBounds(128, 59, 116, 21);
		idField.setColumns(10);

		pwdL = new JLabel("비밀번호 : ");
		pwdL.setFont(fLabel);
		pwdL.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdL.setBounds(12, 102, 100, 15);

		pwdField = new JPasswordField();
		pwdL.setLabelFor(pwdField);
		pwdField.setColumns(10);
		pwdField.setBounds(128, 99, 116, 21);

		seatL = new JLabel("좌석 : ");
		seatL.setFont(fLabel);
		seatL.setHorizontalAlignment(SwingConstants.RIGHT);
		seatL.setBounds(12, 142, 100, 15);

		seatCB = new JComboBox();
		seatCB.setBackground(UIManager.getColor("Button.background"));
		seatCB.setModel(new DefaultComboBoxModel(seatNum));
		seatL.setLabelFor(seatCB);
		seatCB.setBounds(128, 137, 116, 25);

		loginBt = new JButton("로그인");
		loginBt.setFont(fBt);
		loginBt.setBounds(38, 181, 97, 23);
		loginBt.setBackground(cBt);
		loginBt.setBorderPainted(false);
		loginBt.setFocusPainted(false);

		joinBt = new JButton("회원가입");
		joinBt.setFont(fBt);
		joinBt.setBounds(147, 181, 97, 23);
		joinBt.setBackground(cBt);
		joinBt.setBorderPainted(false);
		joinBt.setFocusPainted(false);

		purchaseBt = new JButton("시간 충전");
		purchaseBt.setFont(fBt);
		purchaseBt.setBounds(38, 216, 97, 23);
		purchaseBt.setBackground(cBt);
		purchaseBt.setBorderPainted(false);
		purchaseBt.setFocusPainted(false);

		exitBt = new JButton("취소");
		exitBt.setFont(fBt);
		exitBt.setBounds(147, 216, 97, 23);
		exitBt.setBackground(cBt);
		exitBt.setBorderPainted(false);
		exitBt.setFocusPainted(false);

		loginPanel.add(loginL);
		loginPanel.add(idL);
		loginPanel.add(idField);
		loginPanel.add(pwdL);
		loginPanel.add(pwdField);
		loginPanel.add(seatL);
		loginPanel.add(seatCB);
		loginPanel.add(loginBt);
		loginPanel.add(joinBt);
		loginPanel.add(purchaseBt);
		loginPanel.add(exitBt);

		this.setContentPane(loginPanel);
		eventList();
	}

	public void eventList() {
		purchaseBt.addActionListener(this);
		exitBt.addActionListener(this);
		joinBt.addActionListener(this);
		loginBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (purchaseBt == e.getSource()) {
			pFrame = new PurchaseFrame();
		}
		if (exitBt == e.getSource()) {
			dispose();
		}
		if (joinBt == e.getSource()) {
			jFrame = new JoinFrame();
		}
				
		// 로그인
		if (loginBt == e.getSource()) {
			seat = Integer.parseInt(seatCB.getSelectedItem().toString());
			String userId = idField.getText();
			String password = pwdField.getText();

			if (userId.equals("") || password.equals("")) {
				JOptionPane.showConfirmDialog(null, "아이디 또는 비밀번호를 입력해 주세요.", "경고", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			Message outMsg = new Message();
			outMsg.setUserID(userId);
			outMsg.setPwd(password);
			outMsg.setSeatNum(seat);
			outMsg.setState(3); //login


			id = idField.getText();
			
			try {
				ClientHandler.oos.writeObject(outMsg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//history 남기기
		}
	}

	public void loginResult(int result, int remain) {

		if (result != 0) {
			cFrame = null;
			JOptionPane.showMessageDialog(null, "LOGIN 성공!");
			cFrame = new ClientFrame();
			dispose();

		} else {
			JOptionPane.showConfirmDialog(null, "존재하지 않는 계정입니다.", "경고", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE);

		}
	}

	public static void main(String[] args) {
		new LoginFrame();
	}
	
}