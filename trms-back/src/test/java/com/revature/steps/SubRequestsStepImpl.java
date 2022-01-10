package com.revature.steps;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.Alert;
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

public class SubRequestsStepImpl {

	public static WebDriver driver;

	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

		driver = new ChromeDriver();
	}

	
	@Given("the user logged in")
	public void the_user_logged_in() {
		driver.get("file:///C:/Users/cwild/Documents/GitHub/ProOne/p1-trms-wilderchris/trms-front/index.html");
	
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait1.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("login"), 0));

		WebElement loginLink = driver.findElement(By.id("login"));
		loginLink.click();

		WebElement userIn = driver.findElement(By.id("username"));
		WebElement passIn = driver.findElement(By.id("password"));
		userIn.sendKeys("sell");
		passIn.sendKeys("pass");
		WebElement loginBtn = driver.findElement(By.id("loginBtn"));
		loginBtn.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("loginForm"), 1));
		
		driver.get("file:///C:/Users/cwild/Documents/GitHub/ProOne/p1-trms-wilderchris/trms-front/subrequest.html");

		
	}

	
	@When("the inpur data is entered and")
	public void the_inpur_data_is_entered_and() {

		WebElement eDate = driver.findElement(By.id("eventdate"));
		WebElement eTime = driver.findElement(By.id("etime"));
		WebElement eLocalA = driver.findElement(By.id("street"));
		WebElement eLocalC = driver.findElement(By.id("city"));
		WebElement eLocalS = driver.findElement(By.id("state"));
		WebElement eCost = driver.findElement(By.id("cost"));
		WebElement eGrad = driver.findElement(By.id("gformat"));
		WebElement eType = driver.findElement(By.id("eventtype"));
		WebElement desc = driver.findElement(By.id("descbox"));
		eDate.sendKeys("01011999");
		eTime.sendKeys("0245PM");
		eLocalA.sendKeys("123 Man St");
		eLocalC.sendKeys("Hubert");
		eLocalS.sendKeys("NC");
		eCost.sendKeys(String.valueOf(100));
		eGrad.sendKeys(String.valueOf(3));
		eType.sendKeys(String.valueOf(6));
		desc.sendKeys("Description for Selenium Test");

		WebElement loginBtn = driver.findElement(By.id("submitbutton"));
		loginBtn.click();

	}

	@When("the submit button pressed")
	public void the_submit_button_pressed() {
		WebElement loginBtn = driver.findElement(By.id("submitbutton"));
		loginBtn.click();
	}

	@Then("the alert box shows submitted")
	public void the_alert_box_shows_submitted() {

		assertTrue(checkAlert());

	}

	public boolean checkAlert() {// nice stackoverflow
		try {
			Wait<WebDriver> waitx = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5));
			waitx.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	@AfterAll
	public static void closeDriver() {
		driver.switchTo().alert().accept();
		driver.close();
		driver.quit();
	}

}
