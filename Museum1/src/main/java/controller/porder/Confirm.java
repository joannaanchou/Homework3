package controller.porder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import model.Porder;
import service.impl.PorderServiceImpl;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Confirm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirm frame = new Confirm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public Confirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 575);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 222, 166));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(120, 183, 240));
		panel.setBounds(165, 38, 306, 63);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("訂單內容");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(249, 255, 228));
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 14));
		lblNewLabel.setBounds(90, 22, 129, 16);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(228, 181, 159));
		panel_1.setBounds(17, 117, 616, 410);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 50, 568, 295);
		panel_1.add(scrollPane);
		
		
		
		//顯示會員姓名及會員級別
		JLabel showMember = new JLabel("");
		showMember.setBounds(23, 6, 569, 34);
		panel_1.add(showMember);
		
		Member m = (Member) Tool.readFile("member.txt");
		showMember.setText("會員："+m.getName());
		
		
		//顯示訂單內容
		JTextArea showPorder = new JTextArea();
		showPorder.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		scrollPane.setViewportView(showPorder);

		Porder p=(Porder)Tool.readFile("porder.txt");
		
		String show= "【訂單內容】\n" +
				   "票券日期："+p.getDate()+
			       "\n單日票：" + p.getDaily() + "張x$299=$" + p.getDaily()*299 +
			       "\n季票：" + p.getSeason() + "張x$1899=$" + p.getSeason()*1899 +
			       "\n年票：" +p.getAnnual() + "張x$6999=$" + p.getAnnual()*6999 +
			       "\n_______________________________________" +
			       "\n總金額：$" +(p.getDaily()*299+p.getSeason()*1899+p.getAnnual()*6999);
				
				
		showPorder.setText(show);
		
	
		
		//BUTTON//
		JButton btnNewButton = new JButton("確認訂單");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new PorderServiceImpl().addPorder(p);
				Finish finish=new Finish();
				finish.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(120, 183, 240));
		btnNewButton.setBounds(475, 353, 117, 51);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重新選購");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddPorder addPorder=new AddPorder();
				addPorder.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1.setBackground(new Color(120, 183, 240));
		btnNewButton_1.setBounds(23, 353, 117, 51);
		panel_1.add(btnNewButton_1);

	}

}
