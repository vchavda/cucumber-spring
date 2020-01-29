Feature: selenium easy

  @selenium1 @seleasy-ts001
  Scenario: Making a call to SeleniumEasy - simple Forms
    Given My landing page is: 'https://www.seleniumeasy.com/test/'
    Then test simple form

  @selenium1 @seleasy-ts002
  Scenario: Making a call to SeleniumEasy - checkbox demo
    Given My landing page is: 'https://www.seleniumeasy.com/test/'
    Then test checkbox demo


  @selenium1 @seleasy-ts003
  Scenario: Making a call to SeleniumEasy - RadioButton demo
    Given My landing page is: 'https://www.seleniumeasy.com/test/'
    Then test radiobutton demo

  @selenium1 @seleasy-ts004
  Scenario: Making a call to SeleniumEasy - dropdown List  demo
    Given My landing page is: 'https://www.seleniumeasy.com/test/'
    Then test dropdownlist demo