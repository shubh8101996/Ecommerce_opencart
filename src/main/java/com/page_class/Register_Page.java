package com.page_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.Utility;

public class Register_Page {

	WebDriver driver;

	String filepth = System.getProperty("user.dir") + "\\test_data\\excel_sheet_data\\test_data.xlsx";

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	public WebElement Heading;

	@FindBy(id = "input-firstname")
	public WebElement first_name;

	@FindBy(id = "input-lastname")
	public WebElement last_name;

	@FindBy(id = "input-email")
	public WebElement email;

	@FindBy(id = "input-password")
	public WebElement password;

	@FindBy(id = "input-newsletter")
	public WebElement Subscribe;

	@FindBy(name = "agree")
	public WebElement Privacy;

	@FindBy(xpath = "//button[text()='Continue']")
	public WebElement ContinueBtn;

	@FindBy(id = "error-firstname")
	public WebElement errorFirstname;

	@FindBy(id = "error-lastname")
	public WebElement errorLastname;

	@FindBy(id = "error-email")
	public WebElement errorEmail;

	@FindBy(id = "error-password")
	public WebElement errorPassword;

	@FindBy(xpath = "//*[contains(text(),' Warning: E-Mail Address is already registered!')]")
	public WebElement alert;

	public Register_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterCredentials(String flagVal) {

		String[] rowData = Utility.readRowWithFlagY(filepth, "registerTestData",flagVal);
		Utility.sendKeys(driver, first_name, rowData[0], 10);
		Utility.sendKeys(driver, last_name, rowData[1], 10);
		Utility.sendKeys(driver, email, rowData[2], 10);
		Utility.sendKeys(driver, password, rowData[3], 10);
	}
	
	

}
