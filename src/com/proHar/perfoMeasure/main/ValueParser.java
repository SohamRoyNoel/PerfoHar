package com.proHar.perfoMeasure.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.proHar.perfoMeasure.main.accessors.PerfoPOJO;

public class ValueParser {
	PerfoPOJO perfoPOJO = new PerfoPOJO(); 
	
	// Resource Status
	@SuppressWarnings("unused")
	public static void ResourceAnalyser() throws InterruptedException{

		String encodedBodySize = "";
		String entryType = "";
		String responseEnd = "";
		String workerStart = "";
		String responseStart = "";
		String domainLookupEnd = "";
		String domainLookupStart = "";
		String redirectEnd = "";
		String decodedBodySize = "";
		String duration = "";
		String transferSize = "";
		String redirectStart = "";
		String connectEnd = "";
		String connectStart = "";
		String requestStart = "";
		String secureConnectionStart = "";
		String name = "";
		String startTime = "";
		String fetchStart = "";
		String serverTiming = "";
		String nextHopProtocol = "";
		String initiatorType = "";

		String appName = "";
		String createValueString = "";
		String baseURL="";

		System.setProperty("webdriver.chrome.driver","E:\\Jars\\ChromeDriver-79\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		String baseUrl = "https://stackoverflow.com/questions/32970855/clear-cache-before-running-some-selenium-webdriver-tests-using-java"; 

		driver.get(baseUrl);
		Thread.sleep(3000);
		baseURL = driver.getCurrentUrl();
		appName = baseURL.startsWith("https://") == true ? baseURL.substring(8, baseUrl.lastIndexOf(".com")+4) : baseURL.substring(7, baseUrl.lastIndexOf(".com")+4);
		JavascriptExecutor js =(JavascriptExecutor)driver;
		int Counter = Integer.parseInt(js.executeScript("return window.performance.getEntriesByType('resource').length").toString());
		System.out.println(Counter);

		// All Timings
		try{
			for (int i = 1; i < Integer.parseInt(js.executeScript("return window.performance.getEntriesByType('resource').length").toString()); i++) {
				String allStatusString = js.executeScript("return window.performance.getEntriesByType('resource')["+i+"]").toString().replace('{', ' ').replace('}', ' ');
				String[] keyValPairs = allStatusString.split(",");
				Map<String, String> elementHolder = new HashMap<String, String>();
				String keyForSubMap = "Element";
				int mapperCounter = i;
				for (String str : keyValPairs) {
					String[] key = str.split("=");
					elementHolder.put(key[0].trim(),  key[1].trim());
				}

				// Check if all values are present
				if (elementHolder.get("connectStart") != null && elementHolder.get("domainLookupEnd") != null && elementHolder.get("startTime") != null && elementHolder.get("domainLookupStart") != null && elementHolder.get("connectEnd") != null && elementHolder.get("responseStart") != null && elementHolder.get("requestStart") != null && elementHolder.get("responseEnd") != null && elementHolder.get("name") != null && elementHolder.get("secureConnectionStart") != null && elementHolder.get("duration") != null) {
					// Resource Value Determiner
					Double blocked = Math.max(0, (Double.parseDouble(elementHolder.get("connectStart"))-Double.parseDouble(elementHolder.get("startTime"))));
					Double dns = (Double.parseDouble(elementHolder.get("domainLookupEnd"))-Double.parseDouble(elementHolder.get("domainLookupStart"))) == 0 ? -1 : (Double.parseDouble(elementHolder.get("domainLookupEnd"))-Double.parseDouble(elementHolder.get("domainLookupStart")));
					Double connect = (Double.parseDouble(elementHolder.get("connectEnd"))-Double.parseDouble(elementHolder.get("connectStart"))) == 0 ? -1 : (Double.parseDouble(elementHolder.get("connectEnd"))-Double.parseDouble(elementHolder.get("connectStart")));
					Double send = Math.max(0, Double.parseDouble(elementHolder.get("responseStart"))-Double.parseDouble(elementHolder.get("requestStart")));
					Double receive = Math.max(0, Double.parseDouble(elementHolder.get("responseEnd"))-Double.parseDouble(elementHolder.get("responseStart")));
					Double ssl = -1d;

					try {
						if (elementHolder.get("name") != null && elementHolder.get("name").toLowerCase().startsWith("https:")) {
							ssl = (double)Math.round(Double.parseDouble(elementHolder.get("secureConnectionStart")) == 0 ? -1 : (Double.parseDouble(elementHolder.get("connectEnd")) - Double.parseDouble(elementHolder.get("secureConnectionStart"))));
						}
					} catch (Exception e) {   }
					Double time = Double.parseDouble(elementHolder.get("duration"));
					long wait = Math.round(time) - Math.round(Math.max(0, dns)) - Math.round(Math.max(0, connect)) - Math.round(Math.max(0, ssl)) - Math.round(send) - Math.round(receive) - Math.round(blocked);
					if (wait < 0) {
						time -= wait;
						wait = 0;
					}
					double durations = time;
					//                         System.out.println("BLOCKED : " + blocked + " DNS : " + dns + " CONNECT : " + connect + " SEND : " + send + " RECEIVE : " + receive + " SSL : " + ssl + " Time : " + time + " Durations : " + durations);

					// Getting the Total Value
					Date date = new Date();
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					String stringDate = sdf.format(date);
					createValueString = appName+","+baseUrl+","+elementHolder.get("name").toString()+","+durations+","+stringDate;
					System.out.println("Value String : " + createValueString);
				}                  
			}
		} catch (Exception e) {   }
	}

	// Navigation Status
	@SuppressWarnings("unused")
	public static void NavigationAnalyser() throws InterruptedException{
		// 32 Common fields in case of NAVIGATION
		String redirectCount="";
		String encodedBodySize="";
		String unloadEventEnd="";
		String responseEnd="";
		String domainLookupEnd="";
		String unloadEventStart="";
		String domContentLoadedEventStart="";
		String type="";
		String decodedBodySize="";
		String duration="";
		String redirectStart="";
		String connectEnd="";
		String requestStart="";
		String startTime="";
		String fetchStart="";
		String serverTiming="";
		String domContentLoadedEventEnd="";
		String entryType="";
		String workerStart="";
		String responseStart="";
		String domInteractive="";
		String domComplete="";
		String domainLookupStart="";
		String redirectEnd="";
		String transferSize="";
		String connectStart="";
		String loadEventStart="";
		String secureConnectionStart="";
		String name="";
		String nextHopProtocol="";
		String initiatorType="";
		String loadEventEnd="";

		String appName = "";
		String createValueString = "";
		String baseURL="";

		// value calculation
		double Unload;
		double Redirect;
		double AppCache;
		double TTFB;
		double Processing;
		double Dom_Interactive;
		double Dom_Complete;
		double Content_load;
		double Page_load;

		System.setProperty("webdriver.chrome.driver","E:\\Jars\\ChromeDriver-79\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		String baseUrl = "https://www.johnhancock.com/individual.html";
		driver.get(baseUrl);
		Thread.sleep(3000);
		baseURL = driver.getCurrentUrl();

		appName = baseURL.startsWith("https://") == true ? baseURL.substring(8, baseUrl.lastIndexOf(".com")+4) : baseURL.substring(7, baseUrl.lastIndexOf(".com")+4);
		JavascriptExecutor js =(JavascriptExecutor)driver;

		// Navigation Timings
		String navigationStatusString = js.executeScript("return window.performance.getEntries()["+0+"]").toString();
		System.out.println("Navigation : " + navigationStatusString);
		// get Navigation value string length
		int length = navigationStatusString.length();
		System.out.println(length);
		// get String without {}
		String newString = navigationStatusString.substring(1, navigationStatusString.length()-1);
		System.out.println("new " + newString);
		// put the string in map
		String[] keyValPairs = newString.split(",");
		Map<String,String> navigationValMap = new HashMap<>();
		// Push to map
		for (String str : keyValPairs) {
			String[] key = str.split("=");
			navigationValMap.put(key[0].trim(), key[1].trim());
		}

		// Values from Entries-Navigation
		redirectCount=navigationValMap.get("redirectCount");
		encodedBodySize=navigationValMap.get("encodedBodySize");
		unloadEventEnd=navigationValMap.get("unloadEventEnd");
		responseEnd=navigationValMap.get("responseEnd");
		domainLookupEnd=navigationValMap.get("domainLookupEnd");
		unloadEventStart=navigationValMap.get("unloadEventStart");
		domContentLoadedEventStart=navigationValMap.get("domContentLoadedEventStart");
		type=navigationValMap.get("type");
		decodedBodySize=navigationValMap.get("decodedBodySize");
		duration=navigationValMap.get("duration");
		redirectStart=navigationValMap.get("redirectStart");
		connectEnd=navigationValMap.get("connectEnd");
		requestStart=navigationValMap.get("requestStart");
		startTime=navigationValMap.get("startTime");
		fetchStart=navigationValMap.get("fetchStart");
		serverTiming=navigationValMap.get("serverTiming");
		domContentLoadedEventEnd=navigationValMap.get("domContentLoadedEventEnd");
		entryType=navigationValMap.get("entryType");
		workerStart=navigationValMap.get("workerStart");
		responseStart=navigationValMap.get("responseStart");
		domInteractive=navigationValMap.get("domInteractive");
		domComplete=navigationValMap.get("domComplete");
		domainLookupStart=navigationValMap.get("domainLookupStart");
		redirectEnd=navigationValMap.get("redirectEnd");
		transferSize=navigationValMap.get("transferSize");
		connectStart=navigationValMap.get("connectStart");
		loadEventStart=navigationValMap.get("loadEventStart");
		secureConnectionStart=navigationValMap.get("secureConnectionStart");
		name=navigationValMap.get("name");
		nextHopProtocol=navigationValMap.get("nextHopProtocol");
		initiatorType=navigationValMap.get("initiatorType");
		loadEventEnd=navigationValMap.get("loadEventEnd");

		// Event Counter
		double Onload = Double.parseDouble(loadEventEnd)-Double.parseDouble(loadEventStart);
		double OnContentload = Double.parseDouble(domContentLoadedEventEnd)-Double.parseDouble(domContentLoadedEventStart);
		double domloading = Double.parseDouble(domInteractive)-Double.parseDouble(responseEnd);

		// Calculation
		Unload = Double.parseDouble(unloadEventEnd)-Double.parseDouble(unloadEventStart);
		Redirect = Double.parseDouble(redirectEnd)-Double.parseDouble(redirectStart);
		AppCache = Double.parseDouble(domainLookupStart)-Double.parseDouble(fetchStart);
		TTFB = Double.parseDouble(responseStart)-Double.parseDouble(connectEnd);
		Processing = Double.parseDouble(loadEventStart)-Double.parseDouble(responseEnd);
		Dom_Interactive = Double.parseDouble(domInteractive)-domloading;
		Dom_Complete = Double.parseDouble(domComplete)-domloading;
		Content_load = OnContentload;
		Page_load = Onload;

		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String stringDate = sdf.format(date);
		createValueString = appName+","+baseUrl+","+Unload+","+Redirect+","+AppCache+","+TTFB+","+Processing+","+Dom_Interactive+","+Dom_Complete+","+Content_load+","+Page_load+","+stringDate;
		System.out.println(createValueString);

	}


}
