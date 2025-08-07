package controller.porder;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import model.Porder;
import service.PorderService;
import service.impl.PorderServiceImpl;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.DateLabelFormatter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Properties;


public class AddPorder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPorder frame = new AddPorder();
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
	public AddPorder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 575);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 222, 166));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(229, 181, 159));
		panel_2.setBounds(24, 374, 598, 63);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		// 日期選擇器元件（要先初始化datePanel，再建立 datePicker）
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "今天");
		p.put("text.month", "月份");
		p.put("text.year", "年份");


				
				
		JLabel lblNewLabel_2_3 = new JLabel("票券日期");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setForeground(new Color(0, 1, 1));
		lblNewLabel_2_3.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2_3.setBackground(new Color(119, 183, 240));
		lblNewLabel_2_3.setBounds(6, 13, 112, 37);
		panel_2.add(lblNewLabel_2_3);
		
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(126, 13, 250, 44);
		panel_2.add(datePicker);
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(120, 183, 240));
		panel.setBounds(171, 33, 306, 63);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("MONA MUSEUM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(249, 255, 228));
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 14));
		lblNewLabel.setBounds(90, 22, 129, 16);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(228, 181, 159));
		panel_1.setBounds(24, 140, 598, 222);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("票種");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(0, 1, 1));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setBounds(28, 10, 112, 37);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("單價");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(new Color(0, 1, 1));
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(234, 10, 112, 37);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("數量");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(new Color(0, 1, 1));
		lblNewLabel_2_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(419, 10, 112, 37);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_1 = new JLabel("單日票");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(85, 87, 83));
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 57, 86, 37);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("季節票");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(85, 87, 83));
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(38, 99, 86, 37);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("年票");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(new Color(85, 87, 83));
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(38, 148, 86, 37);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("$299");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setForeground(new Color(85, 87, 83));
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(244, 57, 86, 37);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("$1899");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setForeground(new Color(85, 87, 83));
		lblNewLabel_1_3_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1_3_1.setBounds(244, 99, 86, 37);
		panel_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("$6999");
		lblNewLabel_1_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_2.setForeground(new Color(85, 87, 83));
		lblNewLabel_1_3_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_1_3_2.setBounds(244, 148, 86, 37);
		panel_1.add(lblNewLabel_1_3_2);
		
		JComboBox daily = new JComboBox();
		daily.setBounds(446, 55, 85, 45);
		panel_1.add(daily);
		
		JComboBox season = new JComboBox();
		season.setBounds(446, 97, 85, 45);
		panel_1.add(season);
		
		JComboBox annual = new JComboBox();
		annual.setBounds(446, 146, 85, 45);
		panel_1.add(annual);
		
		for (int i = 0; i <= 10; i++) {
			daily.addItem(i);
			season.addItem(i);
			annual.addItem(i);
		}
		
		

	
		JLabel showMember = new JLabel("");
		showMember.setBackground(new Color(234, 222, 166));
		showMember.setBounds(24, 112, 598, 26);
		contentPane.add(showMember);
		
		
		Member m = (Member) Tool.readFile("member.txt");
		

		String show = "會員："+m.getName();
		showMember.setText(show);
		
		
		//BUTTON//
		JButton btnNewButton = new JButton("確認");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int dailyQty = (int) daily.getSelectedItem();
				int seasonQty = (int) season.getSelectedItem();
				int annualQty = (int) annual.getSelectedItem();
			
				
				// 取得日期選擇器的值
		        Date selectedDateRaw = (Date) datePicker.getModel().getValue();
		        
		        
		        
		        if (selectedDateRaw ==null) {
		        	javax.swing.JOptionPane.showMessageDialog(AddPorder.this, "請選擇票券日期！");
					return;
		        }
					
		        
		        // 轉換為 LocalDate（系統預設時區）
		        LocalDate selectedDate = selectedDateRaw.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		           

		        // 今天
		        LocalDate today = LocalDate.now();

		        // 不能是今天或過去
		        if (!selectedDate.isAfter(today)) {
		            javax.swing.JOptionPane.showMessageDialog(AddPorder.this, "請選擇未來日期！");
		            return;
		        }

		        //轉換字串
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		        String orderDate = selectedDate.format(formatter);
		        
		        //儲存訂單資料
		        Porder p = new Porder(m.getName(), dailyQty, seasonQty, annualQty, orderDate);
		        Tool.saveFile(p, "porder.txt");
		
			
				
				Confirm confirm = new Confirm();
				confirm.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(120, 183, 240));
		btnNewButton.setBounds(505, 458, 117, 51);
		contentPane.add(btnNewButton);
		
		
		

	}
}
