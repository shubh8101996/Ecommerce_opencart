package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
	private ExtentReports extent = new ExtentReports();
	private ExtentTest test;
	public WebDriver driver;

	@Override
	public void onStart(ITestContext context) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timestamp = dateFormat.format(new Date());
		String Reportpath = System.getProperty("user.dir") + "\\reports\\" + timestamp + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Reportpath);
		extent.attachReporter(htmlReporter);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
	    test.fail(result.getName() + " failed"); // Log the test name and "failed"
	    test.fail(result.getThrowable()); // Capture and log the exception details

	    // Capture a screenshot and add it to the report (you can use your Utility method)
	    Utility.captureScreenshot(driver, "Failure_Screenshot_" + result.getName());
	}


	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	// Implement other ITestListener methods as needed

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
