package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.page_class.Login_Page;
import com.page_class.MyAccount_Page;
import com.page_class.NavBar_Page;
import com.utility.Utility;

public class Base {

	public static WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	public String browser = Utility.getPropertyDirectly("browser");
	public String url = Utility.getPropertyDirectly("url");
	public static Login_Page login;
	public static MyAccount_Page myaccount;
	public static NavBar_Page nav;

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
		
		login = new Login_Page(driver);
        myaccount = new MyAccount_Page(driver);
        nav = new NavBar_Page(driver);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void login() {

		Utility.clickElement(driver, nav.MyAccount, 20);
		Utility.clickElement(driver, nav.Login, 10);
		Utility.sendKeys(driver, login.email, Utility.getPropertyDirectly("username"), 10);
		Utility.sendKeys(driver, login.password, Utility.getPropertyDirectly("password"), 10);
		Utility.clickElement(driver, login.LoginBtn, 10);
		Utility.assertTextEqualsByElementText(driver, myaccount.HeadingMyAccount, "My Account", 10);
	}

	@AfterClass(alwaysRun = true)
	public void tearUp() {
		driver.quit();
	}

}
