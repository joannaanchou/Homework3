package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import service.impl.MemberServiceImpl;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTextField address;
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
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
	public AddMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 485);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 244, 218));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("註冊會員");
		lblNewLabel.setBackground(new Color(228, 181, 159));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(172, 39, 246, 73);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(228, 181, 159));
		panel.setBounds(172, 39, 246, 73);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("姓名:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(172, 145, 75, 33);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(240, 145, 178, 37);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("帳號:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(172, 192, 75, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("密碼:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(172, 241, 75, 33);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("地址:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(172, 291, 75, 33);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("電話:");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(172, 338, 75, 33);
		contentPane.add(lblNewLabel_1_4);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(240, 190, 178, 37);
		contentPane.add(username);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(240, 239, 178, 37);
		contentPane.add(password);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(240, 288, 178, 37);
		contentPane.add(address);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(240, 337, 178, 37);
		contentPane.add(phone);
		
		
		//BUTTON//
		JButton AddMemberButton = new JButton("確認");
		AddMemberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Name=name.getText();
				String Username=username.getText();
				String Password=password.getText();
				String Address=address.getText();
				String Phone=phone.getText();
				Member member=new Member(Name,Username,Password,Address,Phone);
				
				if(new MemberServiceImpl().addMember(member))
				{
					Tool.saveFile(member, "member.txt");
					AddMemberSuccess addMembersuccess=new AddMemberSuccess();
					addMembersuccess.setVisible(true);
					dispose();
				}else { 
					JOptionPane.showMessageDialog(
							null,
							"此帳號已被使用，請使用其他帳號",
							"錯誤",
							JOptionPane.ERROR_MESSAGE
						);
						name.setText("");
						username.setText("");
						password.setText("");
						address.setText("");
						phone.setText("");
					
				}
			}
		});
		AddMemberButton.setBackground(new Color(228, 181, 159));
		AddMemberButton.setFont(new Font("Dialog", Font.BOLD, 16));
		AddMemberButton.setBounds(187, 398, 231, 37);
		contentPane.add(AddMemberButton);

	}

}
