package client.frame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JoinFrame extends JFrame implements ActionListener{

	private JFrame frmVipJoin;
	private JTextField idF;
	private JTextField pwdF;
	private JTextField pwdOkF;
	private JTextField textField;
	private JLabel joinL;
	private JLabel idL;
	private JButton idCheckBt;
	private JLabel idCheckL;
	private JLabel pwdL;
	private JLabel pwdCheckL;
	private JLabel pwdOkL;
	private JLabel pwdOkCheckL;
	private JLabel pwdOkL_1;
	private JButton joinBt;
	private JButton exitBt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinFrame window = new JoinFrame();
					window.frmVipJoin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JoinFrame() {
		initialize();
		eventList();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		Font fTitleLabel = new Font("굴림", Font.BOLD, 25);
		Font fLabel = new Font("굴림", Font.PLAIN, 12);
		Font fBt = new Font("굴림", Font.PLAIN, 12);
		
		Color cFrame = new Color(255, 255, 255);
		Color cBt = new Color(224, 224, 224);
		
		frmVipJoin = new JFrame();
		frmVipJoin.setTitle("VIP JOIN");
		frmVipJoin.getContentPane().setBackground(cFrame);
		frmVipJoin.setBounds(100, 100, 400, 400);
		frmVipJoin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVipJoin.getContentPane().setLayout(null);
		
		joinL = new JLabel("회 원 가 입");
		joinL.setFont(fTitleLabel);
		joinL.setHorizontalAlignment(SwingConstants.CENTER);
		joinL.setBounds(10, 12, 360, 33);
		frmVipJoin.getContentPane().add(joinL);
		
		idL = new JLabel("아이디 : ");
		idL.setHorizontalAlignment(SwingConstants.RIGHT);
		idL.setFont(fLabel);
		idL.setBounds(12, 89, 90, 15);
		frmVipJoin.getContentPane().add(idL);
		
		idF = new JTextField();
		idF.setToolTipText("");
		idL.setLabelFor(idF);
		idF.setColumns(10);
		idF.setBounds(107, 86, 130, 21);
		frmVipJoin.getContentPane().add(idF);
		
		idCheckBt = new JButton("중복확인");
		idCheckBt.setFont(fBt);
		idCheckBt.setBounds(249, 85, 97, 23);
		idCheckBt.setBackground(cBt);
		idCheckBt.setBorderPainted(false);
		idCheckBt.setFocusPainted(false);
		
		frmVipJoin.getContentPane().add(idCheckBt);
		
		idCheckL = new JLabel("아이디 중복 여부를 확인 해주세요");
		idCheckL.setLabelFor(idF);
		idCheckL.setFont(fLabel);
		idCheckL.setForeground(Color.BLACK);
		idCheckL.setBounds(107, 117, 239, 15);
		frmVipJoin.getContentPane().add(idCheckL);
		
		pwdL = new JLabel("비밀번호 : ");
		pwdL.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdL.setFont(fLabel);
		pwdL.setBounds(12, 146, 90, 15);
		frmVipJoin.getContentPane().add(pwdL);
		
		pwdF = new JTextField();
		pwdL.setLabelFor(pwdF);
		pwdF.setColumns(10);
		pwdF.setBounds(107, 143, 239, 21);
		frmVipJoin.getContentPane().add(pwdF);
		
		pwdCheckL = new JLabel("비밀번호를 입력하세요");
		pwdCheckL.setLabelFor(pwdF);
		pwdCheckL.setForeground(Color.BLACK);
		pwdCheckL.setFont(fLabel);
		pwdCheckL.setBounds(107, 174, 239, 15);
		frmVipJoin.getContentPane().add(pwdCheckL);
		
		pwdOkL = new JLabel("비밀번호확인 : ");
		pwdOkL.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdOkL.setFont(fLabel);
		pwdOkL.setBounds(12, 202, 90, 15);
		frmVipJoin.getContentPane().add(pwdOkL);
		
		pwdOkF = new JTextField();
		pwdOkL.setLabelFor(pwdOkF);
		pwdOkF.setColumns(10);
		pwdOkF.setBounds(107, 199, 239, 21);
		frmVipJoin.getContentPane().add(pwdOkF);
		
		pwdOkCheckL = new JLabel("비밀번호를 입력하세요");
		pwdOkCheckL.setLabelFor(pwdOkF);
		pwdOkCheckL.setForeground(Color.BLACK);
		pwdOkCheckL.setFont(fLabel);
		pwdOkCheckL.setBounds(107, 230, 239, 15);
		frmVipJoin.getContentPane().add(pwdOkCheckL);
		
		pwdOkL_1 = new JLabel("이름 : ");
		pwdOkL_1.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdOkL_1.setFont(fLabel);
		pwdOkL_1.setBounds(12, 258, 90, 15);
		frmVipJoin.getContentPane().add(pwdOkL_1);
		
		textField = new JTextField();
		pwdOkL_1.setLabelFor(textField);
		textField.setColumns(10);
		textField.setBounds(107, 255, 239, 21);
		frmVipJoin.getContentPane().add(textField);
		
		joinBt = new JButton("회원가입");
		joinBt.setFont(fBt);
		joinBt.setBounds(90, 307, 97, 23);
		joinBt.setBackground(cBt);
		joinBt.setBorderPainted(false);
		joinBt.setFocusPainted(false);
		frmVipJoin.getContentPane().add(joinBt);
		
		exitBt = new JButton("취소");
		exitBt.setFont(fBt);
		exitBt.setBounds(199, 307, 97, 23);
		exitBt.setBackground(cBt);
		exitBt.setBorderPainted(false);
		exitBt.setFocusPainted(false);
		frmVipJoin.getContentPane().add(exitBt);
	}
	
	public void eventList() {
		joinBt.addActionListener(this);
		exitBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(exitBt == e.getSource()) {
			frmVipJoin.dispose();
		}
	}

}
