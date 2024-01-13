Feature: select date from calender testing method

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    And user is successfully logged in the application

    @calenderDateSelectMethodTest
    Scenario: user selects from date calender method test
      Given user clicks on Recruitment button
      When user enters From Date calender month year and day
      | Month | Year | Day  | ExpectedDate |
      | Sep   | 1995 | 15   | 1995-09-15   |
      Then date is entered into the From Date field
      When user enters To Date calender month year and day
        | Month | Year | Day  | ExpectedDate |
        | Dec   | 1996 | 21   | 1996-12-21   |
      Then date is entered into the To Date field

