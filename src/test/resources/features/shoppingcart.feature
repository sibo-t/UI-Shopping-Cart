@shopping-cart
Feature: Shopping cart feature

  Scenario Outline: Validate that as a user can filter products by size
    When the user filter products by size "<size>"
    Then the number of products found are "<no_of_products>"

    Examples:
      | size              |  no_of_products             |
      |XS                 |  1                          |


  Scenario Outline: Validate that a user can add a product to cart
    When the user adds the product "<product_name>"
    Then the product "<product_name>" is present in the cart

    Examples:
      |  product_name                 |
      | Cropped Stay Groovy off white |