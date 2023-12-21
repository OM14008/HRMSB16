Feature: select date from calender testing method

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    And user is successfully logged in the application

    @calenderDateSelectMethodTest
    Scenario: user selects date from calender method test
      Given user clicks on Recruitment button
      When user enters month, year and day
      | Sep | 1995 | 15 | 1995-09-15 |
      | Dec | 1996 | 21 | 1996-12-21 |

