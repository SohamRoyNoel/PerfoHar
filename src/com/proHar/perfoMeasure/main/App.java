package com.proHar.perfoMeasure.main;

import java.io.File;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		
		// DirecTory Allocations
		String workingPath = System. getProperty("user.dir");
		
		// Create Folder
		String workingFOLDERpath = workingPath + "\\Output";
		File targetPath = new File(workingFOLDERpath);
		if (!targetPath.exists()) {
			targetPath.mkdir();
		}
		
		// Action Files
		String navigationPath = workingFOLDERpath + "\\navTemp.txt";
		String resourcesPath = workingFOLDERpath + "\\resTemp.txt";
		File navFile = new File(navigationPath);
		File resFile = new File(resourcesPath);
		try {
			navFile.createNewFile();
			resFile.createNewFile();
		} catch (IOException e1) {	}
		
//		try {
//			ValueParser.ResourceAnalyser();
//			ValueParser.NavigationAnalyser();
//			
//			System.out.println("From main");
//			System.out.println(ValueParser.navHolder);
//			System.out.println(ValueParser.resHolder);
//		} catch (InterruptedException e) {	}
		
	}

}
