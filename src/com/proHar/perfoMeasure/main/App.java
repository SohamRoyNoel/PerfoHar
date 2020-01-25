package com.proHar.perfoMeasure.main;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.proHar.perfoMeasure.main.reporting.ListFileUtils;
import com.proHar.perfoMeasure.main.reporting.ReporterAgent;

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
		
		String BaseUrl = driver.getCurrentUrl();
		String base = BaseUrl.substring(BaseUrl.indexOf("://")+3, BaseUrl.indexOf(".com"));
		
		// Performance Methods
		try {
			ValueParser.ResourceAnalyser(driver, base);
			ValueParser.NavigationAnalyser(driver, base);
		} catch (InterruptedException e) {	}

	}
	
	public void ReportingAgent() throws InterruptedException {
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
