Feature: Derived Product 2 features

  Background: 
    Given Initialize all the page objects in the bulls

  Scenario: Finding the all the footer links from the bulls
    When Page scroll to footer
    Then Validate is any footer hyper links have duplicates