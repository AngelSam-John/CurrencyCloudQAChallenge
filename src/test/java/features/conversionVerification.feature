Feature: Verify the buy amount with the conversion rate

  @Verify
  Scenario: Verify the buy amount with the conversion rate from GBP to USD

    Given I am an authorized user
    Then I query detailed rates for GBP to USD
    When I Create a Quote for Conversion to sell GBP and buy USD
    Then verify buy amount equals to conversion rate
    And End the Session

  Scenario: Verify the buy amount with the conversion rate from GBP to USD

    Given I am an authorized user
    Then I query detailed rates for GBP to USD
    When I Create a Quote for Conversion to sell GBP and buy USD
    Then verify buy amount not equals to conversion rate
    And End the Session




