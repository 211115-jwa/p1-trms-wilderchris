package com.revature.steps;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class PendingRequestStepImpl {

public static WebDriver driver;
	
	
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		
			driver = new ChromeDriver();
		
	}
	
	
	
	
	@When("the page loads the managers pending reimbursements")
	public void the_page_loads_the_managers_pending_reimbursements() {
		driver.get("file:/C:/Users/cwild/Documents/GitHub/ProOne/p1-trms-wilderchris/trms-front/index.html");
		
		WebElement loginLink = driver.findElement(By.id("login"));
		loginLink.click();

		WebElement userIn = driver.findElement(By.id("username"));
		WebElement passIn = driver.findElement(By.id("password"));
		userIn.sendKeys("homer");
		passIn.sendKeys("pass");
		WebElement loginBtn = driver.findElement(By.id("loginBtn"));
		loginBtn.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(5));
		driver.navigate().refresh();

	}
	@When("the pending requests page is loaded")
	public void the_view_requests_page_is_loaded() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(50));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"), 1));

	}
	@Then("the table shows the requests for the manager")
	public void the_table_shows_the_requests_for_the_manager() {
		 List<WebElement> table = driver.findElements(By.tagName("td"));
		    String text = table.get(1).getText();
		    System.out.println(text);
		assertFalse(table.isEmpty());
	}

	
	
	@AfterAll
	public static void closeDriver() {
		//driver.switchTo().alert().accept();
		driver.close();
		driver.quit();
	}
}
