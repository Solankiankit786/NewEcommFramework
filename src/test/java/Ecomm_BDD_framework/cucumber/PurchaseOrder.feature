Feature: Purchase the product from e-commerce website

Background:
Given I landed on ecomm website page

@SubmitOrder
  Scenario Outline: Submit an order successfully
    Given User registers with name <name>, email <email> and password <password>
    When User adds product <productName> to the cart
    And User proceeds to checkout
    And User enters shipping details with first name <firstName>, last name <lastName>, country <country>, address <address>, city <city>, state <state>, and pin <pin>
    And User selects "Credit Card" as payment method
    And User places the order
    Then Order confirmation message should be "Thank you. Your order has been received."
    
    Examples: 
      | name    | email              | password | productName      | firstName | lastName | country | address     | city     | state    | pin    |
      | Amar   | ankit@test.com     | Test123  | Basic Blue Jeans | Ankit     | Solanki  | India   | 123 Lane St | Mumbai   | Mah      | 350001 |
