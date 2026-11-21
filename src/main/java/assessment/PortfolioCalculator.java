package assessment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PortfolioCalculator {

    public enum Action {
        BUY,
        SELL,
        HOLD
    }

    public record SecurityInput(
            String ticker,
            BigDecimal targetWeight,
            BigDecimal currentWeight,
            BigDecimal sharePrice
    ) {}

    public record RebalanceResult(
            String ticker,
            BigDecimal variance,
            Action action,
            BigDecimal dollarAdjustment,
            BigDecimal shares
    ) {}

    public RebalanceResult calculate(
            SecurityInput security,
            BigDecimal totalAssets
    ) {

        // Variance = Current Weight - Target Weight
        BigDecimal variance = security.currentWeight()
                .subtract(security.targetWeight());

        Action action;

        if (variance.compareTo(BigDecimal.ZERO) < 0) {
            action = Action.BUY;
        } else if (variance.compareTo(BigDecimal.ZERO) > 0) {
            action = Action.SELL;
        } else {
            action = Action.HOLD;
        }

        // Calculate the dollar value that needs adjustment
        BigDecimal dollarAdjustment = variance.abs()
                .divide(new BigDecimal("100"))
                .multiply(totalAssets);

        // Calculate the number of shares
        BigDecimal shares = dollarAdjustment
                .divide(security.sharePrice(), 4, RoundingMode.HALF_UP);

        return new RebalanceResult(
                security.ticker(),
                variance,
                action,
                dollarAdjustment,
                shares
        );
    }
}