package assessment;

import java.math.BigDecimal;

public class PortfolioRunner {

    public static void main(String[] args) {

        BigDecimal totalAssets = new BigDecimal("100000");

        PortfolioCalculator calculator = new PortfolioCalculator();

        PortfolioCalculator.SecurityInput[] securities = {

                new PortfolioCalculator.SecurityInput(
                        "IBM",
                        new BigDecimal("20"),
                        new BigDecimal("10"),
                        new BigDecimal("150")
                ),

                new PortfolioCalculator.SecurityInput(
                        "MSFT",
                        new BigDecimal("20"),
                        new BigDecimal("20"),
                        new BigDecimal("90")
                ),

                new PortfolioCalculator.SecurityInput(
                        "ORCL",
                        new BigDecimal("20"),
                        new BigDecimal("30"),
                        new BigDecimal("220")
                ),

                new PortfolioCalculator.SecurityInput(
                        "AAPL",
                        new BigDecimal("20"),
                        new BigDecimal("20"),
                        new BigDecimal("450")
                ),

                new PortfolioCalculator.SecurityInput(
                        "HD",
                        new BigDecimal("20"),
                        new BigDecimal("20"),
                        new BigDecimal("70")
                )
        };

        System.out.println("PORTFOLIO REBALANCING RESULTS");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-10s %-12s %-12s %-15s%n",
                "Security", "Action", "Variance", "Shares");
        System.out.println("-----------------------------------------------");

        for (PortfolioCalculator.SecurityInput security : securities) {

            PortfolioCalculator.RebalanceResult result =
                    calculator.calculate(security, totalAssets);

            System.out.printf("%-10s %-12s %-12s %-15s%n",
                    result.ticker(),
                    result.action(),
                    result.variance() + "%",
                    result.shares().toPlainString());
        }

        System.out.println("-----------------------------------------------");
    }
}