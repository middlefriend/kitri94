package client.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.Socket;

import client.program.ClientHandler;
import message.Message;

public class TestFrameClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel labelTest;

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
		
		labelTest = new JLabel("label");
		labelTest.setBounds(53, 33, 300, 102);
		labelTest.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelTest);
		
		JButton btn = new JButton("send");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message outMsg = new Message();
				outMsg.setStr(textField.getText());
				try {
					System.out.println(ClientHandler.oos);
					ClientHandler.oos.writeObject(outMsg);
					ClientHandler.oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn.setBounds(325, 191, 97, 23);
		contentPane.add(btn);
		
		textField = new JTextField();
		textField.setBounds(20, 188, 300, 30);
		contentPane.add(textField);
		textField.setColumns(10);
	}

	public void updateLabel(String str) {
		labelTest.setText(str);
	}
}
