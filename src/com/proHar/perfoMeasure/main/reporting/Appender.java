package com.proHar.perfoMeasure.main.reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Appender {

	public void appendStrToFile(String fileName,List<String> str)
	{
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			for (String s : str) {
				out.write(s);
			}
			out.close();
		}
		catch (IOException e) {   }
	}

}
