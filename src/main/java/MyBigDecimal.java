import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class MyBigDecimal extends BigDecimal {

    private static MathContext mc = new MathContext(2, RoundingMode.HALF_UP);

    public MyBigDecimal(double val) {
        super(val);
    }

    public MyBigDecimal(BigDecimal val) {
        super(String.valueOf(val));
    }

    public MyBigDecimal divide(BigDecimal divisor) {
        return new MyBigDecimal(super.divide(divisor, mc));
    }

    public MyBigDecimal add(BigDecimal augend) {
        return new MyBigDecimal(super.add(augend));
    }

    public MyBigDecimal(char[] in, int offset, int len) {
        super(in, offset, len);
    }

    public MyBigDecimal(char[] in, int offset, int len, MathContext mc) {
        super(in, offset, len, mc);
        MyBigDecimal.mc = mc;
    }

    public MyBigDecimal(char[] in) {
        super(in);
    }

    public MyBigDecimal(char[] in, MathContext mc) {
        super(in, mc);
        MyBigDecimal.mc = mc;
    }

    public MyBigDecimal(String val) {
        super(val);
    }

    public MyBigDecimal(String val, MathContext mc) {
        super(val, mc);
    }

    public MyBigDecimal(double val, MathContext mc) {
        super(val, mc);
    }

    public MyBigDecimal(BigInteger val, MathContext mc) {
        super(val, mc);
    }

    public MyBigDecimal(BigInteger unscaledVal, int scale) {
        super(unscaledVal, scale);
    }

    public MyBigDecimal(BigInteger unscaledVal, int scale, MathContext mc) {
        super(unscaledVal, scale, mc);
    }

    public MyBigDecimal(int val) {
        super(val);
    }

    public MyBigDecimal(int val, MathContext mc) {
        super(val, mc);
    }

    public MyBigDecimal(long val) {
        super(val);
    }

    public MyBigDecimal(long val, MathContext mc) {
        super(val, mc);
    }
}
