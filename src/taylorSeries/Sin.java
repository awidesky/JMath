package taylorSeries;

import java.math.BigDecimal;
import java.math.MathContext;

public class Sin {

	private static final int cutOff = 20;
	private static final BigDecimal PI = Pi.getPi(100);
	
	public static void main(String[] args) {

		System.out.println(getSin(Pi.getPi(100).multiply(new BigDecimal("1.5")), 100)); // Should be -1

	}

	/**
	 * 
	 * Calculate <code>sin</code> with Taylor series.
	 * 
	 * */
	public static BigDecimal getSin(final BigDecimal x, final int scale) {
		
		if (x.compareTo(PI.multiply(new BigDecimal(2))) > 0) return getSin(x.remainder(PI.multiply(new BigDecimal(2)), new MathContext(x.precision())), scale);
		if (x.compareTo(PI) > 0) return getSin(x.subtract(PI), scale).multiply(new BigDecimal("-1"));
		if (x.compareTo(PI.divide(new BigDecimal(2))) > 0) return getSin(PI.subtract(x), scale);
		
		BigDecimal sign = new BigDecimal("-1");
		BigDecimal divisor = BigDecimal.ONE;
		BigDecimal i = BigDecimal.ONE;
		BigDecimal num = null;
		BigDecimal dividend = x;
		BigDecimal result = dividend;

		do {
			
			dividend = dividend.multiply(x).multiply(x).multiply(sign);
			
			i = i.add(BigDecimal.ONE);
			divisor = divisor.multiply(i);
			i = i.add(BigDecimal.ONE);
			divisor = divisor.multiply(i);
			
			num = dividend.divide(divisor, scale + cutOff, BigDecimal.ROUND_HALF_UP);
			
			result = result.add(num);

		} while(num.abs().compareTo(new BigDecimal("0.1").pow(scale + cutOff)) > 0);
		
		return result.setScale(scale, BigDecimal.ROUND_HALF_UP);
		
	}

}
