import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RedPencilTest {

    private double initialPrice = 100;
    private BigDecimal price;
    private int days;
    private double discount;
    private double actual;
    private double expected;

    private RedPencil redPencil = new RedPencil(price, LocalDate.now());


    @Test
    public void priceWillRemainTheSameIfNotReducedByAtLeastFivePercent() {

        days = 31;
        discount = .04;

        actual = redPencil.calculateDiscountedPrice(days, initialPrice, discount);
        expected = initialPrice;

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeReducedByMoreThanThirtyPercent() {

        days = 31;
        discount = .4;
        double maximumDiscount = .3;

        actual = redPencil.calculateDiscountedPrice(days, initialPrice, discount);
        expected = initialPrice * (1 - maximumDiscount);

        assertThat(actual, is(expected));
    }

    @Test
    public void discountedPriceReturnsToInitialPriceAfterThirtyDays() {

        days = 61;
        discount = .05;

        actual = redPencil.calculateDiscountedPrice(days, initialPrice, discount);
        expected = initialPrice;

        assertThat(actual, is(expected));
    }
}