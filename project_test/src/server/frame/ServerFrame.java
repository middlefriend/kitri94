package server.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerFrame extends JFrame implements ActionListener {

	JPanel testPanel;
	JPanel seatPanel;
	JPanel chatPanel;

	JButton chatBt;

	JButton num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
	JButton num11, num12, num13, num14, num15, num16, num17, num18, num19, num20;

	JLabel vipL;
	JLabel seatL;
	JTextField chatF;
	JLabel chatL;

	public ServerFrame() {
		this.setTitle("vip pc room");
		this.setBounds(100, 100, 650, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		setComponent();
		this.setVisible(true);
	}

	public void setComponent() {
		testPanel = new JPanel();
		testPanel.setBounds(0, 0, 650, 500);
		testPanel.setLayout(null);
		testPanel.setBackground(Color.white);

		vipL = new JLabel("VIP PC ROOM");
		vipL.setFont(new Font("Cambria", Font.BOLD, 24));
		vipL.setBounds(10, 10, 500, 50);

		seatL = new JLabel("좌석현황");
		seatL.setBounds(10, 70, 500, 30);

		seatPanel = new JPanel();
		seatPanel.setBounds(10, 110, 300, 330);
		seatPanel.setBackground(new Color(224, 224, 224));
		seatPanel.setLayout(null);

		chatPanel = new JPanel();
		chatPanel.setBounds(320, 110, 300, 330);
		chatPanel.setBackground(new Color(224, 224, 224));
		chatPanel.setLayout(null);

		num1 = new JButton("1");
		num1.setBounds(20, 30, 50, 50);
		num1.setBackground(Color.lightGray);
		num1.setBorderPainted(false);
		num1.setFocusPainted(false);
		num2 = new JButton("2");
		num2.setBounds(70, 30, 50, 50);
		num2.setBackground(Color.lightGray);
		num2.setBorderPainted(false);
		num2.setFocusPainted(false);
		num3 = new JButton("3");
		num3.setBounds(120, 30, 50, 50);
		num3.setBackground(Color.lightGray);
		num3.setBorderPainted(false);
		num3.setFocusPainted(false);
		num4 = new JButton("4");
		num4.setBounds(170, 30, 50, 50);
		num4.setBackground(Color.lightGray);
		num4.setBorderPainted(false);
		num4.setFocusPainted(false);
		num5 = new JButton("5");
		num5.setBounds(220, 30, 50, 50);
		num5.setBackground(Color.lightGray);
		num5.setBorderPainted(false);
		num5.setFocusPainted(false);
		num6 = new JButton("6");
		num6.setBounds(20, 110, 50, 50);
		num6.setBackground(Color.lightGray);
		num6.setBorderPainted(false);
		num6.setFocusPainted(false);
		num7 = new JButton("7");
		num7.setBounds(70, 110, 50, 50);
		num7.setBackground(Color.lightGray);
		num7.setBorderPainted(false);
		num7.setFocusPainted(false);
		num8 = new JButton("8");
		num8.setBounds(120, 110, 50, 50);
		num8.setBackground(Color.lightGray);
		num8.setBorderPainted(false);
		num8.setFocusPainted(false);
		num9 = new JButton("9");
		num9.setBounds(170, 110, 50, 50);
		num9.setBackground(Color.lightGray);
		num9.setBorderPainted(false);
		num9.setFocusPainted(false);
		num10 = new JButton("10");
		num10.setBounds(220, 110, 50, 50);
		num10.setBackground(Color.lightGray);
		num10.setBorderPainted(false);
		num10.setFocusPainted(false);
		num11 = new JButton("11");
		num11.setBounds(20, 170, 50, 50);
		num11.setBackground(Color.lightGray);
		num11.setBorderPainted(false);
		num11.setFocusPainted(false);
		num12 = new JButton("12");
		num12.setBounds(70, 170, 50, 50);
		num12.setBackground(Color.lightGray);
		num12.setBorderPainted(false);
		num12.setFocusPainted(false);
		num13 = new JButton("13");
		num13.setBounds(120, 170, 50, 50);
		num13.setBackground(Color.lightGray);
		num13.setBorderPainted(false);
		num13.setFocusPainted(false);
		num14 = new JButton("14");
		num14.setBounds(170, 170, 50, 50);
		num14.setBackground(Color.lightGray);
		num14.setBorderPainted(false);
		num14.setFocusPainted(false);
		num15 = new JButton("15");
		num15.setBounds(220, 170, 50, 50);
		num15.setBackground(Color.lightGray);
		num15.setBorderPainted(false);
		num15.setFocusPainted(false);
		num16 = new JButton("16");
		num16.setBounds(20, 250, 50, 50);
		num16.setBackground(Color.lightGray);
		num16.setBorderPainted(false);
		num16.setFocusPainted(false);
		num17 = new JButton("17");
		num17.setBounds(70, 250, 50, 50);
		num17.setBackground(Color.lightGray);
		num17.setBorderPainted(false);
		num17.setFocusPainted(false);
		num18 = new JButton("18");
		num18.setBounds(120, 250, 50, 50);
		num18.setBackground(Color.lightGray);
		num18.setBorderPainted(false);
		num18.setFocusPainted(false);
		num19 = new JButton("19");
		num19.setBounds(170, 250, 50, 50);
		num19.setBackground(Color.lightGray);
		num19.setBorderPainted(false);
		num19.setFocusPainted(false);
		num20 = new JButton("20");
		num20.setBounds(220, 250, 50, 50);
		num20.setBackground(Color.lightGray);
		num20.setBorderPainted(false);
		num20.setFocusPainted(false);

		chatL = new JLabel();
		chatL.setBounds(0, 0, 300, 300);

		chatF = new JTextField();
		chatF.setBounds(0, 300, 230, 30);

		chatBt = new JButton("send");
		chatBt.setBounds(230, 300, 70, 30);
		chatBt.setBackground(Color.lightGray);
		chatBt.setBorderPainted(false);
		chatBt.setFocusPainted(false);

		seatPanel.add(num1);
		seatPanel.add(num2);
		seatPanel.add(num3);
		seatPanel.add(num4);
		seatPanel.add(num5);
		seatPanel.add(num6);
		seatPanel.add(num7);
		seatPanel.add(num8);
		seatPanel.add(num9);
		seatPanel.add(num10);
		seatPanel.add(num11);
		seatPanel.add(num12);
		seatPanel.add(num13);
		seatPanel.add(num14);
		seatPanel.add(num15);
		seatPanel.add(num16);
		seatPanel.add(num17);
		seatPanel.add(num18);
		seatPanel.add(num19);
		seatPanel.add(num20);

		chatPanel.add(chatL);
		chatPanel.add(chatBt);
		chatPanel.add(chatF);

		testPanel.add(vipL);
		testPanel.add(seatL);
		testPanel.add(seatPanel);
		testPanel.add(chatPanel);

		this.setContentPane(testPanel);
		eventList();

	}

	public void eventList() {
		num1.addActionListener(this);
		num2.addActionListener(this);
		num3.addActionListener(this);
		num4.addActionListener(this);
		num5.addActionListener(this);
		num6.addActionListener(this);
		num7.addActionListener(this);
		num8.addActionListener(this);
		num9.addActionListener(this);
		num10.addActionListener(this);
		num11.addActionListener(this);
		num12.addActionListener(this);
		num13.addActionListener(this);
		num14.addActionListener(this);
		num15.addActionListener(this);
		num16.addActionListener(this);
		num17.addActionListener(this);
		num18.addActionListener(this);
		num19.addActionListener(this);
		num20.addActionListener(this);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerFrame();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}