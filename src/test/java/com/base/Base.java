package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.utility.ReadProperties_Utility;
import com.utility.Utility;

public class Base {

	public static WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	ReadProperties_Utility read = new ReadProperties_Utility();
	public String browser = read.getBrowserValue();
	public String url = read.getUrl();
	public String username = read.getUsername();
	public String password = read.getPassword();

	@BeforeTest
	public void browserSetup() throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					projectPath + "\\browser_drivers\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					projectPath + "\\browser_drivers\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					projectPath + "\\browser_drivers\\edgedriver\\msedgedriver.exe");
			EdgeOptions edgeOptions = new EdgeOptions();
			// Add desired options, preferences, and capabilities to the EdgeOptions object
			edgeOptions.setCapability("ms:edgeOptions", "--disable-notifications"); // Example: Disable notifications
			driver = new EdgeDriver(edgeOptions);

		} else {

			throw new Exception("Invalid Browser Value");
		}
	}

	@AfterTest
	public void tearUp(ITestResult result) {

		driver.quit();
	}

}
