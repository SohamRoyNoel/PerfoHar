/*
 * Author : Soham Roy, QA Analyst, Cognizant Technology Solutions, Kolkata
 * */
package com.proHar.perfoMeasure.main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.proHar.perfoMeasure.main.databaseModules.AzureDataMigrationUtils;
import com.proHar.perfoMeasure.main.reporting.ListFileUtils;
import com.proHar.perfoMeasure.main.reporting.ReporterAgent;
/*
 * Performance Entry
 * */
public class App {

//	public static void main(String[] args) {
	public void Performer(WebDriver driver) {
		
		// DirecTory Allocations
		String workingPath = System.getProperty("user.dir");
		
		// Create Folder
		String workingFOLDERpath = workingPath + "\\Output";
		File targetPath = new File(workingFOLDERpath);
		if (!targetPath.exists()) {
			targetPath.mkdir();
		}
		
		JavascriptExecutor js = (JavascriptExecutor)driver;	
//		String BaseUrl = js.executeScript("return document.domain;").toString();;
		String base = js.executeScript("return document.domain;").toString();
		
		// Performance Methods
		try {
			ValueParser.ResourceAnalyser(driver, base);
			ValueParser.NavigationAnalyser(driver, base);
		} catch (InterruptedException e) {	}

	}
	
	// Excel Database generation class
	public void ExcelAgent() throws InterruptedException {
		String workingPath = System.getProperty("user.dir");
		String workingFOLDERpath = workingPath + "\\Output";
		ReporterAgent ra = new ReporterAgent();
		String nav = getNAVlocation(workingFOLDERpath);
		String res = getRESlocation(workingFOLDERpath);
		ra.getReport(nav, res);
		Thread.sleep(3000);
		try {
			ListFileUtils.GenerateValueFromExcel(nav, res, workingFOLDERpath);
		} catch (IOException e) { }
	}
	
	// Azure Data Migration Class
	public void AzureAgent() {
		AzureDataMigrationUtils adm = new AzureDataMigrationUtils();
		try {
			adm.AzureDatabaseManagerAgent();
		} catch (ClassNotFoundException | SQLException e) {	}
	}
	
	public static String getNAVlocation(String baseFolderPath) {
		String navigationPath = baseFolderPath + "\\navTemp.txt";
		File navFile = new File(navigationPath);
		createFile(navFile);
		return navigationPath;
	}

	public static String getRESlocation(String baseFolderPath) {
		String resourcesPath = baseFolderPath + "\\resTemp.txt";
		File resFile = new File(resourcesPath);
		createFile(resFile);
		return resourcesPath;
	}
	
	public static void createFile(File navFile) {
		try {
			navFile.createNewFile();
		} catch (IOException e1) {	}
	}
}
