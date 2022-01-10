Feature: submitting a request


Scenario: the logged in employee can submits a reimbursement request
	Given the user logged in
	When the inpur data is entered and 
	And the submit button pressed
	Then the alert box shows submitted
	