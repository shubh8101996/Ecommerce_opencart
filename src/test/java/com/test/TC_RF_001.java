package com.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.base.Base;
import com.page_class.NavBar_Page;
import com.page_class.Register_Page;
import com.utility.ExtentReportListener;
import com.utility.Utility;

@Listeners(ExtentReportListener.class)
public class TC_RF_001 extends Base {

	Register_Page register;
	NavBar_Page nav;

	@Test(description = "Validate Registering an Account by providing only the Mandatory fields")
	public void test1() throws InterruptedException {

		register = new Register_Page(driver);
		nav = new NavBar_Page(driver);

		Utility.maximizeWindow(driver);
		driver.get(url);
		Thread.sleep(3000);
		Utility.clickElement(driver, nav.MyAccount, 20);
		Utility.clickElement(driver, nav.Register, 10);

		register.enterCredentials();
//		Utility.captureScreenshot(driver, "Validate Registering an Account by providing only the Mandatory fields");

//		Utility.clickElement(driver, register.Privacy);
		Utility.clickElement(driver, register.ContinueBtn);
		Utility.assertTextEqualsByElementText(driver, register.Heading, "Your Account Has Been Created!", 0);

	}

}
