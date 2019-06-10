import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RedPencilTest {

    private BigDecimal initialPrice = new BigDecimal(100);
    private LocalDate initialPriceStartDate = LocalDate.of(2019, 4, 1);

    private double discount;
    private double actual;
    private double expected;

    private RedPencil redPencil = new RedPencil(initialPrice);


    @Test
    public void priceWillRemainTheSameIfNotReducedByAtLeastFivePercent() {

        discount = .04;
        LocalDate endDate = LocalDate.of(2019, 5, 3);

        actual = redPencil.calculateDiscountedPrice(initialPriceStartDate, endDate, discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeReducedByMoreThanThirtyPercent() {

        discount = .4;
        LocalDate endDate = LocalDate.of(2019, 5, 3);

        actual = redPencil.calculateDiscountedPrice(initialPriceStartDate, endDate, discount);
        expected = 70;

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeDiscountedBeforeThirtyDaysAtRegularPrice() {
        discount = .2;
        LocalDate endDate = LocalDate.of(2019, 4, 12);

        actual = redPencil.calculateDiscountedPrice(initialPriceStartDate, endDate, discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }

    @Test
    public void priceReturnsToInitialPriceAfterThirtyDaysAtDiscountedPrice() {

        discount = .05;
        LocalDate dateAfterThirtyDays = initialPriceStartDate.plusDays(61);

        actual = redPencil.calculateDiscountedPrice(initialPriceStartDate, dateAfterThirtyDays, discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }
}