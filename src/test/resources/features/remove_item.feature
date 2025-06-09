Feature: Remove item

  Scenario: Remove item
    Given home page is displayed
    When user selects "Laptops" category
    And user selects "MacBook air"
    And user clicks add to cart button
    And successful alert pops up
    And user clicks on "Cart" button
    And user removes product from cart
    Then cart should be empty