Feature: Core Test Product features

  Background: 
    Given Initialize all the page objects

  Scenario: Finding the all the Men Jackes from the core product
    When if any cookie policy popup comes up
    And Go to Shop Menu Mens
    And Go to Men Menu Jackets
    Then Get all the price and top seller comments

  Scenario Outline: Finding the all videos count which is >=3d from the News & Features from the core product
    When if any cookie policy popup comes up
    And Go to Secondary menu and tap on News & Features
    Then Validate total number of <count> videos feeds and those are present in the page with "<condition>"
    
    Examples: 
      | count | condition |
      |  3    |   >=	    |
      |  1    |   <=	    |