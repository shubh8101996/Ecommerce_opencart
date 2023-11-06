package com.page_class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar_Page {
	

	WebDriver driver;
	

	@FindBy(xpath = "//span[text()='My Account']")
	public WebElement MyAccount;

	@FindBy(xpath = "//a[text()='Register']")
	public WebElement Register;

	@FindBy(xpath = "//a[text()='Login']")
	public WebElement Login;

	public NavBar_Page(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}
	
	
	
	
	
	

}
