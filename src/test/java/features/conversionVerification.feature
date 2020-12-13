Feature: Verify the buy amount with the conversion rate

  @Verify
  Scenario Outline: Verify the buy amount with the conversion rate from GBP to USD
    When User generates an authentication token
    Then User queries detailed rates for conversion with "<BuyCurrency>" "<SellCurrency>" "<FixedSide>" and "<Amount>"
    And User should receive response code as 200
    When User Creates a Conversion Quote to sell "<SellCurrency>" and buy "<BuyCurrency>" with "<FixedSide>" and "<Amount>" and "<agreement>"
    Then User verifies buy amount is correct with respect to conversion rate
    And  User disconnects the Session

    Examples:
      | SellCurrency | BuyCurrency | FixedSide | Amount | agreement |
      | GBP          | USD         | sell      | 1500   | true      |






