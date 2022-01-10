Feature: get the pending requests for the logged in supervisor


Scenario: viewing the reimbursements by logged in maanger
	Given the user is on the page
	When the page loads the managers pending reimbursements
	When the pending requests page is loaded
	Then the table shows the requests for the manager
	
