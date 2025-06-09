Feature: Validate total price is calculated correctly

  Scenario: Add two products and verify total price
    Given home page is displayed
    When user selects "Phones" category
    And user selects "Nexus 6"
    And user clicks add to cart button
    And successful alert pops up
    And home page is displayed
    And user selects "Monitors" category
    And user selects "ASUS Full HD"
    And user clicks add to cart button
    And successful alert pops up
    And user clicks on "Cart" button
    Then cart contains the added products
    And cart total should match sum of product prices