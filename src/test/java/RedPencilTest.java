import org.junit.Test;

import java.awt.event.ItemEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RedPencilTest {

    private double initialPrice = 100;
    private int days;
    private double discount;
    private double actual;
    private double expected;

    private RedPencil redPencil = new RedPencil();


    @Test
    public void priceShouldBeStableForThirtyDaysBeforePriceChange() {

        days = 30;

        boolean actual = redPencil.availableForDiscount(days);

        assertTrue(actual);
    }

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

    @Test
    public void anAdditionalDiscountOnAnAlreadyReducedItemWillNotProlongTheSalePast30Days() {

        days = 31;
        discount = .05;

        redPencil.calculateDiscountedPrice(days, initialPrice, discount);

        double discountedPrice = 0;
        redPencil.applyDiscount(discountedPrice);
    }
}