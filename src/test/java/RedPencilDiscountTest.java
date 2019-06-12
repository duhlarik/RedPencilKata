import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RedPencilDiscountTest {

    private BigDecimal regularPrice = new BigDecimal(100);
    private LocalDate regularPriceStartDate = LocalDate.of(2019, 3, 1);
    private LocalDate promoPriceStartDate = LocalDate.of(2019, 4, 1);
    private LocalDate promoPriceEndDate;

    private double discount;
    private double actual;
    private double expected;

    private RedPencilDiscount redPencilDiscount = new RedPencilDiscount(regularPrice);

    @Test
    public void priceWillRemainAtRegularPriceIfNotReducedByAtLeastFivePercent() {
        discount = .04;

        actual = redPencilDiscount.calculatePrice(regularPriceStartDate, promoPriceStartDate, discount);
        expected = regularPrice.doubleValue();

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeReducedByMoreThanThirtyPercent() {
        discount = .4;

        actual = redPencilDiscount.calculatePrice(regularPriceStartDate, promoPriceStartDate, discount);
        expected = 70;

        assertThat(actual, is(expected));
    }

    @Test
    public void priceCannotBeDiscountedBeforeThirtyDaysAtRegularPrice() {
        discount = .2;
        promoPriceStartDate = LocalDate.of(2019, 3, 12);

        actual = redPencilDiscount.calculatePrice(regularPriceStartDate, promoPriceStartDate, discount);
        expected = regularPrice.doubleValue();

        assertThat(actual, is(expected));
    }

    @Test
    public void priceReturnsToRegularPriceAfterThirtyDaysAtPromoPrice() {
        discount = .05;
        TestHelper testHelper = new TestHelper();

        actual = redPencilDiscount.calculatePrice(regularPriceStartDate, promoPriceStartDate, discount);
        expected = testHelper.checkPriceNow(regularPrice, LocalDate.of(2019, 4, 15), regularPriceStartDate, promoPriceStartDate, discount);

        assertThat(actual, is(expected));
    }

    @Test
    public void checkBoundaryConditionAtThirtyDays() {
        LocalDate actual = redPencilDiscount.getPromoPriceEndDate(promoPriceStartDate);
        LocalDate expected = LocalDate.of(2019, 5, 1);

        assertThat(actual, is(expected));
    }

//    @Test
//    public void promotionIsNotProlongedByAFurtherReductionInPrice() {
//        discount = .20;
//
//        double discountedPrice = redPencilDiscount.calculatePrice(promoPriceEndDate, discount);
//        double newDiscount = .1;
//        actual = redPencilDiscount.calculatePrice(promoPriceEndDate, newDiscount);
//
//        expected = 72;
//
//        assertThat(actual, is(expected));
//    }
}