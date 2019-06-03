import java.math.BigDecimal;
import java.time.LocalDate;

public class RedPencil {

    public BigDecimal initialPrice;
    public LocalDate startDate;

    public RedPencil(BigDecimal initialPrice, LocalDate startDate) {
        this.initialPrice = initialPrice;
        this.startDate = startDate;
    }

    public double calculateDiscountedPrice(int days, double initialPrice, double discount) {
        double minimumDiscount = .05;
        double maximumDiscount = .3;

        if (days > 30 && days <= 60) {
            return getDiscountedPrice(initialPrice, discount, minimumDiscount, maximumDiscount);
        }
        return initialPrice;
    }

    private Double getDiscountedPrice(double price, double discount, double minimumDiscount, double maximumDiscount) {
        if (checkDiscount(discount, minimumDiscount, maximumDiscount)) {
            return price * (1 - discount);
        }
        if (discount > maximumDiscount) {
            return price * (1 - maximumDiscount);
        }
        return price;
    }

    private boolean checkDiscount(double discount, double minimumDiscount, double maximumDiscount) {
        return discount >= minimumDiscount && discount <= maximumDiscount;
    }
}
