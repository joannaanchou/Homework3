package controller.porder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Porder;
import service.impl.PorderServiceImpl;
import util.CreateExcel;
import util.PorderUi;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindAllPorder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idDelete;
	private JTextField idAdjust;
	private static PorderServiceImpl psi = new PorderServiceImpl();
	private JTextArea allPorder;
	private JLabel sum;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FindAllPorder frame = new FindAllPorder();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FindAllPorder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 655);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 222, 166));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(120, 183, 240));
		panel.setBounds(243, 21, 306, 63);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("訂單管理");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(249, 255, 228));
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 14));
		lblNewLabel.setBounds(90, 22, 129, 16);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 101, 745, 256);
		contentPane.add(scrollPane);

		allPorder = new JTextArea();
		scrollPane.setViewportView(allPorder);

		sum = new JLabel("");
		sum.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		sum.setBounds(23, 357, 745, 39);
		contentPane.add(sum);

		refreshDisplay();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(228, 181, 159));
		panel_1.setBounds(23, 369, 749, 88);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("修改訂單");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(6, 6, 103, 29);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("ID:");
		lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(6, 35, 61, 35);
		panel_1.add(lblNewLabel_2_1);

		idAdjust = new JTextField();
		idAdjust.setColumns(10);
		idAdjust.setBounds(35, 40, 130, 26);
		panel_1.add(idAdjust);

		JLabel lblNewLabel_2_1_1 = new JLabel("單日票:");
		lblNewLabel_2_1_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(177, 35, 61, 35);
		panel_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("季票:");
		lblNewLabel_2_1_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_2_1_2.setBounds(334, 35, 61, 35);
		panel_1.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_1_3 = new JLabel("年票:");
		lblNewLabel_2_1_3.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_2_1_3.setBounds(505, 35, 61, 35);
		panel_1.add(lblNewLabel_2_1_3);

		JComboBox daily = new JComboBox();
		daily.setBounds(226, 33, 85, 45);
		panel_1.add(daily);

		JComboBox season = new JComboBox();
		season.setBounds(374, 33, 85, 45);
		panel_1.add(season);

		JComboBox annual = new JComboBox();
		annual.setBounds(543, 33, 85, 45);
		panel_1.add(annual);

		for (int i = 0; i <= 10; i++) {
			daily.addItem(i);
			season.addItem(i);
			annual.addItem(i);
		}

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(228, 181, 159));
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(19, 469, 749, 77);
		contentPane.add(panel_1_1);

		JLabel lblNewLabel_1 = new JLabel("刪除訂單");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(6, 6, 103, 29);
		panel_1_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_2.setBounds(6, 33, 61, 35);
		panel_1_1.add(lblNewLabel_2);

		idDelete = new JTextField();
		idDelete.setBounds(38, 38, 130, 26);
		panel_1_1.add(idDelete);
		idDelete.setColumns(10);

		
		//BUTTON//
		JButton btnNewButton = new JButton("會員中心");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PorderManager porderManager = new PorderManager();
				porderManager.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(120, 183, 240));
		btnNewButton.setBounds(653, 47, 117, 51);
		contentPane.add(btnNewButton);

		JButton ReviseButton = new JButton("修改");
		ReviseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ID = Integer.parseInt(idAdjust.getText());
				int dailyQty = (int) daily.getSelectedItem();
				int seasonQty = (int) season.getSelectedItem();
				int annualQty = (int) annual.getSelectedItem();
				Porder p = new Porder();
				p.setId(ID);
				p.setDaily(dailyQty);
				p.setSeason(seasonQty);
				p.setAnnual(annualQty);
				psi.updatePorder(p);
				refreshDisplay();
			}
		});
		ReviseButton.setFont(new Font("Dialog", Font.BOLD, 14));
		ReviseButton.setBackground(new Color(120, 183, 240));
		ReviseButton.setBounds(652, 33, 91, 35);
		panel_1.add(ReviseButton);

		JButton DeleteButton = new JButton("刪除");
		DeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int DELID = Integer.parseInt(idDelete.getText());
				Porder p = new Porder();
				p.setId(DELID);
				psi.deletePorder(p);
				refreshDisplay();
			}
		});
		DeleteButton.setFont(new Font("Dialog", Font.BOLD, 14));
		DeleteButton.setBackground(new Color(120, 183, 240));
		DeleteButton.setBounds(652, 33, 91, 35);
		panel_1_1.add(DeleteButton);
		
		JButton OrderReportButton = new JButton("訂單報表");
		OrderReportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 選擇檔案位置與名稱
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("請選擇匯出Excel檔案的位置");
		        fileChooser.setSelectedFile(new File("AllOrderExport.xlsx")); // 預設檔名

		        int userSelection = fileChooser.showSaveDialog(FindAllPorder.this);
		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToSave = fileChooser.getSelectedFile();
		            String filePath = fileToSave.getAbsolutePath();

		            // 若user沒加副檔名，自動補上.xls
		            if (!filePath.toLowerCase().endsWith(".xlsx")) {
		                filePath += ".xlsx";
		                fileToSave = new File(filePath);
		            }

		            try {
		                // 1. 建立 Excel 檔案並寫入欄位標題
		                CreateExcel excel = new CreateExcel();
		                String[] titles = {"訂單編號", "姓名", "日票", "季票", "年票", "總金額"};
		                String sheetName = "周報表";
		                excel.create(filePath, sheetName, titles);

		               
		                List<Porder> list = new PorderServiceImpl().findAllPorder();
		                for (Porder p : list) {
		                    excel.insertPorderwithTotal(p, filePath, sheetName);
		                }

		                JOptionPane.showMessageDialog(FindAllPorder.this, "匯出成功: " + filePath);
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(FindAllPorder.this, "匯出失敗: " + ex.getMessage());
		            }
		        }
		    }
		});
		OrderReportButton.setFont(new Font("Dialog", Font.BOLD, 14));
		OrderReportButton.setBackground(new Color(120, 183, 240));
		OrderReportButton.setBounds(677, 562, 91, 35);
		contentPane.add(OrderReportButton);
		
		JButton ProductSalesButton = new JButton("商品銷量");
		ProductSalesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 選擇檔案位置與名稱
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("請選擇匯出Excel檔案的位置");
		        fileChooser.setSelectedFile(new File("ProductSalesReport.xlsx")); // 預設檔名

		        int userSelection = fileChooser.showSaveDialog(FindAllPorder.this);
		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToSave = fileChooser.getSelectedFile();
		            String filePath = fileToSave.getAbsolutePath();

		            // 若使用者沒加副檔名，自動補上 .xlsx
		            if (!filePath.toLowerCase().endsWith(".xlsx")) {
		                filePath += ".xlsx";
		                fileToSave = new File(filePath);
		            }

		            try {
		                // 1. 取得所有訂單資料
		                List<Porder> list = new PorderServiceImpl().findAllPorder();

		                // 2. 建立報表
		                CreateExcel excel = new CreateExcel();
		                String sheetName = "產品銷售統計";

		                // 呼叫 exportProductSalesReport（含圖表）
		                excel.exportProductSalesReport(list, filePath, sheetName);

		                JOptionPane.showMessageDialog(FindAllPorder.this, "匯出成功: " + filePath);
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(FindAllPorder.this, "匯出失敗: " + ex.getMessage());
		            }
		        }
		    }
		});

		ProductSalesButton.setFont(new Font("Dialog", Font.BOLD, 14));
		ProductSalesButton.setBackground(new Color(120, 183, 240));
		ProductSalesButton.setBounds(577, 562, 91, 35);
		contentPane.add(ProductSalesButton);
	}

	private void refreshDisplay() {
		PorderUi.displayAllPorders(allPorder, sum, psi);
	}
	
	
}
