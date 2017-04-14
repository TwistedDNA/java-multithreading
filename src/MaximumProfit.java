import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksym_Mazur on 4/14/2017.
 */
public class MaximumProfit {

    double[] prices;

    MaximumProfit(double[] prices) {
        this.prices = prices;
    }

    private double calculateMaximumProfitMultiplier() {
        return getSegments(prices).stream().filter(s -> s.raising).map(s -> (s.endValue / s.startValue))
            .reduce((s1, s2) -> s1 * s2).get();
    }

    private List<MonotonicSegment> getSegments(double[] prices) {
        List<MonotonicSegment> segments = new ArrayList<>();
        Double segmentBeginning = prices[0];
        boolean raising = segmentBeginning < prices[1];

        for (int i = 1; i < prices.length; i++) {
            double previousPrice = prices[i - 1];
            double currentPrice = prices[i];

            if (!isMonotonous(previousPrice, currentPrice, raising)) {
                segments.add(new MonotonicSegment(segmentBeginning, previousPrice));
                segmentBeginning = previousPrice;
                raising = !raising;
            }
        }

        double lastPrice = prices[prices.length - 1];
        if (shouldAddLastSegment(segmentBeginning, lastPrice)) {
            segments.add(new MonotonicSegment(segmentBeginning, lastPrice));
        }

        return segments;
    }

    private boolean shouldAddLastSegment(Double startPoint, double lastPrice) {
        return startPoint.doubleValue() != lastPrice;
    }


    private boolean isMonotonous(double start, double end, boolean rising) {
        return (start < end && rising) || (start > end && !rising);
    }

    private class MonotonicSegment {

        double startValue;
        double endValue;
        boolean raising;

        public MonotonicSegment(double startValue, double endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
            raising = startValue < endValue;
        }

    }

    public static void main(String[] args) {
        System.out.println(new MaximumProfit(new double[]{1, 3}).calculateMaximumProfitMultiplier()); //expect 3
        System.out.println(new MaximumProfit(new double[]{2, 4}).calculateMaximumProfitMultiplier()); //expect 2
        System.out.println(new MaximumProfit(new double[]{1, 3, 2, 4}).calculateMaximumProfitMultiplier()); //expect 6
        System.out.println(new MaximumProfit(new double[]{1, 3, 1, 3}).calculateMaximumProfitMultiplier()); //expect 9
        System.out.println(new MaximumProfit(new double[]{1, 3, 1, 3, 1, 3}).calculateMaximumProfitMultiplier()); //expect 27
        System.out.println(new MaximumProfit(new double[]{1, 3, 1, 3, 1, 3, 1, 3}).calculateMaximumProfitMultiplier()); //expect 81
    }
}
