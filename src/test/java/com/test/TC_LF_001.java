package com.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.base.Base;
import com.reports.ExtentReportListener;
import com.utility.Utility;

@Listeners(ExtentReportListener.class)
public class TC_LF_001 extends Base {

	@Test(description = "Validate logging into the Application using valid credentials")
	public void tc_lf_001() throws InterruptedException {

		Utility.maximizeWindow(driver);
		driver.get(url);
		Thread.sleep(3000);
		Utility.clickElement(driver, Utility.getElement("MyAccount", driver), 20);
		Utility.clickElement(driver, Utility.getElement("Login", driver), 20);
		Utility.sendKeys(driver, Utility.getElement("email", driver), Utility.getPropertyDirectly("username"), 20);
		Utility.sendKeys(driver, Utility.getElement("password", driver), Utility.getPropertyDirectly("password"), 20);
		Utility.clickElement(driver, Utility.getElement("loginBtn", driver), 10);
		Utility.assertTextEqualsByElementText(driver, Utility.getElement("HeadingMyAccount", driver), "My Account", 10);
	}

}
