Feature: the get requests by requestor

Background: Given the user is on the get requests by employee id page

Scenario: the requests show up in the table
	Given the user is on the get requests page
	When the submit button is clicked
	Then the table shows the requests for the employee
	
Scenario: logged in employees can submit requests
	Given the employee is on the submit requests page
	When the fields are completed
	And the user clicks the submit button
	Then the request is added to the table
