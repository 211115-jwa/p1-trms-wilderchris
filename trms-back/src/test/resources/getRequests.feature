Feature: the get requests by requestor



Scenario: viewing the requests by the employee id
	Given the user is on the page
	When the employee id is entered
	Then the table shows the requests for the employee
	