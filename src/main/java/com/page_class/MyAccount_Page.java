package com.page_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount_Page {

	WebDriver driver;

	String filepth = System.getProperty("user.dir") + "\\test_data\\excel_sheet_data\\test_data.xlsx";

	@FindBy(xpath = "//h2[text()='My Account']")
	public WebElement HeadingMyAccount;

	public MyAccount_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	

}
