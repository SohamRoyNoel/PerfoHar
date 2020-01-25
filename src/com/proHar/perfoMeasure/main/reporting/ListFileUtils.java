package com.proHar.perfoMeasure.main.reporting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ListFileUtils {
	static ReportGenerationHelperClass reportGenerationHelperObj = new ReportGenerationHelperClass();

	public static void Converter(String args,String args1) throws IOException, InterruptedException {
		String test=args;
		System.out.println(test);
		final String directoryWindows =test;
		String outputDirLocation=args1+"\\";
		String temp_file1=args1+"\\Temp1.txt";
		File f1 = new File(temp_file1);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet XSSFSheetSummary = reportGenerationHelperObj.createReportSheet("Summary", workbook);
		createSummaryReport(XSSFSheetSummary, workbook);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(temp_file1));
		String line = null;
		int line_count=1;
		String[] temp;
		//delimiter
		String delimiter = ",";
		String str = " ";
		String str1 = " ";
		String str2 = " ";
		String str3 = " ";
		String str4 = " ";
		String str5 = " "; // Recheck Status Unique ID

		while ((line = bufferedReader.readLine()) != null) {
			// given string will be split by the argument delimiter provided.
			temp = line.split(delimiter);
			//print substrings
			for(int i =0; i < temp.length ; i++){
				str = temp[0];
				str1 = temp[1];
				str2 = temp[2];
				str3 = temp[3];
				str4 = temp[4];
				str5 = str1.substring(str1.lastIndexOf("\\")+1, str1.lastIndexOf("_"));
			}
			writeDataIntoCellsMismatch(line_count, XSSFSheetSummary, workbook, str, str1, str2, str3, str4, str5);
			line_count++;
		}
		bufferedReader.close();
		String path1= reportGenerationHelperObj.createReportDirectoryAndGetPath("\\\\entsserver85\\cognizant\\DSTScriptRunner\\Reports\\");
		//                            String path1= reportGenerationHelperObj.createReportDirectoryAndGetPath(outputDirLocation);
		// write to excel file
		reportGenerationHelperObj.writeWorkbook(path1 + "\\Overall_test_report" + ".xlsx", workbook);
		File file = new File(temp_file1);
		workbook.close();
		///
		if (file.exists() && file.isFile())
		{
			//file.delete();
		}
	}

	private static void createSummaryReport(XSSFSheet XSSFSheetMismatch, XSSFWorkbook workbook) {
		String[] columnNames = {"Folder Name", "Source File Name", "Target File Name","Status","Comment","UniqueID"};
		reportGenerationHelperObj.createRowInExcel(0, columnNames, XSSFSheetMismatch, workbook, reportGenerationHelperObj.createCellStyle(workbook));
	}
	static int rownum = 1;

	private static void writeDataIntoCellsMismatch(int row, XSSFSheet XSSFSheet, XSSFWorkbook workbook, String Folder_Name, String Source_File_Name, String Target_File_Name, String Status, String Comment, String UniqueID) {
		String[] rowData = {Folder_Name, Source_File_Name, Target_File_Name,Status,Comment,UniqueID};
		reportGenerationHelperObj.createRowInExcel(row, rowData, XSSFSheet, workbook, reportGenerationHelperObj.createCellStyle1(workbook));
	}

}
