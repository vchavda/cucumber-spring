Feature: selenium wikipedia page

  @selenium1 @sel-ts001
  Scenario: Making a call to Wikepedia
    When I am on the landing page: 'https://en.wikipedia.org/wiki/Main_Page'
    And type: "Kilimanjaro" into message field
    And the title of the page is: 'Mount Kilimanjaro - Wikipedia'
#    And the language is changed to French
#    And the language is changed to English
#    And click on the logn link
#    And enter username: 'someusername'
#    And enter password: 'somepassword'

