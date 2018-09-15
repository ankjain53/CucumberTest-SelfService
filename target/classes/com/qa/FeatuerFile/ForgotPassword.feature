Feature: ESS Forgot Password

#without Examples Keyword
Scenario: ESS Forgot Pasword functionality verification

Given user is on ESS Forgot Password Page
Then user validates Logo and Tittle Forgot Password home page
Then user validates second and third tittle of Forgot Password home page
Then Validates Error Message on entering incorrect userid/pwd on Password page
Then Validates Error Message on not entering any userid/pwd on forgot Password page
Then Validates Message on not entering correct userid/pwd on Forgot Password page