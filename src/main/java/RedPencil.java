import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RedPencil {

    private BigDecimal initialPrice;

    public RedPencil(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double calculateDiscountedPrice(LocalDate startDate, LocalDate endDate, double discount) {
        double minimumDiscount = .05;
        double maximumDiscount = .3;

        long days = getDays(startDate, endDate);

        if (days > 30 && days <= 60) {
            return getDiscountedPrice(initialPrice, discount, minimumDiscount, maximumDiscount);
        }
        return initialPrice.doubleValue();
    }

    public long getDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    private double getDiscountedPrice(BigDecimal initialPrice, double discount, double minimumDiscount, double maximumDiscount) {
        if (checkDiscount(discount, minimumDiscount, maximumDiscount)) {
            return initialPrice.multiply(BigDecimal.ONE.subtract(new BigDecimal(discount))).doubleValue();
        }
        if (discount > maximumDiscount) {
            return initialPrice.multiply(BigDecimal.ONE.subtract(new BigDecimal(maximumDiscount))).doubleValue();
        }
        return initialPrice.doubleValue();
    }

    private boolean checkDiscount(double discount, double minimumDiscount, double maximumDiscount) {
        return discount >= minimumDiscount && discount <= maximumDiscount;
    }
}
