import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RedPencilDiscount {

    private BigDecimal regularPrice;

    public RedPencilDiscount(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public double calculatePrice(LocalDate regularPriceStartDate, LocalDate promoPriceStartDate, double discount) {
        double minimumDiscount = .05;
        double maximumDiscount = .3;

        LocalDate promoPriceEndDate = getPromoPriceEndDate(promoPriceStartDate);

        long daysAtRegularPrice = areDaysValid(regularPriceStartDate, promoPriceStartDate);
        long daysAtPromoPrice = areDaysValid(promoPriceStartDate, promoPriceEndDate);

        if (daysAtRegularPrice >= 30 && daysAtPromoPrice <= 30) {
            return getDiscountedPrice(regularPrice, discount, minimumDiscount, maximumDiscount);
        } else {
            return regularPrice.doubleValue();
        }
    }

    private long areDaysValid(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    private double getDiscountedPrice(BigDecimal regularPrice, double discount, double minimumDiscount, double maximumDiscount) {
        if (checkDiscount(discount, minimumDiscount, maximumDiscount)) {
            return regularPrice.multiply(BigDecimal.ONE.subtract(new BigDecimal(discount))).doubleValue();
        }
        if (discount > maximumDiscount) {
            return regularPrice.multiply(BigDecimal.ONE.subtract(new BigDecimal(maximumDiscount))).doubleValue();
        }
        return regularPrice.doubleValue();
    }

    private boolean checkDiscount(double discount, double minimumDiscount, double maximumDiscount) {
        return discount >= minimumDiscount && discount <= maximumDiscount;
    }

    public LocalDate getPromoPriceEndDate(LocalDate promoPriceStartDate) {
        return promoPriceStartDate.plusDays(30);
    }

}
