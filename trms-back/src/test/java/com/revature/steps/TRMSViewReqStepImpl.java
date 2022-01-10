package com.revature.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
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

public class TRMSViewReqStepImpl {
	
	public static WebDriver driver;
		
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
	}

	
	
	
@Given("the user is logged in")
public void the_user_is_logged_in() throws InterruptedException {
	driver.get("file:///C:/Users/cwild/Documents/GitHub/ProOne/p1-trms-wilderchris/trms-front/view.html");
	WebElement loginLink = driver.findElement(By.id("login"));
	loginLink.click();

	WebElement userIn = driver.findElement(By.id("username"));
	WebElement passIn = driver.findElement(By.id("password"));
	userIn.sendKeys("bart");
	passIn.sendKeys("pass");
	WebElement loginBtn = driver.findElement(By.id("loginBtn"));
	loginBtn.click();
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(5))
			.pollingEvery(Duration.ofMillis(50));
	wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("loginForm"), 1));

}

@When("the viewRequests page is loaded")
public void the_view_requests_page_is_loaded() {
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(5))
			.pollingEvery(Duration.ofMillis(50));
	wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"), 1));

}

@Then("the table shows the requests for the logged in employee")
public void the_table_shows_the_requests_for_the_logged_in_employee() {
	 List<WebElement> table = driver.findElements(By.tagName("td"));
	    String text = table.get(1).getText();
	    System.out.println(text);
	    
	    assertFalse(table.isEmpty());

}

@AfterAll
public static void closeDriver() {
	driver.quit();
}


}

