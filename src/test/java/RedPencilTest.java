import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RedPencilTest {

    private MyBigDecimal initialPrice = new MyBigDecimal(100.00);
    private int days;
    private double discount;
    private double actual;
    private double expected;

    private RedPencil redPencil = new RedPencil(initialPrice, LocalDate.now());


    @Test
    public void priceWillRemainTheSameIfNotReducedByAtLeastFivePercent() {

        days = 31;
        discount = .04;

        actual = redPencil.calculateDiscountedPrice(days, discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeReducedByMoreThanThirtyPercent() {

        days = 31;
        discount = .4;

        actual = redPencil.calculateDiscountedPrice(days, discount);
        expected = 70.00;

        assertThat(actual, is(expected));
    }

    @Test
    public void discountedPriceReturnsToInitialPriceAfterThirtyDays() {

        days = 61;
        discount = .05;

        actual = redPencil.calculateDiscountedPrice(days, discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }
}