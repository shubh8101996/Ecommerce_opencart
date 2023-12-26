package com.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.base.Base;
import com.reports.ExtentReportListener;
import com.utility.Utility;

@Listeners(ExtentReportListener.class)
public class TC_RF_001 extends Base {

	String filepth = System.getProperty("user.dir") + "\\test_data\\excel_sheet_data\\test_data.xlsx";

	@Test(description = "Validate Registering an Account by providing only the Mandatory fields")
	public void tc_rf_001() throws InterruptedException {

		Utility.maximizeWindow(driver);
		driver.get(url);
		Thread.sleep(3000);
		Utility.clickElement(driver, Utility.getElement("MyAccount", driver), 20);
		Utility.clickElement(driver, Utility.getElement("Register", driver), 20);
		Utility.readRowWithFlagY(filepth, "registerTestData", "Y");
		Utility.clickElement(driver, Utility.getElement("Privacy", driver));
		Utility.clickElement(driver, Utility.getElement("ContinueBtn", driver));
		Utility.assertTextEqualsByElementText(driver, Utility.getElement("Heading", driver),
				"Your Account Has Been Created!", 20);
		
		

	}

}
