import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RedPencil {

    public BigDecimal initialPrice;
    public LocalDate startDate;

    public RedPencil(BigDecimal initialPrice, LocalDate startDate) {
        this.initialPrice = initialPrice;
        this.startDate = startDate;
    }

    public BigDecimal calculateDiscountedPrice(int days, double discount) {
        double minimumDiscount = .05;
        double maximumDiscount = .3;

        if (days > 30 && days <= 60) {
            return getDiscountedPrice(initialPrice, discount, minimumDiscount, maximumDiscount).setScale(2, RoundingMode.HALF_UP);
        }
        return initialPrice.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getDiscountedPrice(BigDecimal price, double discount, double minimumDiscount, double maximumDiscount) {
        if (checkDiscount(discount, minimumDiscount, maximumDiscount)) {
            return price.multiply(BigDecimal.ONE.subtract(new BigDecimal(discount)));
        }
        if (discount > maximumDiscount) {
            return price.multiply(BigDecimal.ONE.subtract(new BigDecimal(maximumDiscount)));
        }
        return price;
    }

    private boolean checkDiscount(double discount, double minimumDiscount, double maximumDiscount) {
        return discount >= minimumDiscount && discount <= maximumDiscount;
    }
}
