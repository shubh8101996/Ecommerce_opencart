package com.test;

import org.testng.annotations.Test;

import com.base.Base;
import com.page_class.NavBar_Page;
import com.page_class.Register_Page;
import com.utility.Utility;

public class TC_RF_004 extends Base {

	Register_Page register;
	NavBar_Page nav;

	@Test(description = "Validate proper notification messages are displayed for the mandatory fields, when you "
			+ "don't provide any fields in the " + "'Register Account' page and submit")
	public void test1() throws InterruptedException {

		register = new Register_Page(driver);
		nav = new NavBar_Page(driver);

		Utility.maximizeWindow(driver);
		driver.get(url);
		Thread.sleep(3000);
		Utility.clickElement(driver, nav.MyAccount, 20);
		Utility.clickElement(driver, nav.Register, 10);
		Utility.clickElement(driver, register.ContinueBtn);

		Utility.assertTextEqualsByElementText(driver, register.errorFirstname,
				"First Name must be between 1 and 32 characters!", 10);
		Utility.assertTextEqualsByElementText(driver, register.errorLastname,
				"Last Name must be between 1 and 32 characters!", 10);
		Utility.assertTextEqualsByElementText(driver, register.errorEmail,
				"E-Mail Address does not appear to be valid!", 10);
		Utility.assertTextEqualsByElementText(driver, register.errorPassword,
				"Password must be between 4 and 20 characters!", 10);

	}

}
