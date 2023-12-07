package com.utility;

/** 
 * @Author: Shubham Shedge
 * @Version: 1.0
 * @Description: This class contains all Utility functions.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class Utility {

	public static void setImplicitWait(WebDriver driver, int timeoutInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
	}

	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public static void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public static void clickElement(WebDriver driver, By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public static void clickElement(WebDriver driver, WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void clickElement(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void sendKeys(WebDriver driver, By locator, String text, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
	}

	public static void sendKeys(WebDriver driver, WebElement element, String text, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}

	public static void assertTextEqualsByElementText(WebDriver driver, WebElement element, String expectedText,
			int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));

		String actualText = element.getText();

		Assert.assertEquals(actualText, expectedText, "Text mismatch: ");
	}

	public static void assertTextEqualsByElementValue(WebDriver driver, WebElement element, String expectedText,
			int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, expectedText));

		String actualText = element.getAttribute("value");

		Assert.assertEquals(actualText, expectedText, "Text mismatch: ");
	}

	public static void assertElementPresent(WebDriver driver, By by, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Assert.assertTrue(element.isDisplayed(), "Element is not displayed on the page.");
		} catch (org.openqa.selenium.TimeoutException e) {
			Assert.fail("Element is not present on the page within the specified timeout.");
		}
	}

	public static void assertElementPresent(WebDriver driver, WebElement element, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isDisplayed(), "Element is not displayed on the page.");
		} catch (org.openqa.selenium.TimeoutException e) {
			Assert.fail("Element is not present on the page within the specified timeout.");
		}
	}

	public static String readExcelData(String filePath, String sheetName, int rowNum, int colNum) {
		String cellValue = null;
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(rowNum - 1); // Excel rows are 0-based, so subtract 1
			Cell cell = row.getCell(colNum - 1); // Excel columns are 0-based, so subtract 1

			if (cell != null) {
				if (cell.getCellType() == CellType.STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cell.getCellType() == CellType.NUMERIC) {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
			}

			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
	}

	public static void captureScreenshot(WebDriver driver, String testName) {
		try {
			if (driver instanceof TakesScreenshot) {
				TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
				File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				String timestamp = dateFormat.format(new Date());

				String screenshotDirectory = System.getProperty("user.dir") + "\\screenshot\\";

				File directory = new File(screenshotDirectory);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				String screenshotFilePath = screenshotDirectory + testName + "_" + timestamp + ".png";
				File finalScreenshot = new File(screenshotFilePath);
				FileUtils.copyFile(screenshot, finalScreenshot);

				System.out.println("Screenshot saved as: " + screenshotFilePath);

			} else {
				System.out.println("Driver does not support taking screenshots.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] readRowWithFlagY(String filePath, String sheetName, String flagVal) {
		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);

			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row != null) {
					Cell flagCell = row.getCell(0); // Assuming the flag is in the first column (0-based index)

					if (flagCell != null && flagCell.getCellType() == CellType.STRING) {
						String flag = flagCell.getStringCellValue();

						if (flagVal.equalsIgnoreCase(flag)) {
							int lastColNum = row.getLastCellNum();
							String[] rowData = new String[lastColNum - 1]; // Excluding the flag column
							int rowDataIndex = 0;

							for (int colNum = 1; colNum < lastColNum; colNum++) {
								Cell dataCell = row.getCell(colNum);

								if (dataCell != null) {
									if (dataCell.getCellType() == CellType.STRING) {
										rowData[rowDataIndex] = dataCell.getStringCellValue();
									} else if (dataCell.getCellType() == CellType.NUMERIC) {
										rowData[rowDataIndex] = String.valueOf(dataCell.getNumericCellValue());
									}
									rowDataIndex++;
								}
							}

							return rowData;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null; // Return null if no row with the flag "Y" is found
	}

}