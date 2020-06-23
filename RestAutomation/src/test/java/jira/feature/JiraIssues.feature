Feature: Operations on Jira Issues

Scenario: create issue with description
	Given Request URL is initiated by Jira
	And Authentication is performed 
	When Body is posted with summary 
	Then post staus code should be 201 
	And Verify response is within 5 secs
	
Scenario: Get recently created jira issue 
	Given Request URL is initiated by Jira
	And Authentication is performed 
	When Recently created Jira issue is passed 
	Then get staus code should be 200 
	And Verify response is within 5 secs
	

Scenario: Delete recently created jira issue 
	Given Request URL is initiated by Jira
	And Authentication is performed 
	When Delete Recently created Jira issue is passed 
	Then Delete staus code should be 204 
	And Verify response is within 5 secs
	
