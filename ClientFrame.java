package client.frame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ClientFrame extends JFrame implements ActionListener, MouseListener{

	private JFrame frmVipPcRoom;
	private JTextField textField;
	
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JLabel pcroomNameL;
	private JLabel notice1Label;
	private JLabel notice2Label;
	private JLabel noticeTimeL;
	private JComboBox seatCB;
	
	private JButton btnNewButton;
	private JButton logoutBt;
	private JButton purchaseBt;
	
	private String[] seatNum =  {"1","2","3","4","5","6","7","8","9","10",
			"11","12","13","14","15","16","17","18","19","20"};

	static LoginFrame login;
	static String id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame(login, id);
					window.frmVipPcRoom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrame(LoginFrame login, String id) {
		this.login = login;
		this.id = id;
		initialize();
		eventList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		Font fTitleLabel = new Font("Comic Sans MS", Font.BOLD, 30);
		Font fLabel = new Font("굴림", Font.PLAIN, 12);
		Font fBt = new Font("굴림", Font.PLAIN, 12);
		
		frmVipPcRoom = new JFrame();
		frmVipPcRoom.setTitle("VIP PC ROOM");
		frmVipPcRoom.getContentPane().setBackground(new Color(255, 255, 255));
		frmVipPcRoom.setBounds(100, 100, 500, 600);
		frmVipPcRoom.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVipPcRoom.getContentPane().setLayout(null);
		
		pcroomNameL = new JLabel("VIP PC ROOM");
		pcroomNameL.setFont(fTitleLabel);
		pcroomNameL.setBounds(30, 20, 266, 35);
		frmVipPcRoom.getContentPane().add(pcroomNameL);
		
		notice1Label = new JLabel("님 환영합니다!");
		notice1Label.setFont(fLabel);
		notice1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		notice1Label.setBounds(308, 20, 164, 15);
		frmVipPcRoom.getContentPane().add(notice1Label);
		
		notice2Label = new JLabel("잔여시간 : ");
		notice2Label.setFont(fLabel);
		notice2Label.setBounds(308, 39, 61, 15);
		frmVipPcRoom.getContentPane().add(notice2Label);
		
		noticeTimeL = new JLabel("");
		noticeTimeL.setFont(fLabel);
		noticeTimeL.setBounds(381, 39, 91, 15);
		frmVipPcRoom.getContentPane().add(noticeTimeL);
		
		seatCB = new JComboBox();
		seatCB.setMaximumRowCount(5);
		seatCB.setModel(new DefaultComboBoxModel(seatNum));
		seatCB.setBackground(new Color(224, 224, 224));
		seatCB.setBounds(30, 88, 80, 25);
		frmVipPcRoom.getContentPane().add(seatCB);
		
		btnNewButton = new JButton("자리이동");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(fBt);
		btnNewButton.setBounds(125, 80, 100, 40);
		btnNewButton.setBackground(new Color(224, 224, 224));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		frmVipPcRoom.getContentPane().add(btnNewButton);
		
		logoutBt = new JButton("로그아웃");
		logoutBt.setFont(fBt);
		logoutBt.setBounds(240, 80, 100, 40);
		logoutBt.setBackground(new Color(224, 224, 224));
		logoutBt.setBorderPainted(false);
		logoutBt.setFocusPainted(false);
		frmVipPcRoom.getContentPane().add(logoutBt);
		
		purchaseBt = new JButton("시간 충전");
		purchaseBt.setFont(fBt);
		purchaseBt.setBounds(355, 80, 100, 40);
		purchaseBt.setBackground(new Color(224, 224, 224));
		purchaseBt.setBorderPainted(false);
		purchaseBt.setFocusPainted(false);
		frmVipPcRoom.getContentPane().add(purchaseBt);
		textArea.setBackground(new Color(240, 255, 240));
		
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(240,248,255));
		scrollPane.setBounds(30, 140, 425, 370);
		frmVipPcRoom.getContentPane().add(scrollPane);
		
		textField = new JTextField();
		textField.setBackground(new Color(240, 240, 240));
		textField.setForeground(Color.BLACK);
		textField.setBounds(30, 509, 425, 30);
		frmVipPcRoom.getContentPane().add(textField);
		textField.setColumns(10);
		
		
	}
	
	public void eventList() {
		btnNewButton.addActionListener(this);
		logoutBt.addActionListener(this);
		purchaseBt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (logoutBt == e.getSource()) { // 로그아웃
			id = null;
			
			login.setVisible(true);
			dispose();
			
		} 
		
	}
	public void addActionListener(ActionListener l) {

	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
