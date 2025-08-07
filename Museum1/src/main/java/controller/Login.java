package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.member.AddMember;
import controller.member.LoginError;
import controller.member.LoginSuccess;
import model.Member;
import service.impl.MemberServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 475);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 181, 159));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(120, 183, 240));
		panel.setBounds(127, 33, 330, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MONA MUSEUM");
		lblNewLabel.setBackground(new Color(120, 183, 240));
		lblNewLabel.setForeground(new Color(249, 255, 228));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 6, 278, 46);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("帳號：");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(127, 128, 61, 36);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBackground(new Color(238, 244, 218));
		username.setBounds(176, 128, 281, 36);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBackground(new Color(238, 244, 218));
		password.setBounds(176, 187, 281, 36);
		contentPane.add(password);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼：");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(127, 187, 61, 36);
		contentPane.add(lblNewLabel_1_1);
		
		
		//BUTTON//
		JButton LoginButton = new JButton("登入");
		LoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Username=username.getText();
				String Password=password.getText();
				Member m=new MemberServiceImpl().login(Username, Password);
				
				if(m!=null)
				{
					Tool.saveFile(m,"member.txt");
					
					LoginSuccess loginSuccess=new LoginSuccess();
					loginSuccess.setVisible(true);
					dispose();
				}else {
					LoginError loginError=new LoginError();
					loginError.setVisible(true);
					dispose();
				}
				
			}
		});
		LoginButton.setForeground(new Color(0, 0, 0));
		LoginButton.setBackground(new Color(120, 183, 240));
		LoginButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		LoginButton.setBounds(340, 248, 117, 41);
		contentPane.add(LoginButton);
		
		JButton RegisterButton = new JButton("註冊");
		RegisterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddMember addmember=new AddMember();
				addmember.setVisible(true);
				dispose();
			}
		});
		RegisterButton.setForeground(Color.BLACK);
		RegisterButton.setFont(new Font("Dialog", Font.BOLD, 14));
		RegisterButton.setBackground(new Color(120, 183, 240));
		RegisterButton.setBounds(127, 248, 117, 41);
		contentPane.add(RegisterButton);

	}
}
