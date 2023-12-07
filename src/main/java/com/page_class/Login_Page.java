package com.page_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.Utility;

public class Login_Page {

	WebDriver driver;

	String filepth = System.getProperty("user.dir") + "\\test_data\\excel_sheet_data\\test_data.xlsx";

	@FindBy(id = "input-email")
	public WebElement email;

	@FindBy(id = "input-password")
	public WebElement password;

	@FindBy(xpath = "//button[text()='Login']")
	public WebElement LoginBtn;

	public Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterCredLogin(String flagVal) {

		String[] rowData = Utility.readRowWithFlagY(filepth, "loginTestData", flagVal);
		Utility.sendKeys(driver, email, rowData[0], 10);
		Utility.sendKeys(driver, password, rowData[1], 10);

	}

	public void clickLoginBtn() {

		Utility.clickElement(driver, LoginBtn, 10);

	}

}
