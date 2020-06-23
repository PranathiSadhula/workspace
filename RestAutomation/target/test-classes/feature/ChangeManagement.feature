Feature: Create a new Change Request
Scenario: This scenario is to create a new Change Request with description
	Given User to be authenticated
	And CR will be added with short_desc 
	When new CR is created
	Then the status code should be 201
		