# Portfolio Rebalancing – Test Cases

## 1. Test Case Summary

| Test Case ID | Security | Scenario | Expected Action |
|---|---|---|---|
| TC-001 | IBM | Security requires additional shares | BUY |
| TC-002 | MSFT | Current allocation matches target | HOLD |
| TC-003 | ORCL | Security has excess allocation | SELL |
| TC-004 | AAPL | Current allocation matches target | HOLD |
| TC-005 | HD | Current allocation matches target | HOLD |
| TC-006 | All securities | Verify complete portfolio results | BUY / SELL / HOLD |

## 2. Detailed Test Cases

### TC-001: Verify BUY Calculation for IBM

**Objective:** Verify that the application correctly calculates the number of IBM shares to buy when the current allocation is below the target allocation.

**Input:**
- Total assets: $100,000
- Target allocation: 20%
- Current allocation: 10%
- Share price: $150

**Steps:**
1. Provide the IBM input data to the application.
2. Execute the portfolio rebalancing calculation.
3. Verify the recommended action.
4. Verify the calculated share quantity.

**Expected Results:**
- Target variance: -10%.
- Recommended action: BUY.
- Dollar adjustment: $10,000.
- Shares to buy: 66.6667.

### TC-002: Verify HOLD Calculation for MSFT

**Objective:** Verify that the application recommends no adjustment when the current allocation matches the target allocation.

**Input:**
- Total assets: $100,000
- Target allocation: 20%
- Current allocation: 20%
- Share price: $90

**Steps:**
1. Provide the MSFT input data to the application.
2. Execute the portfolio rebalancing calculation.
3. Verify the recommended action.
4. Verify the calculated share quantity.

**Expected Results:**
- Target variance: 0%.
- Recommended action: HOLD.
- Shares to buy or sell: 0.0000.

### TC-003: Verify SELL Calculation for ORCL

**Objective:** Verify that the application correctly calculates the number of ORCL shares to sell when the current allocation exceeds the target allocation.

**Input:**
- Total assets: $100,000
- Target allocation: 20%
- Current allocation: 30%
- Share price: $220

**Steps:**
1. Provide the ORCL input data to the application.
2. Execute the portfolio rebalancing calculation.
3. Verify the recommended action.
4. Verify the calculated share quantity.

**Expected Results:**
- Target variance: +10%.
- Recommended action: SELL.
- Dollar adjustment: $10,000.
- Shares to sell: 45.4545.

### TC-004: Verify HOLD Calculation for AAPL

**Objective:** Verify that the application recommends no adjustment when AAPL's current allocation matches its target allocation.

**Input:**
- Total assets: $100,000
- Target allocation: 20%
- Current allocation: 20%
- Share price: $450

**Steps:**
1. Provide the AAPL input data to the application.
2. Execute the portfolio rebalancing calculation.
3. Verify the recommended action.
4. Verify the calculated share quantity.

**Expected Results:**
- Target variance: 0%.
- Recommended action: HOLD.
- Shares to buy or sell: 0.0000.

### TC-005: Verify HOLD Calculation for HD

**Objective:** Verify that the application recommends no adjustment when HD's current allocation matches its target allocation.

**Input:**
- Total assets: $100,000
- Target allocation: 20%
- Current allocation: 20%
- Share price: $70

**Steps:**
1. Provide the HD input data to the application.
2. Execute the portfolio rebalancing calculation.
3. Verify the recommended action.
4. Verify the calculated share quantity.

**Expected Results:**
- Target variance: 0%.
- Recommended action: HOLD.
- Shares to buy or sell: 0.0000.

### TC-006: Verify Complete Portfolio Rebalancing Results

**Objective:** Verify that the application correctly calculates the required action and share quantity for all five securities.

**Steps:**
1. Provide all five securities and their supplied input data.
2. Execute the portfolio rebalancing calculation.
3. Verify the action recommended for each security.
4. Verify the share quantity for each security.

**Expected Results:**

| Security | Expected Action | Expected Shares |
|---|---|---:|
| IBM | BUY | 66.6667 |
| MSFT | HOLD | 0.0000 |
| ORCL | SELL | 45.4545 |
| AAPL | HOLD | 0.0000 |
| HD | HOLD | 0.0000 |

All five securities must return the expected results.

## 3. Test Execution Status

| Execution Type | Status |
|---|---|
| Manual test cases | Documented |
| Automated BDD tests | PASSED |
| Total automated scenarios | 6 |
| Failures | 0 |
| Errors | 0 |
| Skipped | 0 |

Automated test execution was completed successfully using Maven and Cucumber.

Command used:

```bash
mvn clean test
```

Result: BUILD SUCCESS.

## 4. BDD Automation Coverage

The six test cases documented above have been automated using Cucumber and Gherkin.

Each test case is represented by a corresponding scenario in:

`src/test/resources/features/portfolio-rebalancing.feature`

| Test Case ID | Gherkin Scenario |
|---|---|
| TC-001 | Buy IBM shares when current allocation is below target |
| TC-002 | Hold MSFT shares when current allocation matches target |
| TC-003 | Sell ORCL shares when current allocation is above target |
| TC-004 | Hold AAPL shares when current allocation matches target |
| TC-005 | Hold HD shares when current allocation matches target |
| TC-006 | Rebalance the complete portfolio |

The automated scenarios validate:
- Recommended rebalancing action.
- Dollar adjustment.
- Calculated share quantity.
- Four-decimal-place rounding.
- Results across the complete portfolio.

The Cucumber HTML execution report is generated at:

`target/cucumber-reports/cucumber.html`
