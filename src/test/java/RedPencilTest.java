import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RedPencilTest {

    private BigDecimal initialPrice = new BigDecimal(100.00);
    private int days;
    private double discount;
    private BigDecimal actual;
    private BigDecimal expected;

    private RedPencil redPencil = new RedPencil(initialPrice, LocalDate.now());


    @Test
    public void priceWillRemainTheSameIfNotReducedByAtLeastFivePercent() {


        days = 31;
        discount = .04;

        actual = redPencil.calculateDiscountedPrice(days, discount);
        expected = initialPrice.setScale(2, RoundingMode.HALF_UP);

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeReducedByMoreThanThirtyPercent() {

        days = 31;
        discount = .4;

        actual = redPencil.calculateDiscountedPrice(days, discount);
        expected = new BigDecimal(70.00).setScale(2, RoundingMode.HALF_UP);

        assertThat(actual, is(expected));
    }

    @Test
    public void discountedPriceReturnsToInitialPriceAfterThirtyDays() {

        days = 61;
        discount = .05;

        actual = redPencil.calculateDiscountedPrice(days, discount);
        expected = initialPrice.setScale(2, RoundingMode.HALF_UP);

        assertThat(actual, is(expected));
    }
}