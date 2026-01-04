Feature: Test Scenarios for General store app
 
 
 

 
	@Smoke @Regression
  Scenario: User land product list page
  Given User is on landing page of the application
  When user select 'India' from the country dropdown 
  And user enter "Tarun" in username field
  And user selects 'Male' as gender in radio button
  Then user lands on product list page
  
  

