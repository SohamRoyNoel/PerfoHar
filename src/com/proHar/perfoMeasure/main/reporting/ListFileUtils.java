package com.proHar.perfoMeasure.main.reporting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ListFileUtils {
	static ReportGenerationHelperClass reportGenerationHelperObj = new ReportGenerationHelperClass();

	public static void GenerateValueFromExcel(String nav,String res, String Reportgenpath) throws IOException, InterruptedException {

		File f1 = new File(nav);
		File f2 = new File(res);
		if (f1.getName().equalsIgnoreCase("navTemp.txt")) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet XSSFSheetSummary = reportGenerationHelperObj.createReportSheet("Navigation Timing", workbook);
			createSummaryReport(XSSFSheetSummary, workbook);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(f1));
			String line = null;
			int line_count=1;
			String[] temp;
			//delimiter
			String delimiter = ",";
			while ((line = bufferedReader.readLine()) != null) {
				temp = line.split(delimiter);
				System.out.println("base Url : " + temp[1]);
				writeDataIntoCellsMismatch(line_count, XSSFSheetSummary, workbook, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5],temp[6],temp[7],temp[8],temp[9],temp[10],temp[11]);
				line_count++;
			}
			bufferedReader.close();
			String path1= reportGenerationHelperObj.createReportDirectoryAndGetPath(Reportgenpath);
			reportGenerationHelperObj.writeWorkbook(path1 + "\\Navigation_Status" + ".xlsx", workbook);
			workbook.close();
		}
		if (f2.getName().equalsIgnoreCase("resTemp.txt")) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet XSSFSheetSummary = reportGenerationHelperObj.createReportSheet("Resource Timing", workbook);
			createSummaryReport2(XSSFSheetSummary, workbook);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(f2));
			String line = null;
			int line_count=1;
			String[] temp;
			//delimiter
			String delimiter = ",";
			while ((line = bufferedReader.readLine()) != null) {
				temp = line.split(delimiter);
				writeDataIntoCellsMismatch(line_count, XSSFSheetSummary, workbook, temp[0], temp[1], temp[2], temp[3], temp[4]);
				line_count++;
			}
			bufferedReader.close();
			String path1= reportGenerationHelperObj.createReportDirectoryAndGetPath(Reportgenpath);
			reportGenerationHelperObj.writeWorkbook(path1 + "\\Resource_Status" + ".xlsx", workbook);
			workbook.close();
		}
//		f1.delete();
//		f2.delete();
	}

	private static void createSummaryReport(XSSFSheet XSSFSheetMismatch, XSSFWorkbook workbook) {
		String[] columnNames = {"appName", "baseUrl", "Unload","Redirect","AppCache","TTFB","Processing","Dom_Interactive","Dom_Complete","Content_load","Page_load","Date"};
		reportGenerationHelperObj.createRowInExcel(0, columnNames, XSSFSheetMismatch, workbook, reportGenerationHelperObj.createCellStyle(workbook));
	}
	static int rownum = 1;

	private static void writeDataIntoCellsMismatch(int row, XSSFSheet XSSFSheet, XSSFWorkbook workbook, String appName, String baseUrl, String Unload,String Redirect,String AppCache,String TTFB,String Processing,String Dom_Interactive,String Dom_Complete,String Content_load,String Page_load, String stringDate) {
		String[] rowData = {appName, baseUrl, Unload,Redirect,AppCache,TTFB,Processing,Dom_Interactive,Dom_Complete,Content_load,Page_load, stringDate};
		reportGenerationHelperObj.createRowInExcel(row, rowData, XSSFSheet, workbook, reportGenerationHelperObj.createCellStyle1(workbook));
	}
	
	private static void createSummaryReport2(XSSFSheet XSSFSheetMismatch, XSSFWorkbook workbook) {
		String[] columnNames = {"AppName", "BaseUrl", "ElementName","Duration","Date"};
		reportGenerationHelperObj.createRowInExcel(0, columnNames, XSSFSheetMismatch, workbook, reportGenerationHelperObj.createCellStyle(workbook));
	}
//	static int rownum = 1;

	private static void writeDataIntoCellsMismatch(int row, XSSFSheet XSSFSheet, XSSFWorkbook workbook, String appName, String baseUrl, String elementName,String Duration,String date) {
		String[] rowData = {appName, baseUrl, elementName,Duration,date};
		reportGenerationHelperObj.createRowInExcel(row, rowData, XSSFSheet, workbook, reportGenerationHelperObj.createCellStyle1(workbook));
	}

}
