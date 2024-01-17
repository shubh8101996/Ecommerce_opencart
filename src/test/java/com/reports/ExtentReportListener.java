package com.reports;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.Base;
import com.utility.Utility;

public class ExtentReportListener implements ITestListener {

	private ExtentTest test;
	private Utility utility;

	private static ExtentReports extent;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	static String timestamp = dateFormat.format(new Date());
//	static String Reportpath = System.getProperty("user.dir") + "\\reports\\" + timestamp + ".html";
	static String Reportpath = Paths.get(System.getProperty("user.home"), "Desktop", "reports", timestamp + ".html")
			.toString();

	public static ExtentReports createExtentInstance() {
		if (extent == null) {
			extent = new ExtentReports();
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Reportpath);
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}

	@Override
	public void onTestStart(ITestResult result) {
//		test = extent.createTest(result.getName());
		createExtentInstance();
		String testName = result.getName();
		String testDescription = Utility.getTestMethodDescription(result.getInstance().getClass(), testName);
		test = extent.createTest(testName, testDescription);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Failed");
		WebDriver driver = Base.getDriver();

		// Capture screenshot and get the file path
		String screenshotFilePath = Utility.captureScreenshotOnDesktop(driver, result.getName());

		// Add screen capture to Extent Report
		if (screenshotFilePath != null) {
			try {
				test.addScreenCaptureFromPath(screenshotFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			String errorMessage = throwable.getMessage();
			test.log(Status.FAIL, "Error Trace: " + errorMessage);
		}
	}

	private String getStackTrace(Throwable throwable) {
		StringBuilder stackTrace = new StringBuilder();
		for (StackTraceElement element : throwable.getStackTrace()) {
			stackTrace.append(element.toString()).append("\n");
		}
		return stackTrace.toString();
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