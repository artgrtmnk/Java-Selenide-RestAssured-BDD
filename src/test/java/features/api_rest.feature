Feature: GoRest Rest API

  Background:
    Given I set up a basic url as 'https://gorest.co.in/public/v2/users'

  Scenario: Get user list
    When I send a Get user list request
    Then Response code is 200

  Scenario: Create new user
    When I send a Post create user request
    Then Response code is 201
    And Response contains 'id'
    And I save user id

  Scenario: Get created user data
    When I send a Get created user request
    Then Response code is 200
    And Response contains correct user info

  Scenario: Change created user details
    When I send a Patch user request with body
      """
      { "name": "Donald Duck" }
      """
    Then Response code is 200
    And Response contains 'Donald Duck'

  Scenario: Delete created user
    When I send a Delete user request
    Then Response code is 204

  Scenario: Verify that user was deleted
    When I send a Get created user request
    Then Response code is 404