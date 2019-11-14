Feature: basic feature file

  @tag1
  Scenario: making a basic rest call
  When I make a test call using this url: 'https://reqres.in/api/unknown'
    Then I get a '200' response code returned
