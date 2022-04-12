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
    * this static json test: 'data[0].data2[0].data3[0].name'
    And the node: 'data[*].id' is sorted in 'ascending' order
    * the response should contain this node: 'data[0].id' and value: '1'
    * the header: 'X-Powered-By' is populated
    * the response should only contain these values: 'cerulean|fuchsia rose|true red|aqua sky|tigerlily|blue turquoise' for node: 'data[*].name'
    * the number of of occurance of the node: 'data[*].name' in the response should be: '6'
    * this node: 'data[0].name' should be returned in the response
    * this header is removed: 'header2'
    #this last step demonstrates how stae is shared between different steps (when steps and then steps)
    * the response should contain values
      | data[0].id    | 1        |
      | data[0].name  | cerulean |
      | data[0].year  | 2000     |
      | data[0].color | #98B2D1  |


  @restassured @rest-ts003
  Scenario Outline: testForDoubleQotes
    And my respone should contain this '<node>' and '<value>'
    Examples:
      | node      | value     |
      | "red hat" | black cat |
