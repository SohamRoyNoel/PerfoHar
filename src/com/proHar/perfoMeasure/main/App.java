package com.proHar.perfoMeasure.main;

public class App {

	public static void main(String[] args) {

		try {
			ValueParser.ResourceAnalyser();
			ValueParser.NavigationAnalyser();
		} catch (InterruptedException e) {	}
		
	}

}
