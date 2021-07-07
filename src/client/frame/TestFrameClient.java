package client.frame;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

import client.program.ClientHandler;
import message.Message;

@SuppressWarnings("serial")
public class TestFrameClient extends JFrame {

	private JPanel contentPane;
	private JTextField statField;
	private JTextField seatField;
	private JTextField remainField;
	private JTextField strField;
	private JLabel inputLabel;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TestFrameClient frame = new TestFrameClient();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TestFrameClient() {
		this.setVisible(true);
		this.setTitle("client");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputLabel = new JLabel("label");
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setBounds(63, 10, 300, 30);
		contentPane.add(inputLabel);
		
		JLabel statLabel = new JLabel("stat");
		statLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statLabel.setBounds(0, 49, 73, 30);
		contentPane.add(statLabel);
		
		statField = new JTextField();
		statField.setColumns(10);
		statField.setBounds(73, 50, 349, 30);
		contentPane.add(statField);
		
		JLabel seatLabel = new JLabel("seat");
		seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		seatLabel.setBounds(0, 89, 73, 30);
		contentPane.add(seatLabel);
		
		seatField = new JTextField();
		seatField.setColumns(10);
		seatField.setBounds(73, 90, 349, 30);
		contentPane.add(seatField);
		
		JLabel remainLabel = new JLabel("remain");
		remainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		remainLabel.setBounds(0, 129, 73, 30);
		contentPane.add(remainLabel);
		
		remainField = new JTextField();
		remainField.setColumns(10);
		remainField.setBounds(73, 130, 349, 30);
		contentPane.add(remainField);
		
		JLabel strLabel = new JLabel("str");
		strLabel.setHorizontalAlignment(SwingConstants.CENTER);
		strLabel.setBounds(0, 169, 73, 30);
		contentPane.add(strLabel);
		
		strField = new JTextField();
		strField.setColumns(10);
		strField.setBounds(73, 170, 349, 30);
		contentPane.add(strField);
		
		JButton btn = new JButton("send");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message outMsg = new Message();
				outMsg.setState(Integer.parseInt(statField.getText()));
				outMsg.setSeat(Integer.parseInt(seatField.getText()));
				outMsg.setStr(strField.getText());
				outMsg.setRemain(Integer.parseInt(remainField.getText()));
				try {
					ObjectOutput oos = ClientHandler.oos;
					System.out.println(oos);
					oos.writeObject(outMsg);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn.setBounds(325, 228, 97, 23);
		contentPane.add(btn);
	}

	public void updateLabel(String str) {
		inputLabel.setText(str);
	}
	public void alert10min() {
		JOptionPane.showConfirmDialog(null, "종료 10분 전입니다.","경고",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
	}
}
