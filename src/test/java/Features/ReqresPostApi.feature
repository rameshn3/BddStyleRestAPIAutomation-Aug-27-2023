#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Reqres Post Api feature
 @smoke
  Scenario: reqres post api to create user scenario
    Given provide valid endpoint to create user
    When I send the request to the server with requestbody
    Then I validate the create user response status code
           |201|
    And validate the create user response body fields

@regression
  Scenario Outline: reqres Post api to create more users
    Given provide valid endpoint to create user
    When I send the request to the server with requestbody as "<name>" and "<job>" 
    Then I validate the create user response status code
           |201|
    And validate the create user response body fields

    Examples: 
      | name  		| job                 |
      | morpheus  |leader               |
      | Ramesh Ch |Automation Test lead | 