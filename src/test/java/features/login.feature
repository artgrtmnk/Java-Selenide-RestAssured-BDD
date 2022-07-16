Feature: Login

  Scenario Outline: Invalid sign in with user
    Given User has opened Oracle Profile page
    When User inputs '<email>' as email
    And User inputs '<password>' as password
    And User presses Sign In button
    Then User sees invalid credentials message

    Examples:
      | email           | password  |
      | aaajf@pdosf.com | Password1 |
      | cndvm@kofs.com  | Password2 |
      | cndvm@kofs.org  | Password3 |