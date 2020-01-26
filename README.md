# PerfoHar
A Performance Evaluation Framework, build on JAVA works with Selenium </br>

# Download Link
https://github.com/SohamRoyNoel/PerfoHar/releases/download/PerformanceMeasure/PerfoMeasure-1.3.jar

# Dependencies :
Fillo Jar 1.15 : https://jar-download.com/artifacts/com.codoid.products/fillo/1.15/source-code 

# How To Use With Existing Selenium Code:

public class Main { </br>
	       public static App ap = new App(); </br>
	        public static void main(String[] args) throws InterruptedException { </br>
		System.setProperty("webdriver.chrome.driver","<Chrome Driver Location>"); </br>
		WebDriver driver = new ChromeDriver(); </br>
		String baseUrl = "https://Redirectable_link.com"; </br>
		driver.get(baseUrl); </br>

    // Get The Performance List : Pass the driver instance </br>
		ap.Performer(driver);	 </br>
		driver.quit(); </br>
    
    // In the End You have to call this method in order to get The Excel Report </br>
		ap.ReportingAgent(); </br>
	} </br>

} </br>

# Report Generation Path
PerfoHar Will explicitly create a folder on the project Location called 'Output', all Excel Sheets will be found there

# Expectation from Next Version
1. Customizable HTML Performance Dashboard. </br>
2. Option to set VALUES in the runtime.  </br>
3. No Excel or Database Revolution. </br>

# Sample Code To Test The .jar

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.proHar.perfoMeasure.main.App;


public class Main {
	public static App ap = new App();

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver","E:\\Jars\\ChromeDriver-79\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		String baseUrl = "https://www.johnhancock.com/individual.html";

		driver.get(baseUrl);
		System.out.println("page loaded");

		ap.Performer(driver);
		
		driver.get("https://www.stackoverflow.com/questions/35705795/how-to-upload-a-jar-file-to-github");
		ap.Performer(driver);
		
		driver.get("https://www.guru99.com/");
		ap.Performer(driver);
		
		driver.get("https://www.flipkart.com/");
		ap.Performer(driver);
		
		driver.get("https://www.amazon.com/");
		ap.Performer(driver);
		
		driver.get("https://www.manulife.com/");
		ap.Performer(driver);
		
		driver.get("https://www.walmart.com/");
		ap.Performer(driver);
		
		driver.quit();
		ap.ReportingAgent();
	}

}
