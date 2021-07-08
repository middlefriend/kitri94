package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class JoinFrame extends JFrame implements ActionListener, KeyListener {

	JPanel joinPanel;

	JLabel joinL;

	JLabel idL;
	JTextField idF;
	JButton idCheckBt;
	JLabel idCheckL;

	JLabel pwdL;
	JTextField pwdF;
	JLabel pwdOkL;

	JLabel pwdCheckL;
	JTextField pwdOkF;
	JLabel pwdOkCheckL;

	JLabel nameL;
	JTextField nameF;

	JButton joinBt;
	JButton exitBt;
	
	Font fTitleLabel = new Font("굴림", Font.BOLD, 25);
    Font fLabel = new Font("굴림", Font.PLAIN, 12);
    Font fBt = new Font("굴림", Font.PLAIN, 12);
    
    Color cFrame = new Color(255, 255, 255);
    Color cBt = new Color(224, 224, 224);

    int idCheck;
    int pwdCheck;
    
    public ClientFrame clientFrame;
    
	public JoinFrame() {
		this.setTitle("회원 가입");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 400);
		this.setLayout(null);
		setComponent();
		this.setVisible(true);
	}

	public void setComponent() {
		joinPanel = new JPanel();
		joinPanel.setLayout(null);
		joinPanel.setBounds(0, 0, 400, 400);
		joinPanel.setBackground(Color.white);

		joinL = new JLabel("회 원 가 입");
		joinL.setFont(fTitleLabel);
		joinL.setHorizontalAlignment(SwingConstants.CENTER);
		joinL.setBounds(10, 12, 360, 33);

		idL = new JLabel("아이디 : ");
		idL.setHorizontalAlignment(SwingConstants.RIGHT);
		idL.setFont(fLabel);
		idL.setBounds(12, 89, 90, 15);
		idF = new JTextField();
		idF.setToolTipText("");
		idL.setLabelFor(idF);
		idF.setColumns(10);
		idF.setBounds(107, 86, 130, 21);
		idCheckBt = new JButton("중복확인");
		idCheckBt.setFont(fBt);
		idCheckBt.setBounds(249, 85, 97, 23);
		idCheckBt.setBackground(cBt);
		idCheckBt.setBorderPainted(false);
		idCheckBt.setFocusPainted(false);
		
		idCheckL = new JLabel();
		idCheckL.setLabelFor(idF);
		idCheckL.setFont(fLabel);
		idCheckL.setForeground(Color.BLACK);
		idCheckL.setBounds(107, 117, 239, 15);
		
		pwdL = new JLabel("비밀번호 : ");
		pwdL.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdL.setFont(fLabel);
		pwdL.setBounds(12, 146, 90, 15);
		pwdL.setLabelFor(pwdF);
		
		pwdF = new JTextField();
		pwdF.setColumns(10);
		pwdF.setBounds(107, 143, 239, 21);
		
		pwdCheckL = new JLabel("비밀번호를 입력하세요");
		pwdCheckL.setLabelFor(pwdF);
		pwdCheckL.setForeground(Color.BLACK);
		pwdCheckL.setFont(fLabel);
		pwdCheckL.setBounds(107, 174, 239, 15);
		
		pwdOkL = new JLabel("비밀번호확인 : ");
		pwdOkL.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdOkL.setFont(fLabel);
		pwdOkL.setBounds(12, 202, 90, 15);
		pwdOkL.setLabelFor(pwdOkF);
		
		pwdOkF = new JTextField();
		pwdOkF.setColumns(10);
		pwdOkF.setBounds(107, 199, 239, 21);
		
		pwdOkCheckL = new JLabel();
		pwdOkCheckL.setLabelFor(pwdOkF);
		pwdOkCheckL.setForeground(Color.BLACK);
		pwdOkCheckL.setFont(fLabel);
		pwdOkCheckL.setBounds(107, 230, 239, 15);

		nameL = new JLabel("이름 : ");
		nameL.setHorizontalAlignment(SwingConstants.RIGHT);
		nameL.setFont(fLabel);
		nameL.setBounds(12, 258, 90, 15);
		nameL.setLabelFor(nameF);
		nameF = new JTextField();
		nameF.setColumns(10);
		nameF.setBounds(107, 255, 239, 21);

		joinBt = new JButton("회원가입");
		joinBt.setFont(fBt);
		joinBt.setBounds(90, 307, 97, 23);
		joinBt.setBackground(cBt);
		joinBt.setBorderPainted(false);
		joinBt.setFocusPainted(false);

		exitBt = new JButton("취소");
		exitBt.setFont(fBt);
		exitBt.setBounds(199, 307, 97, 23);
		exitBt.setBackground(cBt);
		exitBt.setBorderPainted(false);
		exitBt.setFocusPainted(false);
		
		joinPanel.add(joinL);
		joinPanel.add(idL);
		joinPanel.add(idF);
		joinPanel.add(idCheckBt);
		joinPanel.add(idCheckL);
		joinPanel.add(pwdL);
		joinPanel.add(pwdF);
		joinPanel.add(pwdCheckL);
		joinPanel.add(pwdOkL);
		joinPanel.add(pwdOkF);
		joinPanel.add(pwdOkCheckL);
		joinPanel.add(nameL);
		joinPanel.add(nameF);
		joinPanel.add(joinBt);
		joinPanel.add(exitBt);
		
		this.setContentPane(joinPanel);
		eventList();
	}

	public void eventList() {
		idCheckBt.addActionListener(this);
		idCheckL.addKeyListener(this);
		pwdOkF.addKeyListener(this);
		joinBt.addActionListener(this);
		exitBt.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ID확인 버튼
		if (idCheckBt == e.getSource()) { // ID 공백
			String id = idF.getText();
			if (id.equals("")) {
				JOptionPane.showConfirmDialog(null, "ID를 입력해 주세요.", "경고", JOptionPane.DEFAULT_OPTION);
				idF.requestFocus();
				return;
			}
			// ID 값 받아서 비교
		}
		//ID 사용 가능 여부 출력
		if(idCheck == 1) {
			idCheckL.setForeground(Color.black);
			idCheckL.setText("사용 가능한 아이디입니다.");
		}else if (idCheck == 0) {
			idCheckL.setForeground(Color.red);
			idCheckL.setText("이미 존재하는 아이디입니다.");
		}
		
		//회원가입 버튼
		if(joinBt == e.getSource()) {
			if(idCheck==0) {
				JOptionPane.showConfirmDialog(null, "사용 가능한 ID를 입력해 주세요.", "경고", JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwdF.getText().equals("") ) {
				JOptionPane.showConfirmDialog(null, "비밀번호를 입력해 주세요.", "경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdF.requestFocus();
				return;
			}
			if(pwdOkF.getText().equals("") ) {
				JOptionPane.showConfirmDialog(null, "비밀번호를 확인해 주세요", "경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				pwdF.requestFocus();
				return;
			}
			if(pwdCheck==0 ) {
				JOptionPane.showConfirmDialog(null, "비밀번호가 일치하지 않습니다.", "경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(nameF.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "이름을 입력해 주세요", "경고", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			clientFrame = new ClientFrame();
		}
		//받은 정보 보내기

		//취소 버튼
		if(exitBt == e.getSource()) {
			dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		String pwd = pwdF.getText();
		String pwdOk = pwdOkF.getText();
		if(pwd.equals(pwdOk)) {
			pwdOkCheckL.setForeground(Color.black);
			pwdOkCheckL.setText("비밀번호가 일치합니다.");
			pwdCheck=1;
		}else {
			pwdOkCheckL.setForeground(Color.red);
			pwdOkCheckL.setText("비밀번호가 일치하지 않습니다.");
			pwdCheck=0;
		}
	}

	public static void main(String[] args) {
		new JoinFrame();
	}

}
