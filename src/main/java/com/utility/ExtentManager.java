package com.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
    private static ExtentReports extent;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	static String timestamp = dateFormat.format(new Date());
	static String Reportpath = System.getProperty("user.dir") + "\\reports\\" + timestamp + ".html";

    public static ExtentReports createExtentInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Reportpath);
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
}
