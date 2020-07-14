Feature: rest assured feature

  @restassured @rest-ts001
  Scenario: making a basic rest API call
    When I make a test call using this url: 'https://reqres.in/api/unknown'
    Then I get a numeric '200' response code returned


  @restassured @rest-ts001a
  Scenario: making a basic rest API call
    Then I get a numeric '200' response code returned

  @skip
  Scenario: making a basic rest API call
    Then I get a numeric '200' response code returned


  @restassured @rest-ts002
  Scenario: Making a basic rest API call using examples
    Given a user spcifies query parameters:
      | queryParam | queryParamValue |
      | param2     | value2          |
    Given I submit dynamic headers
      | headerType | headerValue |
      | header2    | headerval2  |

    When I make a request to this url: 'https://reqres.in/api/unknown'
    Then I get a '200' response code returned



