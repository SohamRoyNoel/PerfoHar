package com.proHar.perfoMeasure.main.reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Appender {

	public static void appendStrToFile(String fileName,String str)
	{
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(str);
			out.close();
		}
		catch (IOException e) {   }
	}

}
