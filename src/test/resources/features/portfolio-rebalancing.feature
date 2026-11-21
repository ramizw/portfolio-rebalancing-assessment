Feature: Portfolio Rebalancing

  As a portfolio manager
  I want to calculate the shares to buy or sell
  So that the portfolio allocation matches the target allocation

  Background:
    Given the total portfolio assets are 100000

  Scenario: Buy IBM shares when current allocation is below the target
    Given the security is "IBM"
    And the target allocation is 20 percent
    And the current allocation is 10 percent
    And the share price is 150
    When the portfolio is rebalanced
    Then the recommended action should be "BUY"
    And the dollar adjustment should be 10000
    And the number of shares should be 66.6667

  Scenario: Hold MSFT shares when current allocation matches the target
    Given the security is "MSFT"
    And the target allocation is 20 percent
    And the current allocation is 20 percent
    And the share price is 90
    When the portfolio is rebalanced
    Then the recommended action should be "HOLD"
    And the dollar adjustment should be 0
    And the number of shares should be 0.0000

  Scenario: Sell ORCL shares when current allocation is above the target
    Given the security is "ORCL"
    And the target allocation is 20 percent
    And the current allocation is 30 percent
    And the share price is 220
    When the portfolio is rebalanced
    Then the recommended action should be "SELL"
    And the dollar adjustment should be 10000
    And the number of shares should be 45.4545

  Scenario: Hold AAPL shares when current allocation matches target
    Given the security is "AAPL"
    And the target allocation is 20 percent
    And the current allocation is 20 percent
    And the share price is 450
    When the portfolio is rebalanced
    Then the recommended action should be "HOLD"
    And the dollar adjustment should be 0
    And the number of shares should be 0.0000

  Scenario: Hold HD shares when current allocation matches target
    Given the security is "HD"
    And the target allocation is 20 percent
    And the current allocation is 20 percent
    And the share price is 70
    When the portfolio is rebalanced
    Then the recommended action should be "HOLD"
    And the dollar adjustment should be 0
    And the number of shares should be 0.0000

  Scenario: Rebalance the complete portfolio
    Given the portfolio contains the following securities:
      | ticker | target | current | price |
      | IBM    | 20     | 10      | 150   |
      | MSFT   | 20     | 20      | 90    |
      | ORCL   | 20     | 30      | 220   |
      | AAPL   | 20     | 20      | 450   |
      | HD     | 20     | 20      | 70    |
    When the entire portfolio is rebalanced
    Then the portfolio should contain 5 results
    And the rebalancing results should be:
      | ticker | action | shares  |
      | IBM    | BUY    | 66.6667 |
      | MSFT   | HOLD   | 0.0000  |
      | ORCL   | SELL   | 45.4545 |
      | AAPL   | HOLD   | 0.0000  |
      | HD     | HOLD   | 0.0000  |