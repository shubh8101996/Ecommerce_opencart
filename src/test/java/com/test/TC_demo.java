package com.test;

import org.testng.annotations.Test;

import com.base.Base;
import com.utility.Utility;

public class TC_demo extends Base {

	@Test
	public void testcaseDemo() throws InterruptedException {

		driver.get(url);
		Utility.maximizeWindow(driver);
		

	}

}
