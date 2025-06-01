Feature: Create order

  Scenario: Create order
    Given home page is displayed
    When user selects "Laptops" category
    And user selects "Sony vaio i5"
    And user clicks add to cart button
    And successful alert pops up
    And user clicks on "Cart" button
    And user places an order
    And user completes the order form
      | name  | country | city       | creditCard | month | year |
      | Jonny | Mexico  | Monterrey  | 4568       | 03    | 1999 |
    Then user checks and confirms order

