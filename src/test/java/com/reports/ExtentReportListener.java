package com.reports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.Base;

public class ExtentReportListener implements ITestListener {

	private ExtentReports extent = ExtentManager.createExtentInstance();
	private ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Failed");
		WebDriver driver = Base.getDriver();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timestamp = dateFormat.format(new Date());
		String screenshotDirectory = System.getProperty("user.dir") + "\\screenshot\\";
		String screenshotFilePath = screenshotDirectory + result.getName() + "_" + timestamp + ".png";

		File dest = new File(screenshotFilePath);
		try {
			FileUtils.copyFile(src, dest);
			test.addScreenCaptureFromPath(screenshotFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
