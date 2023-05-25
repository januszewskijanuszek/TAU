Feature: Logowanie na stronie Sauce Demo

  Scenario: Correct login
    Given I am on the Sauce Demo login page
    When I enter valid username
    When I enter valid password
    And I click the login button
    Then I should be logged in successfully
    And I should see the products page

  Scenario: incorrect Login (empty name space)
    Given I am on the Sauce Demo login page
    When I enter an empty username
    When I enter a valid password
    And I click the login button
    Then I should see an error message

  Scenario: incorrect Login (empty password space)
    Given I am on the Sauce Demo login page
    When I enter a valid username
    When I enter an empty password
    And I click the login button
    Then I should see an error message

  Scenario: incorrect Login (wrong user-name)
    Given I am on the Sauce Demo login page
    When I enter an invalid username
    When I enter a valid password
    And I click the login button
    Then I should see an error message

  Scenario: incorrect Login (wrong password)
    Given I am on the Sauce Demo login page
    When I enter a valid username
    When I enter an invalid password
    And I click the login button
    Then I should see an error message

  Scenario: incorrect Login (all wrong)
    Given I am on the Sauce Demo login page
    When I enter an invalid username
    When I enter an invalid password
    And I click the login button
    Then I should see an error message
