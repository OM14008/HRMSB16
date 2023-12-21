Feature: Adding new job in HRMS

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    And user is successfully logged in the application
    @AddJob
    Scenario: User adds a new job
    * user clicks on the admin button
    * user clicks on the job
    * user clicks on the job Title
    * user clicks on the add button
    * user enters job "book keeping" title
    * user enters job description "learning to code"
    * user enters job note "this is the best time"
    * user clicks on the save button
    * verify data is stored properly in database

