package server.frame;


import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.*;
import java.util.Iterator;

import message.Message;
import server.program.*;

@SuppressWarnings("serial")
public class TestFrameServer extends JFrame {

	private JPanel contentPane;
	private JTextField statField;
	private JLabel inputLabel;
	private JTextField seatField;
	private JTextField strField;
	private JTextField remainField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TestFrameServer frame = new TestFrameServer();
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
	public TestFrameServer() {
		
		this.setVisible(true);
		this.setTitle("server");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputLabel = new JLabel("label");
		inputLabel.setBounds(63, 10, 300, 30);
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(inputLabel);
		
		JButton btn = new JButton("send");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message outMsg = new Message();
				outMsg.setState(Integer.parseInt(statField.getText()));
				outMsg.setSeat(Integer.parseInt(seatField.getText()));
				outMsg.setUserId(strField.getText());
				outMsg.setRemain(Integer.parseInt(remainField.getText()));
				try {
					Iterator<ServerHandler> it = Server.serverSet.iterator();
					while(it.hasNext()) {
						ObjectOutput oos = it.next().oos;
						System.out.println(oos);
						oos.writeObject(outMsg);
						oos.flush();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn.setBounds(325, 228, 97, 23);
		contentPane.add(btn);
		
		statField = new JTextField();
		statField.setBounds(73, 50, 349, 30);
		contentPane.add(statField);
		statField.setColumns(10);
		
		JLabel statLabel = new JLabel("stat");
		statLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statLabel.setBounds(0, 49, 73, 30);
		contentPane.add(statLabel);
		
		JLabel seatLabel = new JLabel("seat");
		seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		seatLabel.setBounds(0, 89, 73, 30);
		contentPane.add(seatLabel);
		
		JLabel strLabel = new JLabel("str");
		strLabel.setHorizontalAlignment(SwingConstants.CENTER);
		strLabel.setBounds(0, 169, 73, 30);
		contentPane.add(strLabel);
		
		seatField = new JTextField();
		seatField.setColumns(10);
		seatField.setBounds(73, 90, 349, 30);
		contentPane.add(seatField);
		
		strField = new JTextField();
		strField.setColumns(10);
		strField.setBounds(73, 170, 349, 30);
		contentPane.add(strField);
		
		JLabel remainLabel = new JLabel("remain");
		remainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		remainLabel.setBounds(0, 129, 73, 30);
		contentPane.add(remainLabel);
		
		remainField = new JTextField();
		remainField.setColumns(10);
		remainField.setBounds(73, 130, 349, 30);
		contentPane.add(remainField);
	}

	public void updateLabel(String str) {
		inputLabel.setText(str);
	}
}
