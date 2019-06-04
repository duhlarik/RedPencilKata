import java.time.LocalDate;

public class RedPencil {

    private MyBigDecimal initialPrice;
    private LocalDate startDate;

    public RedPencil(MyBigDecimal initialPrice, LocalDate startDate) {
        this.initialPrice = initialPrice;
        this.startDate = startDate;
    }

    public double calculateDiscountedPrice(int days, double discount) {
        double minimumDiscount = .05;
        double maximumDiscount = .3;

        if (days > 30 && days <= 60) {
            return getDiscountedPrice(initialPrice, discount, minimumDiscount, maximumDiscount);
        }
        return initialPrice.doubleValue();
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
