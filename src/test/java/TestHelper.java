import java.math.BigDecimal;
import java.time.LocalDate;

public class TestHelper {

    double checkPriceNow(BigDecimal regularPrice, LocalDate date, LocalDate regularPriceStartDate, LocalDate promoPriceStartDate, double discount) {
        RedPencilDiscount redPencilDiscount = new RedPencilDiscount(regularPrice);
        LocalDate promoPriceEndDate = redPencilDiscount.getPromoPriceEndDate(promoPriceStartDate);
        if ((date.isAfter(promoPriceStartDate) || date.equals(promoPriceStartDate)) && (date.isBefore(promoPriceEndDate) || date.equals(promoPriceEndDate))) {
            return redPencilDiscount.calculatePrice(regularPriceStartDate, promoPriceStartDate, discount);
        }
        return regularPrice.doubleValue();
    }
}
