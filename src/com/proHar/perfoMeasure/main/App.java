package com.proHar.perfoMeasure.main;

import java.io.File;
import java.io.IOException;

import com.proHar.perfoMeasure.main.reporting.ReporterAgent;

public class App {

	public static void main(String[] args) {
		
		// DirecTory Allocations
		String workingPath = System.getProperty("user.dir");
		
		// Create Folder
		String workingFOLDERpath = workingPath + "\\Output";
		File targetPath = new File(workingFOLDERpath);
		if (!targetPath.exists()) {
			targetPath.mkdir();
		}
		
		// Performance Methods
		try {
			ValueParser.ResourceAnalyser();
			ValueParser.NavigationAnalyser();
		} catch (InterruptedException e) {	}
		
		ReporterAgent ra = new ReporterAgent();
		ra.getReport(getNAVlocation(workingFOLDERpath), getRESlocation(workingFOLDERpath));
		
	}
	
	public static String getNAVlocation(String baseFolderPath) {
		String navigationPath = baseFolderPath + "\\navTemp.txt";
		File navFile = new File(navigationPath);
		createFile(navFile);
		return navigationPath;
	}

	public static String getRESlocation(String baseFolderPath) {
		String resourcesPath = baseFolderPath + "\\\\resTemp.txt";
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
