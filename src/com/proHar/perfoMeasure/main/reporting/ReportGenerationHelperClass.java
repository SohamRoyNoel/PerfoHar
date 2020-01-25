package com.proHar.perfoMeasure.main.reporting;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportGenerationHelperClass {

	public CellStyle createCellStyle(XSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
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
		default:
			System.out.println();
		}
		System.out.println(stringCellValue);
		return stringCellValue;
	}

	public CellStyle createCellStyle1(XSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
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
		style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
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
			FileOutputStream out = new FileOutputStream(fileName);
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String createReportDirectoryAndGetPath(String location) {
		return location;
	}

}
