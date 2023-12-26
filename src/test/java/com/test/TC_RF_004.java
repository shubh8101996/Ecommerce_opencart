package com.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.Base;
import com.reports.ExtentReportListener;
import com.utility.Utility;

@Listeners(ExtentReportListener.class)
public class TC_RF_004 extends Base {

	@Test(description = "Validate proper notification messages are displayed for the mandatory fields, when you "
			+ "don't provide any fields in the " + "'Register Account' page and submit")
	public void tc_rf_004() throws InterruptedException {

		Utility.maximizeWindow(driver);
		driver.get(url);
		Thread.sleep(3000);
		Utility.clickElement(driver, Utility.getElement("MyAccount", driver), 20);
		Utility.clickElement(driver, Utility.getElement("Register", driver), 20);
		Utility.clickElement(driver, Utility.getElement("ContinueBtn", driver));

		Utility.assertTextEqualsByElementText(driver, Utility.getElement("errorFirstname", driver),
				"First Name must be between 1 and 32 characters!", 10);
		Utility.assertTextEqualsByElementText(driver, Utility.getElement("errorLastname", driver),
				"Last Name must be between 1 and 32 characters!", 10);
		Utility.assertTextEqualsByElementText(driver, Utility.getElement("errorEmail", driver),
				"E-Mail Address does not appear to be valid!", 10);
		Utility.assertTextEqualsByElementText(driver, Utility.getElement("errorPassword", driver),
				"Password must be between 4 and 20 characters!", 10);

	}

}
