package server.frame;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.io.*;
import java.net.Socket;
import java.util.Iterator;

import message.Message;
import server.program.Server;
import server.program.ServerHandler;
//import client.program.ClientHandler;


public class TestFrameServer extends JFrame {

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
