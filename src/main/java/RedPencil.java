import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RedPencil {

    private MyBigDecimal initialPrice;

    public RedPencil(MyBigDecimal initialPrice) {
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

    private long getDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    private double getDiscountedPrice(MyBigDecimal initialPrice, double discount, double minimumDiscount, double maximumDiscount) {
        if (checkDiscount(discount, minimumDiscount, maximumDiscount)) {
            return initialPrice.multiply(MyBigDecimal.ONE.subtract(new MyBigDecimal(discount))).doubleValue();
        }
        if (discount > maximumDiscount) {
            return initialPrice.multiply(MyBigDecimal.ONE.subtract(new MyBigDecimal(maximumDiscount))).doubleValue();
        }
        return initialPrice.doubleValue();
    }

    private boolean checkDiscount(double discount, double minimumDiscount, double maximumDiscount) {
        return discount >= minimumDiscount && discount <= maximumDiscount;
    }
}
