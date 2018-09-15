Feature: ESS Login Functionality

#without Examples Keyword
Scenario: ESS Login functionality and JIO Centeral Page Validation

Given user is able to login into ESS using valid username as "ankur.jain" and password as "Word03Pass"
Then user validates Tittle and image on Jio Central Page
Then user validates Links and elements inside frames of Jio Central Page
Then Validates frames and Moving window from Jio Central page to Employee Central
Then Validates Tittle and image on Employee Central Page
