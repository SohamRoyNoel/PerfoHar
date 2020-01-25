package com.proHar.perfoMeasure.main.reporting;

import com.proHar.perfoMeasure.main.ValueParser;

public class ReporterAgent {
	Appender appender = new Appender();

	public void getReport(String navFileLocation, String resFileLocation) {

		// Get The Generated List by CONDITION
		appender.appendStrToFile(navFileLocation, ValueParser.navHolder);
		appender.appendStrToFile(resFileLocation, ValueParser.resHolder);

	}

}
