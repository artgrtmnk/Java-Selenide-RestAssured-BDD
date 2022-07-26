Feature: Gorest GraphQL API

  Background:
    Given GQL I set up GraphQL endpoint with url 'https://gorest.co.in/public/v2/graphql'

  Scenario: Get user list using GraphQL
    When GQL I set a request body
      """
      {
        "query":
            "query  {
                users {
                    pageInfo {
                        endCursor 
                        startCursor 
                        hasNextPage 
                        hasPreviousPage
                    } 
                    totalCount nodes {
                        id 
                        name 
                        email 
                        gender 
                        status
                    }
                }
            }"
        }
      """
    When GQL I send a GQL request
    Then GQL Response code is 200
    But GQL Response does not contains
      """
      "errors"
      """

  Scenario: Create a new user using GraphQL
    When GQL I set a request body
      """
      {
        "query":
            "mutation{
                createUser(input: {
                    name: \"Default User\" 
                    gender: \"male\" 
                    email: \"default_email@gmail.com\" 
                    status: \"active\"
                }) 
                {
                    user {
                        id 
                        name 
                        gender 
                        email 
                        status
                    }
                }
            }"
        }
      """
    And GQL I send a GQL request
    Then GQL Response code is 200
    And GQL Response contains
      """
      id
      """
    But GQL Response does not contains
      """
      "errors"
      """
    And GQL I save user data

  Scenario: Get created user data using GraphQL
    When GQL I set a request body
      """
      {
        "query":
            "query{
                user(id: 99999) { 
                    id 
                    name 
                    email 
                    gender 
                    status 
                }
            }"
        }
      """
    And GQL I send a GQL request
    Then GQL Response code is 200
    And GQL Response contains
      """
      id
      """
    But GQL Response does not contains
      """
      "errors"
      """
    
  Scenario: Change created user details using GraphQL
    When GQL I set a request body
      """
      {
        "query":
            "mutation{
                updateUser(input: {
                        id: 99999 
                        name: \"Donald Duck\"
                }) 
                {
                    user {
                            id 
                            name 
                            gender 
                            email 
                            status
                    }
                }
            }"
        }
      """
    And GQL I send a GQL request
    Then GQL Response code is 200
    And GQL Response contains
      """
      "name":"Donald Duck"
      """
    But GQL Response does not contains
      """
      "errors"
      """

  Scenario: Delete created user using GraphQL
    When GQL I set a request body
      """
      {
        "query":
            "mutation{
                deleteUser(input: {
                        id: 99999
                }) 
                {
                    user {
                            id 
                            name 
                            gender 
                            email 
                            status
                    }
                }
            }"
        }
      """
    And GQL I send a GQL request
    Then GQL Response code is 200
    And GQL Response contains
      """
      id
      """
    But GQL Response does not contains
      """
      "errors"
      """

  Scenario: Get deleted user data using GraphQL
    When GQL I set a request body
      """
      {
        "query":
            "query{
                user(id: 99999) { 
                    id 
                    name 
                    email 
                    gender 
                    status 
                }
            }"
        }
      """
    And GQL I send a GQL request
    Then GQL Response code is 200
    And GQL Response contains
      """
      "user":null
      """
    But GQL Response does not contains
      """
      "name":"Donald Duck"
      """
