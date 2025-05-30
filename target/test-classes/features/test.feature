Feature: Create order

  Scenario: Create order
    Given home page is displayed
    When user selects "Laptops" category
    And user selects "Sony vaio i5"
    And user is redirected to product detail page
    And user clicks add to cart button
    And successful alert pops up
    And user clicks on "Cart" link
    And user places an order
    And user completes the order form
    Then user checks and confirms order

