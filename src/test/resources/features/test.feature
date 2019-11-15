Feature: basic feature file

  @tag1
  Scenario: making a basic rest API call
    When I make a test call using this url: 'https://reqres.in/api/unknown'
    Then I get a numeric '200' response code returned


  Scenario Outline: Making a basic rest API call using examples
    When I make a test call using this url: '<url>'
    Then I get a '<code>' response code returned

    Examples:

      | url                           | code |
      | https://reqres.in/api/unknown | 200  |


    Scenario: Making a selenium call
      Given Open the Chrome and launch the application
