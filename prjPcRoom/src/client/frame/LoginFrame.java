package client.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {

	private JFrame frmVipLogin;
	private JTextField idField;
	private JTextField pwdField;
	private JButton loginBt;
	private JButton joinBt;
	private JButton purchaseBt;
	private JButton exitBt;
	private JLabel loginL;
	private JLabel idL;
	private JLabel pwdL;
	private JLabel seatL;
	private JComboBox seatCB;

	public JoinFrame jFrame;
//   public PurchaseFrame pFrame;

	private String[] seatNum = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frmVipLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		initialize();
		eventList();
	}

	public void initialize() {
		
		Font fTitleLabel = new Font("굴림", Font.BOLD, 25);
		Font fLabel = new Font("굴림", Font.PLAIN, 12);
		Font fBt = new Font("굴림", Font.PLAIN, 12);

		Color cFrame = new Color(255, 255, 255);
		Color cBt = new Color(224, 224, 224);

		frmVipLogin = new JFrame();
		frmVipLogin.setTitle("VIP LOGIN");
		frmVipLogin.getContentPane().setBackground(cFrame);
		frmVipLogin.setBounds(100, 100, 300, 300);
		frmVipLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVipLogin.getContentPane().setLayout(null);

		loginL = new JLabel("로 그 인");
		loginL.setFont(fTitleLabel);
		loginL.setHorizontalAlignment(SwingConstants.CENTER);
		loginL.setBounds(12, 10, 260, 33);
		frmVipLogin.getContentPane().add(loginL);

		idL = new JLabel("아이디 : ");
		idL.setFont(fLabel);
		idL.setHorizontalAlignment(SwingConstants.RIGHT);
		idL.setBounds(12, 62, 100, 15);
		frmVipLogin.getContentPane().add(idL);

		idField = new JTextField();
		idL.setLabelFor(idField);
		idField.setBounds(128, 59, 116, 21);
		frmVipLogin.getContentPane().add(idField);
		idField.setColumns(10);

		pwdL = new JLabel("비밀번호 : ");
		pwdL.setFont(fLabel);
		pwdL.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdL.setBounds(12, 102, 100, 15);
		frmVipLogin.getContentPane().add(pwdL);

		pwdField = new JTextField();
		pwdL.setLabelFor(pwdField);
		pwdField.setColumns(10);
		pwdField.setBounds(128, 99, 116, 21);
		frmVipLogin.getContentPane().add(pwdField);

		seatL = new JLabel("좌석 : ");
		seatL.setFont(fLabel);
		seatL.setHorizontalAlignment(SwingConstants.RIGHT);
		seatL.setBounds(12, 142, 100, 15);
		frmVipLogin.getContentPane().add(seatL);

		seatCB = new JComboBox();
		seatCB.setBackground(UIManager.getColor("Button.background"));
		seatCB.setModel(new DefaultComboBoxModel(seatNum));
		seatL.setLabelFor(seatCB);
		seatCB.setBounds(128, 137, 116, 25);
		frmVipLogin.getContentPane().add(seatCB);

		loginBt = new JButton("로그인");
		loginBt.setFont(fBt);
		loginBt.setBounds(38, 181, 97, 23);
		loginBt.setBackground(cBt);
		loginBt.setBorderPainted(false);
		loginBt.setFocusPainted(false);
		frmVipLogin.getContentPane().add(loginBt);

		joinBt = new JButton("회원가입");
		joinBt.setFont(fBt);
		joinBt.setBounds(147, 181, 97, 23);
		joinBt.setBackground(cBt);
		joinBt.setBorderPainted(false);
		joinBt.setFocusPainted(false);
		frmVipLogin.getContentPane().add(joinBt);

		purchaseBt = new JButton("시간 충전");
		purchaseBt.setFont(fBt);
		purchaseBt.setBounds(38, 216, 97, 23);
		purchaseBt.setBackground(cBt);
		purchaseBt.setBorderPainted(false);
		purchaseBt.setFocusPainted(false);
		frmVipLogin.getContentPane().add(purchaseBt);

		exitBt = new JButton("취소");
		exitBt.setFont(fBt);
		exitBt.setBounds(147, 216, 97, 23);
		exitBt.setBackground(cBt);
		exitBt.setBorderPainted(false);
		exitBt.setFocusPainted(false);
		frmVipLogin.getContentPane().add(exitBt);
		
		idField.setText("");
		pwdField.setText("");
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
//         pFrame = new PurchaseFrame();
		}
		if (exitBt == e.getSource()) {
			frmVipLogin.dispose();
		}
		if (joinBt == e.getSource()) {
			jFrame = new JoinFrame();
		}

		// 로그인
		if (loginBt == e.getSource()) {
			
			
			idField.setText("");
			pwdField.setText("");
		}
	}

}