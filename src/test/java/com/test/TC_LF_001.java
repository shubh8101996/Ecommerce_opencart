package com.test;

import org.testng.annotations.Test;

import com.base.Base;
import com.page_class.Login_Page;
import com.page_class.MyAccount_Page;
import com.page_class.NavBar_Page;
import com.utility.Utility;

public class TC_LF_001 extends Base {

	Login_Page login;
	NavBar_Page nav;
	MyAccount_Page mypage;

	@Test(description = "Validate logging into the Application using valid credentials")
	public void test1() throws InterruptedException {

		login = new Login_Page(driver);
		nav = new NavBar_Page(driver);
		mypage = new MyAccount_Page(driver);

		Utility.maximizeWindow(driver);
		driver.get(url);
		Thread.sleep(3000);
		Utility.clickElement(driver, nav.MyAccount, 20);
		Utility.clickElement(driver, nav.Login, 10);
		login.enterCredLogin("Y");
		login.clickLoginBtn();
		Utility.assertTextEqualsByElementText(driver, mypage.HeadingMyAccount, "My Account", 10);

	}

}
