package taylorSeries;

import java.math.BigDecimal;

public class E {

	private static final int cutOff = 20;
	
	public static void main(String[] args) {

		System.out.println(getE(100));

	}
	
	public static BigDecimal getE(int scale) {
		
		BigDecimal e = BigDecimal.ZERO;
		BigDecimal x = BigDecimal.ONE; //Exponent of e
		BigDecimal divider = BigDecimal.ONE;
		BigDecimal num = BigDecimal.ONE;
		
		BigDecimal n = BigDecimal.ONE;
		
		do {
			
			num = BigDecimal.ONE.divide(divider, scale + cutOff, BigDecimal.ROUND_HALF_UP).multiply(x);
			e = e.add(num);
			
			x = x.multiply(x);
			divider = divider.multiply(n);
			n = n.add(BigDecimal.ONE);
			
		} while(num.compareTo(new BigDecimal("0.1").pow(scale + cutOff)) > 0);
		
		//System.err.println(num);
		//System.err.println(new BigDecimal("0.1").pow(scale + cutOff));
		
		return e.setScale(scale, BigDecimal.ROUND_HALF_UP);
		
	}
	
	/*
	 * static BigDecimal factorial(BigInteger num) {
	 * 
	 * BigInteger x = BigInteger.ONE;
	 * 
	 * for (BigInteger i = BigInteger.ONE; !num.equals(i) ; i =
	 * i.add(BigInteger.ONE)) {
	 * 
	 * x = x.multiply(i);
	 * 
	 * }
	 * 
	 * return new BigDecimal(x);
	 * 
	 * }
	 */
}
