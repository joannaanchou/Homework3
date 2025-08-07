package controller.porder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Member;
import model.Porder;
import util.CreateExcel;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JScrollPane;

public class Finish extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finish frame = new Finish();
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
	public Finish() {
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
		panel.setBounds(172, 52, 306, 63);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("訂單完成");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(249, 255, 228));
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 14));
		lblNewLabel.setBounds(90, 22, 129, 16);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(228, 181, 159));
		panel_1.setBounds(25, 164, 596, 327);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//右上時鐘
		JLabel showTime = new JLabel("");
		showTime.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		showTime.setHorizontalAlignment(SwingConstants.RIGHT);
		showTime.setBounds(465, 129, 156, 36);
		contentPane.add(showTime);
		
		Timer clockTimer = new Timer(1000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		        String currentTime = sdf.format(new Date());
		        showTime.setText(currentTime);
		    }
		});
		clockTimer.start();
		
		
		Member m=(Member)Tool.readFile("member.txt");
		Porder p=(Porder)Tool.readFile("porder.txt");
		
		JLabel showMessage = new JLabel("");
		showMessage.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		showMessage.setBounds(25, 131, 306, 32);
		contentPane.add(showMessage);
		
		showMessage.setText("訂單已完成!"+m.getName()+"\t以下為訂單資訊");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 6, 555, 315);
		panel_1.add(scrollPane);
		
		
		JTextArea showReporter = new JTextArea();
		scrollPane.setViewportView(showReporter);
		
	
		int dailyTotal =p.getDaily() * 299;
		int seasonTotal =p.getSeason() * 1899;
		int annualTotal =p.getAnnual() * 6999;
		int finalAmount = dailyTotal + seasonTotal + annualTotal;
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String orderTime = sdf.format(new Date());
			
		String show="【會員資料】"+
				"\n姓名:"+m.getName()+
				"\n地址:"+m.getAddress()+
				"\n聯絡電話："+m.getPhone()+
				"\n"+
				"\n========================================================"+
				"\n【訂單內容】"+
				"\n訂單成立時間：" + orderTime +
				"\n票券日期：" +p.getDate()+
		       "\n單日票：" + p.getDaily() + "張x$299=$" + dailyTotal +
		       "\n季票：" + p.getSeason() + "張x$1899=$" + seasonTotal +
		       "\n年票：" + p.getAnnual() + "張x$6999=$" + annualTotal +
		   		"\n"+
		       "\n=======================================================" +
		       "\n【金額計算】"+
		       "\n總金額：$" + finalAmount;
		
		showReporter.setText(show);
		

		
		
		//BUTTON//
		JButton btnNewButton = new JButton("匯出");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 選擇檔案位置&名稱
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("請選擇匯出Excel檔案的位置");
		        fileChooser.setSelectedFile(new File("OrderExport.xlsx")); //預設檔名

		        int userSelection = fileChooser.showSaveDialog(Finish.this);
		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToSave = fileChooser.getSelectedFile();
		            String filePath = fileToSave.getAbsolutePath(); //?

		            // 若user沒加副檔名，自動補上.xls
		            if (!filePath.toLowerCase().endsWith(".xlsx")) {
		                filePath += ".xlsx";
		                fileToSave = new File(filePath);
		            }

		            try {
		                // 1. 建立Excel檔案＆欄位標題
		                CreateExcel excel = new CreateExcel();
		                String[] titles = {"姓名", "地址", "聯絡電話", "單日票數", "單日票總價", "季票數", "季票總價", "年票數", "年票總價", "總金額", "票券日期", "訂單成立時間"};
		                excel.create(filePath, "訂單明細", titles);

		                // 2. 寫入一筆資料
		                excel.insertValue(m, p, orderTime, filePath, "訂單明細");

		                javax.swing.JOptionPane.showMessageDialog(Finish.this, "Excel 匯出成功: " + filePath);
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                javax.swing.JOptionPane.showMessageDialog(Finish.this, "Excel 匯出失敗: " + ex.getMessage());
		            }
		        }
		    }
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(120, 183, 240));
		btnNewButton.setBounds(504, 493, 117, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("列印");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					showReporter.print();
				}catch(PrinterException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1.setBackground(new Color(120, 183, 240));
		btnNewButton_1.setBounds(382, 493, 117, 51);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("回會員中心");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PorderManager porderManager=new PorderManager();
				porderManager.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1_1.setBackground(new Color(120, 183, 240));
		btnNewButton_1_1.setBounds(25, 493, 117, 51);
		contentPane.add(btnNewButton_1_1);
		
		

	}

}
