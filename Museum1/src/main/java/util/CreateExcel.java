package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import model.Member;
import model.Porder;

public class CreateExcel {
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFSheet sheet;

    public void create(String outputFile, String sheetName, String[] titleName) {
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            sheet = workbook.createSheet(sheetName);
            XSSFRow row = sheet.createRow(0);
            for (int i = 0; i < titleName.length; i++) {
                row.createCell(i).setCellValue(titleName[i]);
            }
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    // 匯出：會員單筆訂單 excel
    public void insertValue(Member m, Porder p, String orderTime, String fileName, String sheetName) {
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileName));
            sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            XSSFRow row = sheet.createRow(rowCount);

            int dailyTotal = p.getDaily() * 299;
            int seasonTotal = p.getSeason() * 1899;
            int annualTotal = p.getAnnual() * 6999;
            int finalAmount = dailyTotal + seasonTotal + annualTotal;

            row.createCell(0).setCellValue(m.getName());
            row.createCell(1).setCellValue(m.getAddress());
            row.createCell(2).setCellValue(m.getPhone());
            row.createCell(3).setCellValue(p.getDaily());
            row.createCell(4).setCellValue(dailyTotal);
            row.createCell(5).setCellValue(p.getSeason());
            row.createCell(6).setCellValue(seasonTotal);
            row.createCell(7).setCellValue(p.getAnnual());
            row.createCell(8).setCellValue(annualTotal);
            row.createCell(9).setCellValue(finalAmount);
            row.createCell(10).setCellValue(p.getDate());
            row.createCell(11).setCellValue(orderTime);

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                workbook.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // 匯出：所有訂單 excel
    public void insertPorderwithTotal(Porder porder,String fileName, String sheetName) {
    	try {
			workbook=new XSSFWorkbook(new FileInputStream(fileName));
			XSSFSheet sheet=workbook.getSheet(sheetName);
			int count=sheet.getPhysicalNumberOfRows();
			
			XSSFRow row=sheet.createRow(count);
			
			int dailyTotal = porder.getDaily() * 299;
            int seasonTotal = porder.getSeason() * 1899;
            int annualTotal = porder.getAnnual() * 6999;
            int finalAmount = dailyTotal + seasonTotal + annualTotal;
			
			
			row.createCell(0).setCellValue(porder.getId());
			row.createCell(1).setCellValue(porder.getName());
			row.createCell(2).setCellValue(porder.getDaily());
			row.createCell(3).setCellValue(porder.getSeason());
			row.createCell(4).setCellValue(porder.getAnnual());
			row.createCell(5).setCellValue(finalAmount);
			
			FileOutputStream out=new FileOutputStream(fileName);
			workbook.write(out);
			out.flush();
			out.close();
	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void exportProductSalesReport(List<Porder> porderList, String fileName, String sheetName) {
    	workbook = new XSSFWorkbook();
    	sheet=workbook.createSheet(sheetName);
    	
    	// 建立標題列
        String[] headers = {"商品", "銷量", "銷售總額"};
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        
     // 統計總數
        int dailyCount = 0, seasonCount = 0, annualCount = 0;
        for (Porder p : porderList) {
            dailyCount += p.getDaily();
            seasonCount += p.getSeason();
            annualCount += p.getAnnual();
        }
        
        int dailyTotal = dailyCount * 299;
        int seasonTotal = seasonCount * 1899;
        int annualTotal = annualCount * 6999;
        
     // 寫入資料列
        Object[][] data = {
            {"單日票", dailyCount, dailyTotal},
            {"季票", seasonCount, seasonTotal},
            {"年票", annualCount, annualTotal}
        };

        
        for (int i = 0; i < data.length; i++) {
            Object[] rowData = data[i];
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue((String) rowData[0]);
            row.createCell(1).setCellValue(Integer.parseInt(rowData[1].toString()));
            row.createCell(2).setCellValue(Integer.parseInt(rowData[2].toString()));
        }
        
     //plus:設定欄寬
        sheet.setColumnWidth(0, 15 * 256);
        
        
     // 柱狀圖
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 6, 7, 20); // 圖表位置
        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("商品銷售統計");
        chart.setTitleOverlay(false);

        //圖
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);
        
        //X軸
        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("商品");
        
        //Y軸
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("總銷售額 / 數量");
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        
        // 定義資料範圍
        XDDFDataSource<String> categoryData = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                new CellRangeAddress(1, 3, 0, 0)); // A2:A4
        XDDFNumericalDataSource<Double> volumeData = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(1, 3, 1, 1)); // B2:B4
        XDDFNumericalDataSource<Double> amountData = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(1, 3, 2, 2)); // C2:C4
        
        

        
        // 資料 1：柱狀圖（總銷售額）
        XDDFBarChartData barData = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        XDDFBarChartData.Series barSeries = (XDDFBarChartData.Series) barData.addSeries(categoryData, amountData);
        barSeries.setTitle("總銷售額", null);
        barData.setBarDirection(BarDirection.COL);
        chart.plot(barData);

        // 資料 2：折線圖（銷量）
        XDDFLineChartData lineData = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
        XDDFLineChartData.Series lineSeries = (XDDFLineChartData.Series) lineData.addSeries(categoryData, volumeData);
        lineSeries.setTitle("銷量", null);
        

        chart.plot(lineData);
  
        
       
        //寫入檔案
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            workbook.write(out);
        }catch (Exception e) {
        e.printStackTrace();
        }
   
    }
    
}
