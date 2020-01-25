package com.proHar.perfoMeasure.main.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportGenerationHelperClass {

	public CellStyle createCellStyle(XSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderTop((short) 1);
		style.setBorderBottom((short) 1);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 15);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setColor((short) 1);
		style.setFont(font);
		return style;
	}

	public XSSFSheet createReportSheet(String sheetName, XSSFWorkbook workbook) {
		XSSFSheet sheet = workbook.createSheet(sheetName);
		return sheet;
	}

	public String getCellValueInString(Cell cell) {
		String stringCellValue = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			stringCellValue = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				stringCellValue = String.valueOf(cell.getDateCellValue());
			} else {
				int temp = (int) cell.getNumericCellValue();
				stringCellValue = String.valueOf(temp);
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			stringCellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			stringCellValue = String.valueOf(cell.getCellFormula());
			break;
		default:
			System.out.println();
		}
		System.out.println(stringCellValue);
		return stringCellValue;
	}

	public CellStyle createCellStyle1(XSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderTop((short) 1);
		style.setBorderBottom((short) 1);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 13);
		font.setBoldweight((short) 5);
		font.setColor((short) 0);
		style.setFont(font);
		return style;
	}
	public CellStyle createCellStyle2(XSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight((short) 0);
		style.setBorderLeft((short) 0);
		style.setBorderTop((short) 0);
		style.setBorderBottom((short) 0);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 13);
		font.setBoldweight((short) 5);
		font.setColor((short) 0);
		style.setFont(font);
		return style;
	}

	public void createRowInExcel(int rowNumber, String[] RowValues, XSSFSheet XSSFSheet, XSSFWorkbook workbook, CellStyle style) {
		Row row = XSSFSheet.createRow(rowNumber);
		for (int i = 0; i < RowValues.length; i++) {
			Cell cell = row.createCell(i + 1);
			cell.setCellValue(RowValues[i]);
			cell.setCellStyle(style);
			XSSFSheet.autoSizeColumn(i + 1);
		}
	}

	public void writeWorkbook(String fileName, XSSFWorkbook workbook) {
		try {
			//                  System.out.println("writing to file"+fileName);
			FileOutputStream out = new FileOutputStream(fileName);
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//        String location = "D:\\";
	public String createReportDirectoryAndGetPath(String location) {
		String username = null;
		String stringPath;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		username = System.getProperty("user.name");
		File main_directory = new File(location + "TIFtoTIFCompare");
		if (!main_directory.exists()) {
			if (main_directory.mkdir()) {
				//                         System.out.println("Main Directory is created!");
			} else {
				//                         System.out.println("Failed to create main directory!");
			}
		}
		File Root_directory = new File(location + "TIFtoTIFCompare\\" + username);
		if (!Root_directory.exists()) {
			if (Root_directory.mkdir()) {
				//                         System.out.println("Root Directory is created!");
			} else {
				//                         System.out.println("Failed to create root directory!");
			}
		}
		File Run_directory = new File(location + "TIFtoTIFCompare\\" + username + "\\Run");
		if (!Run_directory.exists()) {
			if (Run_directory.mkdir()) {
				//                         System.out.println("Directory is created!");
			} else {
				//                         System.out.println("Failed to create directory!");
			}
		}
		stringPath = location + "TIFtoTIFCompare\\" + username + "\\Run";
		return stringPath;
	}

}
