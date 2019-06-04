import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RedPencilTest {

    private MyBigDecimal initialPrice = new MyBigDecimal(100.00);
    private LocalDate startDate = LocalDate.of(2019, 4, 1);

    private double discount;
    private double actual;
    private double expected;

    private RedPencil redPencil = new RedPencil(initialPrice);


    @Test
    public void priceWillRemainTheSameIfNotReducedByAtLeastFivePercent() {

        discount = .04;
        LocalDate endDate = LocalDate.of(2019, 5, 3);

        actual = redPencil.calculateDiscountedPrice(startDate, endDate, discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeReducedByMoreThanThirtyPercent() {

        discount = .4;
        LocalDate endDate = LocalDate.of(2019, 5, 3);


        actual = redPencil.calculateDiscountedPrice(startDate, endDate, discount);
        expected = 70.00;

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeDiscountedBeforeThirtyDaysAtRegularPrice() {
        discount = .2;
        LocalDate endDate = LocalDate.of(23019, 4, 12);

        actual = redPencil.calculateDiscountedPrice(startDate, endDate, discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }

    @Test
    public void discountedPriceReturnsToInitialPriceAfterThirtyDays() {

        discount = .05;

        actual = redPencil.calculateDiscountedPrice(startDate, LocalDate.now(), discount);
        expected = initialPrice.doubleValue();

        assertThat(actual, is(expected));
    }
}