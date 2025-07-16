Feature: Purchase the product from e-commerce website

Background:
Given I landed on ecomm website page

@ErrorValidation
  Scenario Outline: verify for invalid product
    Given User registers with name <name>, email <email> and password <password>
    When User adds product <productName> to the cart
    And User update the product with <no>
    Then Verify the product <checkproductName>
    
    Examples: 
      | name    | email             | password | productName      | no |checkproductName|
      | Amar   | ankit@test.com     | Test123  | Basic Blue Jeans | 4  | Basic Jeans    |
