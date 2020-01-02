Feature: rest assured feature

  @restassured @rest-ts001
  Scenario: making a basic rest API call
    When I make a test call using this url: 'https://reqres.in/api/unknown'
    Then I get a numeric '200' response code returned

  @restassured @rest-ts002
  Scenario Outline: Making a basic rest API call using examples
    When I make a test call using this url: '<url>'
    Then I get a '<code>' response code returned

    Examples:

      | url                           | code |
      | https://reqres.in/api/unknown | 200  |




