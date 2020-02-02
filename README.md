# PerfoHar
A Performance Evaluation Framework, build on JAVA works with Selenium </br>

# Download Link
https://github.com/SohamRoyNoel/PerfoHar/releases/download/PerformanceMeasure/PerfoMeasure-1.3.jar

# Dependencies :
Fillo Jar 1.15 : https://jar-download.com/artifacts/com.codoid.products/fillo/1.15/source-code < /br>
mariadb-java-client-2.4.4 : https://downloads.mariadb.com/Connectors/java/connector-java-2.4.4/mariadb-java-client-2.4.4.jar </br>
mssql-jdbc-7.4.1.jre8 : https://repo1.maven.org/maven2/com/microsoft/sqlserver/mssql-jdbc/7.4.1.jre8/mssql-jdbc-7.4.1.jre8.jar

# How To Use With Existing Selenium Code:
```
public class Main { 
	       public static App ap = new App(); 
	        public static void main(String[] args) throws InterruptedException { 
		System.setProperty("webdriver.chrome.driver","<Chrome Driver Location>"); 
		WebDriver driver = new ChromeDriver(); 
		String baseUrl = "https://Redirectable_link.com"; 
		driver.get(baseUrl); </br>

    // Get The Performance List : Pass the driver instance 
		ap.Performer(driver);	
		driver.quit(); 
    
    // In the End You have to call this method in order to get The Excel Report 
		ap.ExcelAgent(); 
		
    // from Version 1.5 you can upload your performance in Azure Cloud Database
                ap.AzureAgent();
	} 

}
```
# Report Generation Path
PerfoHar Will explicitly create a folder on the project Location called 'Output', all Excel Sheets will be found there

# Expectation from Next Version
1. Customizable HTML Performance Dashboard. </br>
2. Option to set VALUES in the runtime.  </br>
3. No Excel or Database Revolution. </br>

# Sample Code To Test The .jar
```
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.proHar.perfoMeasure.main.App;


public class Main {
	public static App ap = new App();

# AZURE database login link
https://portal.azure.com/?quickstart=true#home
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
		// AZURE Migration
		ap.AzureAgent();
		// Excel Report
		ap.ReportingAgent();
	}

}
```
