package com.revature.steps;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.revature.pages.TrmsRequestPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrmsRequestStepImpl {
	private WebDriver driver;
	private TrmsRequestPage trmsRPage;
	private FluentWait wait = new FluentWait(driver);
	
	{
	File file = new File("src/test/resources/chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	
	driver = new ChromeDriver();
	trmsRPage = new TrmsRequestPage(driver);
	}
	
	@Given("the user is on the get requests page")
	public void the_user_is_on_the_get_requests_page() {
		trmsRPage.navigateToRequests();
	}

	@When("the submit button is clicked")
	public void the_submit_button_is_clicked() {
		trmsRPage.submitRequestorId(10);
	}

	@SuppressWarnings("deprecation")
	@Then("the table shows the requests for the employee")
	public void the_table_shows_the_requests_for_the_employee() {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 assertNotEquals(null, driver.findElement(By.tagName("td")));
	}
	
	@Given("the employee clicks the log in button")
	public void the_employee_clicks_the_log_in_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("employee enters the username and password")
	public void employee_enters_the_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the employee is shown as logged in")
	public void the_employee_is_shown_as_logged_in() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

//	@Given("the employee is on the submit requests page")
//	public void the_employee_is_on_the_submit_requests_page() {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@When("the fields are completed")
//	public void the_fields_are_completed() {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@When("the user clicks the submit button")
//	public void the_user_clicks_the_submit_button() {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("the request is added to the table")
//	public void the_request_is_added_to_the_table() {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
}
