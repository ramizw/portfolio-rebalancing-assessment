# Portfolio Rebalancing – QA Automation Assessment

## 1. Overview

This project was created as part of the QA Automation Engineer Team Lead technical assessment for Charles River Development (CRD), a State Street company.

The objective is to validate portfolio rebalancing calculations and verify the number of shares that should be bought or sold for each security to bring the portfolio allocation in line with its target allocation.

As the assessment did not provide an existing application, API or executable system to test, a minimal Java reference calculator was implemented based on the supplied requirements and documented assumptions.

The automated tests validate this reference implementation against the expected business behaviour. They do not represent testing of the actual CRD application.

## 2. Technology Stack

- Java 21
- Maven
- JUnit 5
- Cucumber (BDD)
- Gherkin
- AssertJ
- IntelliJ IDEA

## 3. Project Structure

```text
portfolio-rebalancing/
│
├── docs/
│   ├── spec.md
│   └── test-cases.md
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── assessment/
│   │           ├── PortfolioCalculator.java
│   │           └── PortfolioRunner.java
│   │
│   └── test/
│       ├── java/
│       │   └── assessment/
│       │       ├── PortfolioStepDefinitions.java
│       │       └── RunCucumberTest.java
│       │
│       └── resources/
│           └── features/
│               └── portfolio-rebalancing.feature
│
├── .gitignore
├── pom.xml
└── README.md
```

## 4. Business Logic

The portfolio contains five securities with a total asset value of $100,000.

The target allocation for each security is 20%.

The calculation follows these rules:

**Variance**

Current Allocation (%) - Target Allocation (%)

**Dollar Adjustment**

Absolute Variance (%) / 100 × Total Portfolio Assets

**Number of Shares**

Dollar Adjustment / Current Share Price

The recommended action is determined by the variance:

- Negative variance: BUY
- Positive variance: SELL
- Zero variance: HOLD

## 5. Assumptions

The assessment does not specify all calculation and rounding rules. The following assumptions were applied:

- Variance is calculated as Current Allocation minus Target Allocation.
- Share quantities are rounded to four decimal places.
- HALF_UP rounding is used.
- Trailing zeros are retained.
- Transaction fees, taxes and market price fluctuations are excluded.
- The supplied share prices are used for all calculations.

Further details are documented in `docs/spec.md`.

## 6. Test Coverage

The project contains six BDD scenarios covering the supplied assessment data.

| Test Scenario | Expected Action | Expected Shares |
|---|---|---:|
| IBM – Below target allocation | BUY | 66.6667 |
| MSFT – Matches target allocation | HOLD | 0.0000 |
| ORCL – Above target allocation | SELL | 45.4545 |
| AAPL – Matches target allocation | HOLD | 0.0000 |
| HD – Matches target allocation | HOLD | 0.0000 |
| Complete portfolio rebalance | BUY / SELL / HOLD | All five securities |

The scenarios validate:
- Recommended rebalancing action.
- Dollar adjustment.
- Calculated share quantity.
- Four-decimal-place rounding.
- Results across the complete portfolio.

## 7. Running the Tests

### Prerequisites

- Java 21
- Maven

### Execute the automated tests

Open a terminal in the project root directory and run:

```bash
mvn clean test
```

Maven will compile the project and execute the automated test scenarios.

### Expected result

```text
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

### Cucumber HTML Report

After executing the tests, the Cucumber HTML report is generated at:

```text
target/cucumber-reports/cucumber.html
```

Open this file in a web browser to view the test execution results.

## 8. Documentation

Additional assessment documentation is available in the `docs` directory:

- `spec.md` – Requirements, calculation rules and assumptions.
- `test-cases.md` – Manual test cases and expected results.

## 9. Notes

This is a QA assessment reference project created to demonstrate requirements analysis, test case design, BDD scenario development and automated test execution.

The Java calculator is a reference implementation based on the assessment requirements. In a real application testing engagement, the same test scenarios would be executed against the actual application or its exposed interfaces.