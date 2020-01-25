# PerfoHar
A Performance Evaluation Framework, build on JAVA works with Selenium </br>

# Dependencies :
Fillo Jar 1.15 : https://jar-download.com/artifacts/com.codoid.products/fillo/1.15/source-code < /br>

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
