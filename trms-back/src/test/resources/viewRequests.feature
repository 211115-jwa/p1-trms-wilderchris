Feature: the view requests by logged employee



Scenario: viewing the requests for the logged in employee
	Given the user is logged in
	When the viewRequests page is loaded
	Then the table shows the requests for the logged in employee
	