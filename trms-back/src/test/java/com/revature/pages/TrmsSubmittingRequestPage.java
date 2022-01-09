package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TrmsSubmittingRequestPage {
private WebDriver driver;
	
	@FindBy(id="username")
	private WebElement usernameInput;
	@FindBy(id="password")
	private WebElement passwordInput;
	@FindBy(id="loginBtn")
	private WebElement loginBtn;

	@FindBy(id="dataInput")
	private WebElement empInput;
	@FindBy(id="reqbutton")
	private WebElement reqBtn;
	
	public TrmsSubmittingRequestPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToIndex() {
		driver.get("");
	}

	public void navigateTosubRequests() {
		driver.get("file:///C:/Users/cwild/Documents/GitHub/ProOne/p1-trms-wilderchris/trms-front/subrequest.html");
	}
	
	
	
	
	
	
	
	
	
//	public void submitLogin(String username, String password) {
//		usernameInput.sendKeys(username);
//		passwordInput.sendKeys(password);
//		loginBtn.click();
//	}
//	public String getErrorMessage() {
//		WebElement errorMsg = driver.findElement(By.tagName("h3"));
//		return errorMsg.getText();
//	}
//
//	public void submitRequestorId(int empId) {
//		String empid = String.valueOf(empId);
//		empInput.sendKeys(empid);
//		reqBtn.click();
//	}
	
}
