package assesment;

import assessment.PortfolioCalculator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PortfolioStepDefinitions {

    private BigDecimal totalAssets;

    private String ticker;
    private BigDecimal targetWeight;
    private BigDecimal currentWeight;
    private BigDecimal sharePrice;

    private PortfolioCalculator.RebalanceResult result;

    private final PortfolioCalculator calculator = new PortfolioCalculator();

    private List<PortfolioCalculator.SecurityInput> securities = new ArrayList<>();

    private List<PortfolioCalculator.RebalanceResult> results = new ArrayList<>();

    // Background

    @Given("the total portfolio assets are {bigdecimal}")
    public void theTotalPortfolioAssetsAre(BigDecimal assets) {
        this.totalAssets = assets;
    }

    // Individual security inputs

    @Given("the security is {string}")
    public void theSecurityIs(String ticker) {
        this.ticker = ticker;
    }

    @Given("the target allocation is {bigdecimal} percent")
    public void theTargetAllocationIs(BigDecimal target) {
        this.targetWeight = target;
    }

    @Given("the current allocation is {bigdecimal} percent")
    public void theCurrentAllocationIs(BigDecimal current) {
        this.currentWeight = current;
    }

    @Given("the share price is {bigdecimal}")
    public void theSharePriceIs(BigDecimal price) {
        this.sharePrice = price;
    }

    // Execute individual security calculation

    @When("the portfolio is rebalanced")
    public void thePortfolioIsRebalanced() {

        PortfolioCalculator.SecurityInput security =
                new PortfolioCalculator.SecurityInput(
                        ticker,
                        targetWeight,
                        currentWeight,
                        sharePrice
                );

        result = calculator.calculate(security, totalAssets);
    }

    // Individual security assertions

    @Then("the recommended action should be {string}")
    public void theRecommendedActionShouldBe(String action) {
        assertThat(result.action())
                .isEqualTo(PortfolioCalculator.Action.valueOf(action));
    }

    @Then("the dollar adjustment should be {bigdecimal}")
    public void theDollarAdjustmentShouldBe(BigDecimal adjustment) {
        assertThat(result.dollarAdjustment())
                .isEqualByComparingTo(adjustment);
    }

    @Then("the number of shares should be {bigdecimal}")
    public void theNumberOfSharesShouldBe(BigDecimal shares) {
        assertThat(result.shares())
                .isEqualByComparingTo(shares);

        assertThat(result.shares().scale())
                .isEqualTo(4);
    }

    // Full portfolio input

    @Given("the portfolio contains the following securities:")
    public void thePortfolioContainsTheFollowingSecurities(DataTable dataTable) {

        securities.clear();

        List<Map<String, String>> rows = dataTable.asMaps();

        for (Map<String, String> row : rows) {

            PortfolioCalculator.SecurityInput security =
                    new PortfolioCalculator.SecurityInput(
                            row.get("ticker"),
                            new BigDecimal(row.get("target")),
                            new BigDecimal(row.get("current")),
                            new BigDecimal(row.get("price"))
                    );

            securities.add(security);
        }
    }

    // Execute full portfolio calculation

    @When("the entire portfolio is rebalanced")
    public void theEntirePortfolioIsRebalanced() {

        results = securities.stream()
                .map(security -> calculator.calculate(security, totalAssets))
                .toList();
    }

    // Full portfolio assertions

    @Then("the portfolio should contain {int} results")
    public void thePortfolioShouldContainResults(int count) {
        assertThat(results).hasSize(count);
    }

    @Then("the rebalancing results should be:")
    public void theRebalancingResultsShouldBe(DataTable dataTable) {

        List<Map<String, String>> expectedRows = dataTable.asMaps();

        assertThat(results).hasSize(expectedRows.size());

        for (int i = 0; i < expectedRows.size(); i++) {

            Map<String, String> expected = expectedRows.get(i);

            PortfolioCalculator.RebalanceResult actual = results.get(i);

            assertThat(actual.ticker())
                    .isEqualTo(expected.get("ticker"));

            assertThat(actual.action().name())
                    .isEqualTo(expected.get("action"));

            assertThat(actual.shares())
                    .isEqualByComparingTo(expected.get("shares"));

            assertThat(actual.shares().scale())
                    .isEqualTo(4);
        }
    }
}