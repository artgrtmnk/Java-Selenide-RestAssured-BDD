@api
@gql
Feature: GoRest GraphQL API

  Background:
    Given GQL Given I set up a basic url as 'https://gorest.co.in/public/v2/graphql'

  Scenario: Get user list using GraphQL
    When I send a GQL request with body
      """
            query  {
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
            }
      """
    Then GQL Response code is 200
    But GQL Response does not contains '"errors"'

  Scenario: Create a new user using GraphQL
    When I send a GQL request with body
      """
            mutation{
                createUser(input: {
                    name: "Default User"
                    gender: "male"
                    email: "default_email@gmail.com"
                    status: "active"
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
            }
      """
    Then GQL Response code is 200
    And GQL Response contains '"id"'
    But GQL Response does not contains '"errors"'
    And GQL I save user data

  Scenario: Get created user data using GraphQL
    When I send a GQL request with body
      """
            query{
                user(id: 99999) { 
                    id 
                    name 
                    email 
                    gender 
                    status 
                }
            }
      """
    Then GQL Response code is 200
    And GQL Response contains correct user info
    But GQL Response does not contains '"errors"'
    
  Scenario: Change created user details using GraphQL
    When I send a GQL request with body
      """
            mutation{
                updateUser(input: {
                        id: 99999 
                        name: "Donald Duck"
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
            }
      """
    Then GQL Response code is 200
    And GQL Response contains '"name":"Donald Duck"'
    But GQL Response does not contains '"errors"'

  Scenario: Delete created user using GraphQL
    When I send a GQL request with body
      """
            mutation{
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
            }
      """
    Then GQL Response code is 200
    And GQL Response contains '"id"'
    But GQL Response does not contains '"errors"'

  Scenario: Get deleted user data using GraphQL
    When I send a GQL request with body
      """
            query{
                user(id: 99999) { 
                    id 
                    name 
                    email 
                    gender 
                    status 
                }
            }
      """
    Then GQL Response code is 200
    And GQL Response contains '"user":null'
    But GQL Response does not contains '"name":"Donald Duck"'
