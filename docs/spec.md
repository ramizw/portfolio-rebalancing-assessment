# Portfolio Rebalancing – Test Specification

## 1. Objective

The objective of this assessment is to verify that the portfolio rebalancing application correctly calculates the number of shares that need to be bought or sold for each security to bring its target variance to zero.

The testing will validate:

- Correct identification of securities requiring BUY, SELL or HOLD actions.
- Correct calculation of the number of shares to buy or sell.
- Correct handling of securities that already have zero target variance.
- Correct results for all five securities supplied in the assessment.

## 2. Test Scope

Testing will focus exclusively on the portfolio rebalancing functionality described in the assessment.

The test scope includes:

1. Verifying the BUY calculation.
2. Verifying the SELL calculation.
3. Verifying the HOLD condition.
4. Verifying fractional share calculations.
5. Verifying the expected results for all five securities.
6. Verifying the overall portfolio rebalancing results.

No additional application functionality is assumed.

## 3. Input Data

The assessment provides the following account information.

**Total Account Assets: $100,000**

| Security | Target % | Current % | Target Variance | Unit Share Price |
|---|---:|---:|---:|---:|
| IBM | 20% | 10% | -10% | $150 |
| MSFT | 20% | 20% | 0% | $90 |
| ORCL | 20% | 30% | +10% | $220 |
| AAPL | 20% | 20% | 0% | $450 |
| HD | 20% | 20% | 0% | $70 |

## 4. Functional Requirements

### FR-01: Identify Target Variance

The application must identify the difference between the current allocation percentage and the target allocation percentage.

For this assessment, the variance convention is:

`Difference between Current % & Target %`

### FR-02: Identify Required Action

The application must determine the appropriate action based on the target variance.

| Target Variance | Expected Action |
|---|---|
| Negative | BUY |
| Positive | SELL |
| Zero | HOLD |

### FR-03: Calculate Dollar Adjustment

The application must calculate the monetary value required to correct the target variance.

`Dollar Adjustment = Absolute Target Variance / 100 × Total Account Assets`

### FR-04: Calculate Share Quantity

The application must calculate the number of shares required for each security.

`Share Quantity = Dollar Adjustment / Unit Share Price`

### FR-05: Support Fractional Shares

The application must support fractional share quantities.

**Assumption:** Share quantities will be rounded to four decimal places using HALF_UP rounding.

### FR-06: Handle Zero Variance

When a security has zero target variance:

- The expected action must be HOLD.
- The expected share quantity must be 0.0000.

## 5. Expected Results / Acceptance Criteria

Based on the supplied assessment data, the application should produce the following results.

| Security | Expected Action | Expected Share Quantity |
|---|---|---:|
| IBM | BUY | 66.6667 |
| MSFT | HOLD | 0.0000 |
| ORCL | SELL | 45.4545 |
| AAPL | HOLD | 0.0000 |
| HD | HOLD | 0.0000 |

### AC-01: IBM – BUY

The application must identify IBM as requiring a BUY action and calculate 66.6667 shares.

### AC-02: MSFT – HOLD

The application must identify MSFT as requiring no adjustment and return 0.0000 shares.

### AC-03: ORCL – SELL

The application must identify ORCL as requiring a SELL action and calculate 45.4545 shares.

### AC-04: AAPL – HOLD

The application must identify AAPL as requiring no adjustment and return 0.0000 shares.

### AC-05: HD – HOLD

The application must identify HD as requiring no adjustment and return 0.0000 shares.

### AC-06: Complete Portfolio Verification

The application must return the correct action and share quantity for all five securities.

## 6. Assumptions

The assessment does not explicitly specify every calculation and rounding detail. The following assumptions will be used for testing:

1. Total account assets are $100,000.
2. The assessment contains exactly five securities.
3. Fractional shares are supported.
4. Share quantities are rounded to four decimal places using HALF_UP rounding.
5. Target variance is calculated as Current % minus Target %.
6. The supplied share prices are used for calculations.
7. Transaction fees, taxes and market price fluctuations are excluded.

**Fractional Share Rounding**

- Share quantities must be rounded to four decimal places.
- Rounding mode: HALF_UP.
- The rounding is applied to the final calculated share quantity.
- Trailing zeros must be retained when displaying the result.

Example:

66.666666... → 66.6667 shares

45.454545... → 45.4545 shares

45.500000... → 45.5000 shares

## 7. Testing Deliverables

The assessment will be completed with the following deliverables:

1. Test specification.
2. Documented assumptions.
3. Manual test cases.
4. Automated test cases using Java 21, JUnit 5 and Maven.
5. Automated test execution results.
6. GitHub repository containing the testing solution and documentation.
