Feature: ESS Home Page

#without Examples Keyword
Scenario Outline:: ESS Login Home Page Validation

Given user is on ESS Home Page
Then user validates logo and tittle Page
Then user validates second and third tittle
Then Validates Error Message on entering incorrect "<userid>" and "<pwd>" on Home page

Then Validates Tool Tip validation for Forgot/Generate Password on Home Page

Examples:
|userid|pwd|
|ankur.jain|Word09Pass|




