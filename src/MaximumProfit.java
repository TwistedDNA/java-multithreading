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

    private double calculateProfit() {
        return getSegments(prices).stream().filter(s -> s.raising).map(s -> (s.endValue / s.startValue))
            .reduce((s1, s2) -> s1 * s2).get();
    }

    private List<MonotonicSegment> getSegments(double[] prices) {
        List<MonotonicSegment> segments = new ArrayList<>();
        Double startPoint = prices[0];
        Double endPoint = prices[1];
        boolean raising = startPoint < endPoint;

        for (int i = 1; i < prices.length; i++) {
            double previousPrice = prices[i - 1];
            double currentPrice = prices[i];

            if (!isMonotonous(previousPrice, currentPrice, raising)) {
                segments.add(new MonotonicSegment(startPoint, currentPrice));
                startPoint = currentPrice;
                raising = !raising;
            }
        }

        return segments;
    }

    private boolean isMonotonous(double start, double end, boolean rising) {
        return (start < end && rising) || (start > end && !rising);
    }

    private class MonotonicSegment {

        boolean raising;

        double startValue;
        double endValue;

        public MonotonicSegment(double startValue, double endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
            raising = startValue < endValue;
        }

    }

    public static void main(String[] args) {
        System.out.println(new MaximumProfit(new double[]{1, 3, 2, 4}).calculateProfit());
    }
}
