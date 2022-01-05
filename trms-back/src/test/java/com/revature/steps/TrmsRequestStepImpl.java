package com.revature.steps;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrmsRequestStepImpl {

	//private TrmsRequestPage loginPage;
	private WebDriver driver;
	{
	File file = new File("src/test/resources/chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	
	driver = new ChromeDriver();
	}
	
	@Given("the user is on the get requests page")
	public void the_user_is_on_the_get_requests_page() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the submit button is clicked")
	public void the_submit_button_is_clicked() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the table shows the requests for the employee")
	public void the_table_shows_the_requests_for_the_employee() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Given("the employee is on the submit requests page")
	public void the_employee_is_on_the_submit_requests_page() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the fields are completed")
	public void the_fields_are_completed() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks the submit button")
	public void the_user_clicks_the_submit_button() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the request is added to the table")
	public void the_request_is_added_to_the_table() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}
