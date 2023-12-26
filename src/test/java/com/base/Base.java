package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.utility.Utility;

public class Base {

	public static WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	public String browser = Utility.getPropertyDirectly("browser");
	public String url = Utility.getPropertyDirectly("url");
	

	@BeforeClass(alwaysRun = true)
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

	public static WebDriver getDriver() {
		return driver;
	}

	public static void login() {

		Utility.clickElement(driver, Utility.getElement("MyAccount", driver), 20);
		Utility.clickElement(driver, Utility.getElement("Login", driver), 20);
		Utility.sendKeys(driver, Utility.getElement("email", driver), Utility.getPropertyDirectly("username"), 20);
		Utility.sendKeys(driver, Utility.getElement("password", driver), Utility.getPropertyDirectly("password"), 20);
		Utility.clickElement(driver, Utility.getElement("loginBtn", driver), 10);
		Utility.assertTextEqualsByElementText(driver, Utility.getElement("HeadingMyAccount", driver), "My Account", 10);
	}

	@AfterClass(alwaysRun = true)
	public void tearUp() {
//		driver.quit();
	}

}
